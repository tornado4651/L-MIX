package com.tornado4651.lmix.data.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.tornado4651.lmix.data.mybatis.intercepter.SqlInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tornado4651
 * @date 2024/5/20 18:04
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 注册分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInnerInterceptor.setOverflow(false);
        //设置最大单页限制数量，默认 500 条，-1 最大999条
        paginationInnerInterceptor.setMaxLimit(-1L);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;
    }

    /**
     * 注册自定义日志插件
     * @return
     */
    @Bean
    public SqlInterceptor sqlInterceptor() {
        return new SqlInterceptor();
    }
}
