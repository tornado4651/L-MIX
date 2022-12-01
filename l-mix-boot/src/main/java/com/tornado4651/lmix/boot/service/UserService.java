package com.tornado4651.lmix.boot.service;

import com.tornado4651.lmix.boot.beans.LoginUserBaseInfoDTO;
import com.tornado4651.lmix.boot.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author tornado4651
* @description 针对表【user】的数据库操作Service
* @createDate 2022-11-22 14:33:26
*/
public interface UserService extends IService<User> {

    String login(String username, String password);

    LoginUserBaseInfoDTO getLoginUserBaseInfo(String token);

    void logout(String token);
}
