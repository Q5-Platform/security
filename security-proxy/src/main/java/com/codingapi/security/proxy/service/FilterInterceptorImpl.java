package com.codingapi.security.proxy.service;

import com.codingapi.filter.core.interceptor.handler.FilterInterceptor;
import com.codingapi.security.proxy.db.handler.DbProxyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by lorne on 2017/8/16.
 */
public class FilterInterceptorImpl implements FilterInterceptor {


    @Autowired
    private DbProxyHandler dbProxyHandler;


    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration dbProxyInterceptor = registry.addInterceptor(new DbProxyInterceptor(dbProxyHandler));
        // 拦截配置
        dbProxyInterceptor.addPathPatterns("/**");
    }

}
