package com.condingapi.security;

import com.codingapi.filter.core.interceptor.handler.FilterDataResponseHandler;
import com.codingapi.filter.core.interceptor.handler.FilterExceptionHandler;
import com.codingapi.filter.core.interceptor.handler.FilterPreResponseHandler;
import com.codingapi.filter.zuul.handler.self.SelfZuulFilterDataResponseHandler;
import com.codingapi.filter.zuul.handler.self.SelfZuulFilterExceptionHandler;
import com.codingapi.filter.zuul.handler.self.SelfZuulFilterPreResponseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by lorne on 2017/12/31
 */
@Configuration
public class MyFilterHandlerConfiguration {


    @Bean
    public FilterExceptionHandler filterExceptionHandler() {
        return new SelfZuulFilterExceptionHandler();
    }

    @Bean
    public FilterPreResponseHandler filterPreResponseHandler() {
        return new SelfZuulFilterPreResponseHandler();
    }

    @Bean
    public FilterDataResponseHandler filterDataResponseHandler() {
        return new MyZuulFilterDataResponseHandler();
    }

}
