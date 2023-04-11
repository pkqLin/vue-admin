package com.vueadmin.vueadmin.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SysDict)表实体类
 *
 * @author makejava
 * @since 2023-04-11 09:44:30
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="sys_dict")
public class SysDict implements Serializable  {

    private static final long serialVersionUID = 1L;

    //名称
    private String name;
    //内容
    private String value;
    //类型
    private String type;


}

