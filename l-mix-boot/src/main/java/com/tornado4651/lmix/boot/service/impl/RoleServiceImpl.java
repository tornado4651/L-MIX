package com.tornado4651.lmix.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tornado4651.lmix.boot.domain.Role;
import com.tornado4651.lmix.boot.mapper.RoleMapper;
import com.tornado4651.lmix.boot.service.RoleService;
import org.springframework.stereotype.Service;

/**
* @author tornado4651
* @description 针对表【role】的数据库操作Service实现
* @createDate 2022-11-22 11:36:58
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




