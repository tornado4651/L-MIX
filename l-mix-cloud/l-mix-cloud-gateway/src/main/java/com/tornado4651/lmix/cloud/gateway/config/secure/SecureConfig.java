package com.tornado4651.lmix.cloud.gateway.config.secure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义URI所需权限与URI白名单
 * @author tornado4651
 */
@Data
@Component
@ConfigurationProperties(prefix = "lmix.secure")
public class SecureConfig {

    /**
     * 不校验的路径（对外公开）
     */
    private Ignore ignore;

    /**
     * 访问路径与所需角色
     */
    private List<AuthUrlRole> authUrlRoles;

}
