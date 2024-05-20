package com.tornado4651.lmix.cloud.gateway.config.secure;

import lombok.Data;

import java.util.List;

/**
 * 自定义URI白名单
 * @author tornado4651
 * @date 2024/5/17 11:05
 */
@Data
public class Ignore {
    /**
     * 白名单URI列表
     */
    private List<String> uris;
}
