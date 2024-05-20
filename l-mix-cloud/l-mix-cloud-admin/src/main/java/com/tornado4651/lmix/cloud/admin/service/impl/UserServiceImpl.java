package com.tornado4651.lmix.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tornado4651.lmix.cloud.admin.domain.User;
import com.tornado4651.lmix.cloud.admin.mapper.UserMapper;
import com.tornado4651.lmix.cloud.admin.service.UserService;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
* @author tornado4651
* @description 针对表【a_user】的数据库操作Service实现
* @createDate 2024-05-15 14:17:48
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public UserDTO getLoginUserInfo(String username) {
       UserDTO userDTO = baseMapper.getLoginUserInfo(username);
       userDTO.setRoles(Collections.singletonList("USER")); // TODO 暂时写死，后续从数据库中获取
       return userDTO;
    }
}




