package com.tornado4651.lmix.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tornado4651.lmix.boot.beans.LoginUserBaseInfoDTO;
import com.tornado4651.lmix.boot.common.RedisConstants;
import com.tornado4651.lmix.boot.domain.User;
import com.tornado4651.lmix.boot.exception.UserException;
import com.tornado4651.lmix.boot.mapper.UserMapper;
import com.tornado4651.lmix.boot.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String login(String username, String password){
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getPassword, password));
        if(user != null){
            String token = UUID.randomUUID().toString();
            LoginUserBaseInfoDTO loginUserBaseInfoDTO = new LoginUserBaseInfoDTO();
            BeanUtils.copyProperties(user, loginUserBaseInfoDTO);
            redisTemplate.opsForValue().set(RedisConstants.LOGIN_USER_PREFIX+token, loginUserBaseInfoDTO,2, TimeUnit.HOURS);
            return token;
        }else{
            throw new UserException("登陆失败！请重新登陆！");
        }
    }

    @Override
    public LoginUserBaseInfoDTO getLoginUserBaseInfo(String token) {
        Object data = redisTemplate.opsForValue().get(RedisConstants.LOGIN_USER_PREFIX+token);
        if(data != null && data instanceof LoginUserBaseInfoDTO){
            return (LoginUserBaseInfoDTO) data;
        }else{
            throw new UserException("登陆用户信息获取失败！请重新登陆！");
        }
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
