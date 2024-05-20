package com.tornado4651.lmix.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tornado4651.lmix.cloud.admin.domain.User;
import com.tornado4651.lmix.cloud.common.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author tornado4651
* @description 针对表【a_user】的数据库操作Mapper
* @createDate 2024-05-15 14:17:48
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据登录用户名查询用户信息、用户角色列表、用户权限列表
     * @param username 登录用户名
     * @return UserDTO封装类
     */
    UserDTO getLoginUserInfo(@Param("username") String username);
}




