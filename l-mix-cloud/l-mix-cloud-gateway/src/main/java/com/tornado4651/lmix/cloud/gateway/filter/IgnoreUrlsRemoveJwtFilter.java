package com.tornado4651.lmix.cloud.gateway.filter;

import com.tornado4651.lmix.cloud.common.constants.AuthConstant;
import com.tornado4651.lmix.cloud.gateway.config.secure.SecureConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * 白名单路径访问时需要移除JWT请求头
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {
    @Autowired
    private SecureConfig secureConfig;

    private final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        //白名单路径移除JWT请求头
        List<String> ignoreUrls = secureConfig.getIgnore().getUris();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                exchange = exchange.mutate().request(r->r.header(AuthConstant.AUTHORIZATION_HEADER, "")).build();
            }
        }
        return chain.filter(exchange);
    }
}
