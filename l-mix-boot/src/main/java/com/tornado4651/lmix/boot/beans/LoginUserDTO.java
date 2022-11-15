package com.tornado4651.lmix.boot.beans;

import lombok.Data;

@Data
public class LoginUserDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
