package com.tornado4651.lmix.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tornado4651.lmix.boot.beans.LoginUserBaseInfoDTO;
import com.tornado4651.lmix.boot.domain.User;

public interface UserService  extends IService<User> {

    /**
     * 用户是否登陆成功
     */
    String login(String username, String password);

    LoginUserBaseInfoDTO getLoginUserBaseInfo(String token);

    void logout(String token);
}
