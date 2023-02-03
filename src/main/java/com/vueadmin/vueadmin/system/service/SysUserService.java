package com.vueadmin.vueadmin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vueadmin.vueadmin.system.controller.dto.UserDto;
import com.vueadmin.vueadmin.system.entity.SysUser;

import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 14:04:59
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Integer id);

    /**
     * @Title:
     * @Description: 查询所有数据
     * @ClassName: path: com.vueadmin.vueadmin.system.service.SysUserService  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/23 16:51
     * @version V1.0
     */
    List<SysUser> queryAll(SysUser sysUser);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser update(SysUser sysUser);

    /* *
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /** 
     * @Description: 查新分页的总数
     * @Param:  
     * @Author: pkqLin
     * @Date: 2023/2/3 13:19
     * @version V1.0
    */ 
    int total(int num);

    Page<SysUser> findPage(Page<SysUser> objectPage, String username);

    UserDto login(UserDto userDto);

    SysUser register(UserDto userDTO);
}
