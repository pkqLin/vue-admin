package com.vueadmin.vueadmin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vueadmin.vueadmin.system.entity.SysDict;
import com.vueadmin.vueadmin.system.entity.SysUser;

/**
 * (SysDict)表服务接口
 *
 * @author makejava
 * @since 2023-04-11 09:44:30
 */
public interface SysDictService extends IService<SysDict> {

    Page<SysUser> findPage(Page<SysDict> objectPage, String name);



}

