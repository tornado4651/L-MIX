package com.tornado4651.lmix.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
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
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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

}

