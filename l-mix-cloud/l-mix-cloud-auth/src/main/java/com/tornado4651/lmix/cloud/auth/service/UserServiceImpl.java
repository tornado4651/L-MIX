package com.tornado4651.lmix.cloud.auth.service;

import com.tornado4651.lmix.cloud.auth.constant.MessageConstant;
import com.tornado4651.lmix.cloud.auth.domain.SecurityUserDetial;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import com.tornado4651.lmix.cloud.auth.feign.AdminFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Spring Security 用户登录管理认证
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminFeign adminFeign;

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserDTO userDTO = adminFeign.getUserInfo(username);

        if (userDTO == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        SecurityUserDetial securityUserDetial = new SecurityUserDetial(userDTO);
        if (!securityUserDetial.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUserDetial.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUserDetial.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUserDetial.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUserDetial;
    }

}
