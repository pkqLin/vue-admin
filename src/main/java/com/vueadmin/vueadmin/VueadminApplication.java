package com.vueadmin.vueadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;


@SpringBootApplication()
@MapperScan("com.vueadmin.vueadmin")
public class VueadminApplication {
    private  static ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(VueadminApplication.class, args);
    }

}
