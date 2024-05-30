package com.tornado4651.lmix.cloud.auth.domain;

import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录用户信息
 */
@Data
public class SecurityUserDetail implements UserDetails {

    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 是否允许
     */
    private Boolean enabled;
    /**
     * 用户状态（0：锁定；1：正常; 3：暂时封禁）
     */
    private Integer status;
    /**
     * 用户角色
     */
    private List<String> roles;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUserDetail() {

    }

    public SecurityUserDetail(UserDTO userDTO) {
        this.setId(userDTO.getId());
        this.setUsername(userDTO.getUsername());
        this.setPassword(userDTO.getPassword());
        this.setNickname(userDTO.getNickname());
        this.setStatus(userDTO.getStatus());
        this.setRoles(userDTO.getRoles());
        this.setEnabled(userDTO.getStatus() == 1);
        if (userDTO.getRoles() != null) {
            authorities = new ArrayList<>();
            userDTO.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
