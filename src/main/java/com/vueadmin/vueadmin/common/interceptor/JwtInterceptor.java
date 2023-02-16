package com.vueadmin.vueadmin.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vueadmin.vueadmin.common.Constants;
import com.vueadmin.vueadmin.exception.ServiceException;
import com.vueadmin.vueadmin.system.entity.SysUser;
import com.vueadmin.vueadmin.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_401, "无token，请重新登录！");
        }
        //获取token中的userID
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTException e) {
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录！");
        }
        //根据token中的userId查询数据库
        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            throw new ServiceException(Constants.CODE_401, "用户不存在，请重新登录！");
        }
        JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录！");
        }
        return true;
    }
}

