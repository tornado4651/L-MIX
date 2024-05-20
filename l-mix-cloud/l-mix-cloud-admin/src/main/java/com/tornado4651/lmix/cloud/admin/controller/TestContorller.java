package com.tornado4651.lmix.cloud.admin.controller;

import com.tornado4651.lmix.cloud.admin.holder.LoginUserHolder;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 * @author tornado4651
 * @date 2023/6/21 15:33
 */
@RestController
@RequestMapping("test")
public class TestContorller {

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }

    @GetMapping("/adminInfo")
    public String adminInfo() {
        return "admin 才能访问的信息";
    }

    @GetMapping("/publicInfo")
    public String publicInfo() {
        return "公众 都可以访问的信息";
    }
}
