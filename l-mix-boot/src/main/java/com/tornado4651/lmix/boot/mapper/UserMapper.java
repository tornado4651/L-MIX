package com.tornado4651.lmix.boot.mapper;

import com.tornado4651.lmix.boot.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author tornado4651
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-11-16 11:24:46
* @Entity com.tornado4651.lmix.boot.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




