package com.tornado4651.lmix.cloud.admin.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tornado4651.lmix.cloud.admin.domain.User;
import com.tornado4651.lmix.cloud.admin.mapper.UserMapper;
import com.tornado4651.lmix.cloud.admin.service.UserService;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import com.tornado4651.lmix.cloud.common.exception.AlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author tornado4651
* @description 针对表【a_user】的数据库操作Service实现
* @createDate 2024-05-15 14:17:48
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public UserDTO getLoginUserInfo(String username) {
        if(StringUtils.isEmpty(username)){
            throw new AlertException("用户名不能为空！");
        }
        return baseMapper.getLoginUserInfo(username);
//       UserDTO userRecord = baseMapper.getLoginUserInfo(username);
//       if (userRecord == null) {
//           return new UserDTO();
//       }
//       UserDTO userDTO = new UserDTO();
//       BeanUtil.copyProperties(userRecord, userDTO);
//       return userDTO;
    }

    @Override
    @Transactional(rollbackFor = AlertException.class)
    public void updatePassword(String username, String password, String newPassword) {
        UserDTO loginUserInfo = baseMapper.getLoginUserInfo(username);
        if(loginUserInfo == null) {
            throw new AlertException("用户信息【"+username+"】不存在！");
        }
        if(!loginUserInfo.getPassword().equals(password)) {
            throw new AlertException("密码不正确！");
        }
        int update = baseMapper.update(null, new LambdaUpdateWrapper<User>()
                .eq(User::getId, loginUserInfo.getId())
                .set(User::getPassword, newPassword));
//                .setSql("password = HEX(AES_ENCRYPT('" + newPassword + "', 'lmix'))"));
        if (update != 1){
            log.error("用户【{}】更新密码失败！原密码：{} 旧密码：{}",username, password, newPassword);
            throw new AlertException("密码更新失败！");
        }
    }
}




