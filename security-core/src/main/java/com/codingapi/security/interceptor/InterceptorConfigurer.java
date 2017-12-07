package com.codingapi.security.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by houcunlu on 2017/8/16.
 */
@Configuration
public class InterceptorConfigurer extends WebMvcConfigurerAdapter {


    @Value("${server.contextPath}")
    private String contextPath;

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public DbChangeInterceptor dbChangeInterceptor() {
        return new DbChangeInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration dbChangeInterceptor = registry.addInterceptor(dbChangeInterceptor());
        // 拦截配置
        dbChangeInterceptor.addPathPatterns("/**");

        InterceptorRegistration securityInterceptor = registry.addInterceptor(securityInterceptor());

        // 排除配置
        securityInterceptor.excludePathPatterns("/error");

        // 拦截配置
        securityInterceptor.addPathPatterns(contextPath+"/security/**");

    }

}
