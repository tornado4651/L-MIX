package com.tornado4651.lmix.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tornado4651.lmix.cloud.admin.domain.User;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;

/**
* @author tornado4651
* @description 针对表【a_user】的数据库操作Service
* @createDate 2024-05-15 14:17:48
*/
public interface UserService extends IService<User> {

    /**
     * 根据用户名获取当前登录用户信息
     * @param username 登录用户名
     * @return
     */
    UserDTO getLoginUserInfo(String username);


    /**
     *
     * @param username 用户名
     * @param password 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(String username, String password, String newPassword);
}
