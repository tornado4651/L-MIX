package com.tornado4651.lmix.cloud.gateway.authorization;

import cn.hutool.core.convert.Convert;
import com.tornado4651.lmix.cloud.gateway.config.secure.AuthUrlRole;
import com.tornado4651.lmix.cloud.gateway.config.secure.SecureConfig;
import com.tornado4651.lmix.cloud.gateway.constant.AuthConstant;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 鉴权管理器
 * 根据用户角色判断当前访问的URI是否被授权
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    // 授权角色与访问路径关系
    private final Map<String,List<String>> uriAndRoles = new HashMap<>();
    // 白名单
    private final List<String> ignoreUrls = new ArrayList<>();

    private PathMatcher pathMatcher = new AntPathMatcher();

    @Resource
    private SecureConfig secureConfig;


    @PostConstruct
    private void init() {
        List<AuthUrlRole> authUrlRoles = secureConfig.getAuthUrlRoles();
        for (AuthUrlRole authUrlRole : authUrlRoles) {
            uriAndRoles.put(authUrlRole.getUri(), authUrlRole.getRoles());
        }
        ignoreUrls.addAll( secureConfig.getIgnore().getUris() );
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();

        //白名单路径直接放行
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        //对应跨域的预检请求直接放行
        if(request.getMethod()== HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }


        //认证通过且角色匹配的用户可访问当前路径
        Iterator<String> uriIterator = uriAndRoles.keySet().iterator();
        List<String> authorities = new ArrayList<>();
        while (uriIterator.hasNext()) {
            String pattern =  uriIterator.next();
            if (pathMatcher.match(pattern, uri.getPath())) {
                authorities.addAll(Convert.toList(String.class, uriAndRoles.get(pattern)));
            }
        }
        authorities = authorities.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());

        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
//                .map(AuthorizationDecision::new)
                .map(i -> new AuthorizationDecision(true)) // 默认包含权限放行
                .defaultIfEmpty(new AuthorizationDecision(true));
    }

}
