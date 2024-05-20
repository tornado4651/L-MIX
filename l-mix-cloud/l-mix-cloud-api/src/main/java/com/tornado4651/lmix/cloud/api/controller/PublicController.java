package com.tornado4651.lmix.cloud.api.controller;

import com.tornado4651.lmix.cloud.api.feign.AdminFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * hello接口
 * @author tornado4651
 * @date 2023/6/21 16:33
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    @Resource
    private AdminFeign adminFeign;

    @GetMapping("/publicInfo")
    public String publicInfo(){
        return adminFeign.publicInfo();
    }
}
