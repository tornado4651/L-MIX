package com.tornado4651.lmix.cloud.admin.controller;

import com.tornado4651.lmix.cloud.admin.holder.LoginUserHolder;
import com.tornado4651.lmix.cloud.common.bean.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 * @author tornado4651
 * @date 2023/6/21 15:33
 */
@RestController
@RequestMapping("user")
public class UserContorller {

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/currentUser")
    public CommonResult currentUser() {
        return CommonResult.success(loginUserHolder.getCurrentUser());
    }

    @GetMapping("/adminInfo")
    public CommonResult adminInfo() {
        return CommonResult.success("admin 才能访问的信息");
    }

    @GetMapping("/publicInfo")
    public CommonResult publicInfo() {
        return CommonResult.success("公众 都可以访问的信息");
    }

    @PostMapping("/logout")
    public CommonResult logout() {
        return CommonResult.success();
    }

}
