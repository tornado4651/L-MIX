package com.tornado4651.lmix.cloud.api.controller;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello接口
 * @author tornado4651
 * @date 2023/6/21 16:33
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("test")
    public String test(@RequestParam("username") String username){
        if(ObjectUtils.isEmpty(username)){
            return "hello,world!";
        }
        return "hello, "+username;
    }
}
