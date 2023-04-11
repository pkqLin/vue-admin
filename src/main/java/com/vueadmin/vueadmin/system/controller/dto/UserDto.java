package com.vueadmin.vueadmin.system.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vueadmin.vueadmin.system.entity.SysMenu;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 接收前端请求的参数
 */
@Data
public class UserDto {
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像url
     */
    private String avatarUrl;
    private String token;
    private String role;

    private List<SysMenu> menus;
}
