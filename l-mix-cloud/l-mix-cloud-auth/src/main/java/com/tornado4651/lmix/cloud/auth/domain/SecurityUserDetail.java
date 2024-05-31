package com.tornado4651.lmix.cloud.auth.domain;

import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 登录用户信息
 */
@Data
public class SecurityUserDetail implements UserDetails {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户密码（明文）
     */
    private String password;
    /**
     * 出生年月
     */
    private Date birthday;
    /**
     * 性别（0女；1男）
     */
    private Byte gender;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户状态（0：锁定；1：正常; 3：暂时封禁）
     */
    private Integer status;
    /**
     * 用户角色
     */
    private List<String> roles;
    /**
     * 用户权限
     */
    private List<String> auths;

    /**
     * 是否激活
     */
    private Boolean enabled;
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
        this.setBirthday(userDTO.getBirthday());
        this.setGender(userDTO.getGender());
        this.setTelephone(userDTO.getTelephone());
        this.setAvatar(userDTO.getAvatar());
        this.setRoles(userDTO.getRoles());
        this.setAuths(userDTO.getAuths());

        this.setEnabled(userDTO.getStatus()!=null && userDTO.getStatus()== 1);
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
