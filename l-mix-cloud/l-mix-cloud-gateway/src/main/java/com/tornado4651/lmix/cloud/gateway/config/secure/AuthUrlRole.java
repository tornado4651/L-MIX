package com.tornado4651.lmix.cloud.gateway.config.secure;

import lombok.Data;

import java.util.List;

/**
 * 自定义角色权限校验路径
 * @author tornado4651
 * @date 2024/5/17 11:02
 */
@Data
public class AuthUrlRole {
    private String uri;
    private List<String> roles;
}
