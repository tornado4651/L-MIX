package com.tornado4651.lmix.cloud.admin.controller;

import com.tornado4651.lmix.cloud.admin.feign.ApiFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 * @author tornado4651
 * @date 2023/6/21 15:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestContorller {

    private final ApiFeign apiFeign;

    @GetMapping("hello")
    public String hello(@RequestParam("username") String username){
        if (ObjectUtils.isEmpty(username)){
            return "hello";
        }
        return "hello, "+username;
    }

    @GetMapping("apiFeign")
    public String apiFeign(@RequestParam("username") String username){
        return apiFeign.test(username);
    }
}
