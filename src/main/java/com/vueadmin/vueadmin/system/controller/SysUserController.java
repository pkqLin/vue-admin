package com.vueadmin.vueadmin.system.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.common.Constants;
import com.vueadmin.vueadmin.common.Result;
import com.vueadmin.vueadmin.system.controller.dto.UserDto;
import com.vueadmin.vueadmin.system.entity.SysUser;
import com.vueadmin.vueadmin.system.service.SysUserService;
import com.vueadmin.vueadmin.util.TokenUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

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
     * @Description: 登录接口
     * @Param: [userDto]
     * @Author: pkqLin
     * @Date: 2023/2/3 10:20
     * @version V1.0
     */
    @PostMapping("login")
    public Result login(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDto dto = sysUserService.login(userDto);
        return Result.success(dto);
    }


    /**
     * @Description: 注册接口
     * @Param: [userDTO]
     * @Author: pkqLin
     * @Date: 2023/2/3 10:20
     * @version V1.0
     */
    @PostMapping("register")
    public Result register(@RequestBody UserDto userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(sysUserService.register(userDTO));
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Result selectOne(Integer id) {
        return Result.success(this.sysUserService.queryById(id));
    }

    /** 
     * @Description: 通过用户名进行查询
     * @Param:  
     * @Author: pkqLin
     * @Date: 2023/2/3 10:26
     * @version V1.0
    */
    @GetMapping("/username/{username}")
    public Result selectOne(@PathVariable String username){
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(this.sysUserService.getOne(queryWrapper));
    }

    /**
     * @Title:
     * @Description: 查询所有用户数据
     * @ClassName: path: com.vueadmin.vueadmin.system.controller.SysUserController  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/23 16:50
     * @version V1.0
     */
    @GetMapping("selectAll")
    public Result queryAll(@RequestBody SysUser sysUser) {
        return Result.success(this.sysUserService.queryAll(sysUser));
    }

    /**
     * @Description: 更新数据
     * @Param: [sysUser]
     * @Author: pkqLin
     * @Date: 2023/2/3 10:58
     * @version V1.0
     */
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysUser sysUser) {
        if (sysUser.getId() == null && sysUser.getPassword() == null) {  // 新增用户默认密码
//            user.setPassword( SecureUtil.md5("123"));
        }
        return Result.success(this.sysUserService.saveOrUpdate(sysUser));
    }

    /*@PostMapping("insert1")
    public Result insert1(@RequestBody SysUser sysUser) {
        return Result.success(this.sysUserService.save(sysUser));

    }*/

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        this.sysUserService.update(sysUser);
        return Result.success(this.sysUserService.queryById(sysUser.getId()));
    }

    /* *
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return Result.success(this.sysUserService.deleteById(id));
    }

    /**
     * @Title:
     * @Description: 分页查询用户-手写
     * @ClassName: path: com.vueadmin.vueadmin.system.controller.SysUserController  -->  function:
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
     * @ClassName: path: com.vueadmin.vueadmin.system.controller.SysUserController  -->  function:
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
     * @ClassName: path: com.vueadmin.vueadmin.system.controller.SysUserController  -->  function:
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
            user.setAvatarUrl(row.get(6).toString());
            users.add(user);
        }

        this.sysUserService.saveBatch(users);
        return Result.success(true);
    }


}
