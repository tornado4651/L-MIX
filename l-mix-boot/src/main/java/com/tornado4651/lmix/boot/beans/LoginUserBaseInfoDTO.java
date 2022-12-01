package com.tornado4651.lmix.boot.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tornado4651.lmix.boot.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserBaseInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像路径
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户角色列表
     */
    private List<Role> roles;
    /**
     * 性别（0女；1男）
     */
    private Byte gender;
    /**
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 用户状态
     */
    private Short status;
}
