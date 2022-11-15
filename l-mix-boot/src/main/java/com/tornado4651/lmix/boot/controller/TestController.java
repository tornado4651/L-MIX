package com.tornado4651.lmix.boot.controller;

import com.tornado4651.lmix.boot.beans.LoginUserBaseInfoDTO;
import com.tornado4651.lmix.boot.beans.LoginUserDTO;
import com.tornado4651.lmix.boot.beans.Response;
import com.tornado4651.lmix.boot.common.ResponseConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class TestController {

    @PostMapping("/user/login")
    public Response login(@RequestBody LoginUserDTO userDTO){
        if(userDTO.getUsername().equals("admin") && userDTO.getPassword().equals("123456")){
            return Response.success("token-123456");
        }else{
            return Response.failed("登陆失败！");
        }
    }

    @GetMapping("/user/loginInfo")
    public Response getLoginInfo(HttpServletRequest request){
        String token = request.getHeader("L-Token");
        if("token-123456".equals(token)){
            LoginUserBaseInfoDTO userBaseInfoDTO = LoginUserBaseInfoDTO.builder()
                    .username("admin")
                    .nickName("超级管理员")
                    .avatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                    .birthday("1996-08-31")
                    .gender((byte) 1)
                    .telephone("1388888888")
                    .build();
            return Response.success(userBaseInfoDTO);
        }else{
            return Response.failed(ResponseConstants.LOGIN_FAILED_CODE,"登陆用户信息获取失败", null);
        }
    }

    @PostMapping("/user/logout")
    public Response logout(HttpServletRequest request){
        String token = request.getHeader("L-Token");
        log.info("退出登陆：token："+token);
        return Response.success();
    }
}
