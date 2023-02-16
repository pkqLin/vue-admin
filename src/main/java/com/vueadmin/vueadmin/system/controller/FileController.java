package com.vueadmin.vueadmin.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * @Description: 文件上传
     * @Param:
     * @Author: pkqLin
     * @Date: 2023/2/3 15:04
     * @version V1.0
    */
    @PostMapping("/upload")
    public String upload(){
        return "";
    }
}
