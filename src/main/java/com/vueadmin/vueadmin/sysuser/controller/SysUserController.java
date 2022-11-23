package com.vueadmin.vueadmin.sysuser.controller;

import com.vueadmin.vueadmin.sysuser.entity.SysUser;
import com.vueadmin.vueadmin.sysuser.mapper.SysUserMapper;
import com.vueadmin.vueadmin.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Autowired
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

    @PostMapping("insert")
    public SysUser insert(@RequestBody SysUser sysUser) {
        this.sysUserService.insert(sysUser);
        return sysUser;
    }



}
