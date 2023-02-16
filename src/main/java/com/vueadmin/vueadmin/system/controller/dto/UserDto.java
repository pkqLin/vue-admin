package com.vueadmin.vueadmin.system.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

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
}
