package com.tornado4651.lmix.cloud.admin.holder;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.tornado4651.lmix.cloud.common.constants.AuthConstant;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息
 */
@Component
public class LoginUserHolder {

    public UserDTO getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader(AuthConstant.AUTHORIZATION_INFO_HEADER);
        JSONObject userJsonObject = JSONObject.parseObject(userStr);
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(userJsonObject, userDTO);
        return userDTO;
    }
}
