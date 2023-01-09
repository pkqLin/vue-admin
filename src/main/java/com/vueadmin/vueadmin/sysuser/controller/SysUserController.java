package com.vueadmin.vueadmin.sysuser.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.common.Result;
import com.vueadmin.vueadmin.sysuser.entity.SysUser;
import com.vueadmin.vueadmin.sysuser.mapper.SysUserMapper;
import com.vueadmin.vueadmin.sysuser.service.SysUserService;
import org.apache.catalina.User;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2022-11-23 14:05:01
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUser selectOne(Integer id) {
        return this.sysUserService.queryById(id);
    }

    /**
     * @Title:
     * @Description: 查询所有用户数据
     * @ClassName: path: com.vueadmin.vueadmin.sysuser.controller.SysUserController  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/23 16:50
     * @version V1.0
     */
    @GetMapping("selectAll")
    public List<SysUser> queryAll(@RequestBody SysUser sysUser) {
        return this.sysUserService.queryAll(sysUser);
    }

    /**
     * @Title:
     * @Description: 新增用户
     * @ClassName: path: com.vueadmin.vueadmin.sysuser.controller.SysUserController  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/23 17:04
     * @version V1.0
     */
    @PostMapping(" ")
    public SysUser insert(@RequestBody SysUser sysUser) {
        this.sysUserService.insert(sysUser);
        return sysUser;
    }

    @PostMapping("insert1")
    public boolean insert1(@RequestBody SysUser sysUser) {
        return this.sysUserService.save(sysUser);

    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @PostMapping("update")
    public SysUser update(@RequestBody SysUser sysUser) {
        this.sysUserService.update(sysUser);
        return this.sysUserService.queryById(sysUser.getId());
    }

    /* *
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return this.sysUserService.deleteById(id);
    }

    /**
     * @Title:
     * @Description: 分页查询用户-手写
     * @ClassName: path: com.vueadmin.vueadmin.sysuser.controller.SysUserController  -->  function: 
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/24 13:47
     * @version V1.0
     */
/*    @GetMapping("page")
    public Map<String ,Object>  findPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<SysUser> all = this.sysUserService.queryAllByLimit(pageNum, pageSize);
        Map<String ,Object> res  =new HashMap<>();
        int total =this.sysUserService.total(pageNum);
        res.put("data",all);
        res.put("total",total);
        return res;
    }*/

    /**
     * @Title:
     * @Description: mybatics 方式分页查询
     * @ClassName: path: com.vueadmin.vueadmin.sysuser.controller.SysUserController  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/28 10:43
     * @version V1.0
     */
    @GetMapping("page")
    public Result findPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(defaultValue = "") String username) {
//        IPage<SysUser> page = new Page<>(pageNum, pageSize);
//        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("username", username);
//        IPage<SysUser> pageUser = this.sysUserService.page(page, queryWrapper);
//        return pageUser;
        return Result.success(this.sysUserService.findPage(new Page<>(pageNum, pageSize), username));
    }

    /**
     * @Title:
     * @Description: 导出
     * @ClassName: path: com.vueadmin.vueadmin.sysuser.controller.SysUserController  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2023/1/9 13:30
     * @version V1.0
     */
    @GetMapping("export")
    public void export(HttpServletResponse resp) throws Exception {
        List<SysUser> list = this.sysUserService.queryAll(new SysUser());
        //通过工具类创建类写出到磁盘路径
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //加列名
//        writer.addHeaderAlias("id", "id");
//        writer.addHeaderAlias("username", "用户名");
//        writer.addHeaderAlias("password", "密码");
//        writer.addHeaderAlias("nickname", "昵称");
//        writer.addHeaderAlias("mail", "邮箱");
//        writer.addHeaderAlias("tel", "电话");
//        writer.addHeaderAlias("address", "地址");
//        writer.addHeaderAlias("createTime", "创建时间");

        //一次性写出list对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        //设置浏览器响应的格式
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = resp.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<SysUser> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            SysUser user = new SysUser();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
//            user.setAvatarUrl(row.get(6).toString());
            users.add(user);
        }

        this.sysUserService.saveBatch(users);
        return Result.success(true);
    }


}
