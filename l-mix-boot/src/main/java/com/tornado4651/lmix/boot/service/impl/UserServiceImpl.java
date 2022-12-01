package com.tornado4651.lmix.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tornado4651.lmix.boot.beans.LoginUserBaseInfoDTO;
import com.tornado4651.lmix.boot.beans.UserDetail;
import com.tornado4651.lmix.boot.common.RedisConstants;
import com.tornado4651.lmix.boot.domain.User;
import com.tornado4651.lmix.boot.exception.UserException;
import com.tornado4651.lmix.boot.mapper.UserMapper;
import com.tornado4651.lmix.boot.service.UserService;
import com.tornado4651.lmix.boot.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
* @author tornado4651
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-11-22 14:33:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(token);
        if(authenticate == null){
            throw new UserException("登陆失败！");
        }
        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        Integer userId = userDetail.getUser().getId();
        String jwt = JwtUtil.createJWT(userId.toString());
        redisTemplate.opsForValue().set(RedisConstants.LOGIN_USER_PREFIX+userId, userDetail);
        return jwt;
    }

    @Override
    public LoginUserBaseInfoDTO getLoginUserBaseInfo(String token) {
        String userId = "";
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取登陆用户信息失败！Token错误！");
        }
        Object data = redisTemplate.opsForValue().get(RedisConstants.LOGIN_USER_PREFIX + userId);
        if(data == null){
            return null;
        }
        UserDetail userDetail = (UserDetail) data;
        User user = userDetail.getUser();
        LoginUserBaseInfoDTO userBaseInfoDTO = new LoginUserBaseInfoDTO();
        BeanUtils.copyProperties(user, userBaseInfoDTO);
        userBaseInfoDTO.setRoles(userDetail.getRoles());
        return userBaseInfoDTO;
    }

    @Override
    public void logout(String token) {
        String userId;
        try {
            Claims claim = JwtUtil.parseJWT(token);
            userId = claim.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException("Token错误，退出失败！");
        }
        redisTemplate.delete(RedisConstants.LOGIN_USER_PREFIX+userId);
    }
}




