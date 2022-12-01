package com.tornado4651.lmix.boot.mapper;

import com.tornado4651.lmix.boot.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author tornado4651
* @description 针对表【role】的数据库操作Mapper
* @createDate 2022-11-22 11:36:58
* @Entity com.tornado4651.lmix.boot.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getAllRoles(Integer userId);
}




