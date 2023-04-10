package com.vueadmin.vueadmin.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SysRole)表实体类
 *
 * @author makejava
 * @since 2023-04-10 14:14:32
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    //id
    private Integer id;
    //权限
    private String name;
    //唯一标识
    private String flag;
    //描述
    private String description;

    }

