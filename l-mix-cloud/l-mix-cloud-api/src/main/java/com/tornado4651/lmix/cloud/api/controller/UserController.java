package com.tornado4651.lmix.cloud.api.controller;

import com.tornado4651.lmix.cloud.api.feign.AdminFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello接口
 * @author tornado4651
 * @date 2023/6/21 16:33
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AdminFeign adminFeign;

    @GetMapping("/currentUser")
    public String currentUser(){
        return adminFeign.currentUser();
    }

    @GetMapping("/adminInfo")
    public String adminInfo(){
        return adminFeign.adminInfo();
    }

    @GetMapping("/publicInfo")
    public String publicInfo(){
        return adminFeign.publicInfo();
    }
}
