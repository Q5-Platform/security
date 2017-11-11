package com.lorne.interceptor;

import com.lorne.filter.*;
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



    @Bean
    public Interceptor Interceptor() {
        return new Interceptor();
    }


    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(Interceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

}
