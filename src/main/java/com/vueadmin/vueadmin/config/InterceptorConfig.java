package com.vueadmin.vueadmin.config;

import com.vueadmin.vueadmin.common.interceptor.JwtInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/sysUser/login","/sysUser/register","/**/export","/**/import","/file/**","/echarts/**");
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
