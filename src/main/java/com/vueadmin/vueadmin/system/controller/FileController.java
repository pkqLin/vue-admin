package com.vueadmin.vueadmin.system.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.vueadmin.vueadmin.system.entity.SysFile;
import com.vueadmin.vueadmin.system.mapper.SysFileMapper;
import com.vueadmin.vueadmin.system.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
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
        //先存储到磁盘
        //
        File uploadParentFile = new File(fileUploadPath);
        //判断文件夹是否存在
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdir();
        }
        //定义一个文件的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String url="http://localhost:8091/file/"  + uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + uuid + StrUtil.DOT + type);
        file.transferTo(uploadFile);
        SysFile sysFile = new SysFile();
        sysFile.setType(type);
        sysFile.setSize(size/1024);
        sysFile.setName(originalFileName);
        sysFile.setUrl(url);
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


}
