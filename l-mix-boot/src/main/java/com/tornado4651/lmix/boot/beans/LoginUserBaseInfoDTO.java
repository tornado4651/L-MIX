package com.tornado4651.lmix.boot.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserBaseInfoDTO {
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
    private String nickName;
    /**
     * 性别（0女；1男）
     */
    private Byte gender;
    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 电话
     */
    private String telephone;
}
