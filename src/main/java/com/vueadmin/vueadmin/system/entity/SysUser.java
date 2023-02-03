package com.vueadmin.vueadmin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2022-11-23 14:04:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = -34458903758219904L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @JsonIgnore //忽略不展示摸个字段
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * email
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 头像url
     */
    private String avatarUrl;




}
