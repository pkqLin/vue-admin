package com.vueadmin.vueadmin.system.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.common.Result;
import com.vueadmin.vueadmin.system.entity.SysFile;
import com.vueadmin.vueadmin.system.mapper.SysFileMapper;
import com.vueadmin.vueadmin.system.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;


    @Value("${files.upload.url}")
    private String fileUploadUrl;

    @Resource
    private SysFileMapper sysFileMapper;

    /**
     * @Description: 文件上传
     * @Param:
     * @Author: pkqLin
     * @Date: 2023/2/3 15:04
     * @version V1.0
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String type = FileUtil.extName(originalFileName);
        Long size = file.getSize();
        //定义一个文件的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        //判断文件夹是否存在
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }

        String url = fileUploadUrl+ fileUUID;
        //获取文件的md5信息
        String md5 = SecureUtil.md5(file.getInputStream());
        //从数据库查询是否有相同记录
        // 查询文件的md5是否存在
        QueryWrapper<SysFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<SysFile> filesList = sysFileMapper.selectList(queryWrapper);
        SysFile dbFiles = (filesList.size() == 0 ? null : filesList.get(0));
        if (dbFiles != null) {
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
//            url = "http://" + serverIp + ":9090/file/" + fileUUID;
        }

        SysFile sysFile = new SysFile();
        sysFile.setType(type);
        sysFile.setSize(size / 1024);
        sysFile.setName(originalFileName);
        sysFile.setUrl(url);
        sysFile.setMd5(md5);
        sysFileMapper.insert(sysFile);
        return url;
    }

    /**
     * @description: TODO
     * @authoe: lin
     * @date: 2023/4/4 16:15
     * @version: 1.0
     */
    @GetMapping("/{uuid}")
    public void download(@PathVariable String uuid, HttpServletResponse response) throws IOException {
        //根据文件唯一标识吗获取文件
        File downloadFile = new File(fileUploadPath + uuid);
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Dosposition", "attachment;filename" + URLEncoder.encode(uuid, "UTF-8"));
        response.setContentType("application/octet-stream");

        os.write(FileUtil.readBytes(downloadFile));
        os.flush();
        os.close();
    }

    /***
    * @description: TODO 查询数据对于上传的文件
    * @authoe: lin
    * @date: 2023/4/6 13:52
    * @version: 1.0
    */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam(defaultValue = "") String name) {

        QueryWrapper<SysFile> queryWrapper =  new QueryWrapper<>();
        //查询未删除的记录
        queryWrapper.eq("is_del",false);
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
        return Result.success(this.sysFileMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @PostMapping("update")
    public Result update(@RequestBody SysFile sysFile){
        sysFileMapper.updateById(sysFile);
        return Result.success();
    }

    @GetMapping("/detail/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(sysFileMapper.selectById(id));
    }

    //清除一条缓存，key为要清空的数据
//    @CacheEvict(value="files",key="'frontAll'")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        SysFile files = sysFileMapper.selectById(id);
        files.setIsDel(1);
        sysFileMapper.updateById(files);
//        flushRedis(Constants.FILES_KEY);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        // select * from sys_file where id in (id,id,id...)
        QueryWrapper<SysFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        List<SysFile> files = sysFileMapper.selectList(queryWrapper);
        for (SysFile file : files) {
            file.setIsDel(1);
            sysFileMapper.updateById(file);
        }
        return Result.success();
    }

}
