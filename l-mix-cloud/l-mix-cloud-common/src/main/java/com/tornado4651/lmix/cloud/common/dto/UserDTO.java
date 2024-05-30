package com.tornado4651.lmix.cloud.common.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户信息传输封装类
 */
@Data
public class UserDTO {
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

}

