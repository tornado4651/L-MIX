package com.tornado4651.lmix.boot.filter;

import com.tornado4651.lmix.boot.beans.UserDetail;
import com.tornado4651.lmix.boot.common.RedisConstants;
import com.tornado4651.lmix.boot.common.SystemConstants;
import com.tornado4651.lmix.boot.exception.LoginException;
import com.tornado4651.lmix.boot.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter implements Filter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader(SystemConstants.TOKEN_NAME);
        if (token == null || token.isEmpty()) {
            // 放行交给Spring Security处理
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Token 错误");
        }
        //从redis中获取用户信息
        Object o = redisTemplate.opsForValue().get(RedisConstants.LOGIN_USER_PREFIX + userId);
        UserDetail userDetail = (UserDetail) o;
        if(Objects.isNull(userDetail)){
            throw new LoginException("用户登录超时，请重新登录！");
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetail,null, userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
