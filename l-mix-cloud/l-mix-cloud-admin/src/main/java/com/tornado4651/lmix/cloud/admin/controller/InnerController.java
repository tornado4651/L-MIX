package com.tornado4651.lmix.cloud.admin.controller;

import com.tornado4651.lmix.cloud.admin.service.UserService;
import com.tornado4651.lmix.common.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 微服务内部接口
 * @author tornado4651
 */
@RestController
@RequestMapping("/inner")
public class InnerController {

    @Resource
    private UserService userService;

    @GetMapping("userInfo")
    public UserDTO getUserInfo(@RequestParam String username){
        return userService.getLoginUserInfo(username);
    }
}
