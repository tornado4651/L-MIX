package com.tornado4651.lmix.cloud.auth.holder;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.tornado4651.lmix.common.constants.AuthConstant;
import com.tornado4651.lmix.common.dto.UserDTO;
import com.tornado4651.lmix.common.exception.ErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 获取登录用户信息
 */
public class LoginUserHolder {

    public static UserDTO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String loginUserInfoStr = request.getHeader(AuthConstant.AUTHORIZATION_INFO_HEADER);
        if (loginUserInfoStr == null) {
            throw new ErrorException("登录用户信息解析错误！");
        }
        String loginUserInfoJsonStr = new String(Base64.getDecoder().decode(loginUserInfoStr), StandardCharsets.UTF_8);
        JSONObject loginUserInfo = JSONObject.parseObject(loginUserInfoJsonStr);
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(loginUserInfo, userDTO);
        return userDTO;
    }
}
