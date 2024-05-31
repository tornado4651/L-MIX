package com.tornado4651.lmix.cloud.admin.holder;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.tornado4651.lmix.cloud.common.constants.AuthConstant;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 获取登录用户信息
 */
@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String loginUserInfoStr = request.getHeader(AuthConstant.AUTHORIZATION_INFO_HEADER);
        String loginUserInfoJsonStr = new String(Base64.getDecoder().decode(loginUserInfoStr), StandardCharsets.UTF_8);
        JSONObject loginUserInfo = JSONObject.parseObject(loginUserInfoJsonStr);
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(loginUserInfo, userDTO);
        return userDTO;
    }
}
