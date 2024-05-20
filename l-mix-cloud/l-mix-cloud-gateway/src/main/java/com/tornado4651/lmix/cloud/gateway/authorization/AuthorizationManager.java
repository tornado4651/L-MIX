package com.tornado4651.lmix.cloud.gateway.authorization;

import com.tornado4651.lmix.cloud.gateway.config.secure.AuthUrlRole;
import com.tornado4651.lmix.cloud.gateway.config.secure.SecureConfig;
import com.tornado4651.lmix.cloud.gateway.constant.AuthConstant;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 鉴权管理器
 * 根据用户角色判断当前访问的URI是否被授权
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final Map<String,List<String>> uriAndRoles = new HashMap<>();


    @Resource
    private SecureConfig secureConfig;


    @PostConstruct
    private void init() {
        List<AuthUrlRole> authUrlRoles = secureConfig.getAuthUrlRoles();
        for (AuthUrlRole authUrlRole : authUrlRoles) {
            uriAndRoles.put(authUrlRole.getUri(), authUrlRole.getRoles());
        }
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        //从Redis中获取当前路径可访问角色列表
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        List<String> authorities = uriAndRoles.get(uri.getPath());
        if(authorities == null || authorities.isEmpty()) {
            authorities = new ArrayList<>();
        }
        authorities = authorities.stream().map(i -> AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
