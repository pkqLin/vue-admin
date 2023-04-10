package com.vueadmin.vueadmin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vueadmin.vueadmin.system.entity.SysRole;

import java.util.List;


/**
 * (SysRole)表服务接口
 *
 * @author makejava
 * @since 2023-04-10 14:14:34
 */
public interface SysRoleService extends IService<SysRole> {
    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);

}

