package com.tornado4651.lmix.boot.controller;

import com.tornado4651.lmix.boot.beans.LoginUserBaseInfoDTO;
import com.tornado4651.lmix.boot.beans.LoginUserDTO;
import com.tornado4651.lmix.boot.beans.Response;
import com.tornado4651.lmix.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public Response login(@RequestBody LoginUserDTO userDTO){
        String token = userService.login(userDTO.getUsername(), userDTO.getPassword());
        return Response.success("登录成功！", token);
    }

    @GetMapping("/user/loginInfo")
    public Response getLoginInfo(HttpServletRequest request){
        String token = request.getHeader("L-Token");
        LoginUserBaseInfoDTO loginUserBaseInfo = userService.getLoginUserBaseInfo(token);
        return Response.success(loginUserBaseInfo);
    }

    @PostMapping("/user/logout")
    public Response logout(HttpServletRequest request){
        String token = request.getHeader("L-Token");
        userService.logout(token);
        return Response.success();
    }
}
