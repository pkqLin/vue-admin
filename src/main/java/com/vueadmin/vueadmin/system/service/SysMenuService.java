package com.vueadmin.vueadmin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vueadmin.vueadmin.system.entity.SysMenu;

import java.util.List;

/**
 * (SysMenu)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 15:25:36
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findMenus(String name);


}

