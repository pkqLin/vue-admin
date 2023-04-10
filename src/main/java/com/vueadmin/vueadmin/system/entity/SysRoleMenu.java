package com.vueadmin.vueadmin.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SysRoleMenu)表实体类
 *
 * @author makejava
 * @since 2023-04-10 15:54:31
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="sys_role_menu")
public class SysRoleMenu  {
    private static final long serialVersionUID = 1L;
    private Integer roleId;
    
    private Integer menuId;


}

