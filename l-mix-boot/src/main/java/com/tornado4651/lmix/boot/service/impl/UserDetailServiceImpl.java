package com.tornado4651.lmix.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tornado4651.lmix.boot.beans.UserDetail;
import com.tornado4651.lmix.boot.domain.Role;
import com.tornado4651.lmix.boot.domain.User;
import com.tornado4651.lmix.boot.exception.UserException;
import com.tornado4651.lmix.boot.mapper.RoleMapper;
import com.tornado4651.lmix.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if(user == null){
            throw new UserException("用户不存在！");
        }
        List<Role> roles = roleMapper.getAllRoles(user.getId());
        return new UserDetail(user, roles);
    }
}
