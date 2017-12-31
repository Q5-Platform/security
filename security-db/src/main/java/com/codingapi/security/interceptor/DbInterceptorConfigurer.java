package com.codingapi.security.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lorne on 2017/8/16.
 */
@Configuration
public class DbInterceptorConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public DbProxyInterceptor dbChangeInterceptor() {
        return new DbProxyInterceptor();
    }


    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration dbProxyInterceptor = registry.addInterceptor(dbChangeInterceptor());
        // 拦截配置
        dbProxyInterceptor.addPathPatterns("/**");

    }

}
