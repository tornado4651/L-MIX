package com.tornado4651.lmix.boot.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
}
