package com.codingapi.security.core.handler;

import com.codingapi.filter.core.interceptor.handler.FilterDataResponseHandler;
import com.codingapi.filter.core.interceptor.handler.FilterExceptionHandler;
import com.codingapi.filter.core.interceptor.handler.FilterPreResponseHandler;
import com.codingapi.filter.zuul.handler.self.SelfZuulFilterExceptionHandler;
import com.codingapi.filter.zuul.handler.self.SelfZuulFilterPreResponseHandler;
import org.springframework.context.annotation.Bean;

/**
 * create by lorne on 2017/12/31
 */
public class SecurityFilterHandlerConfiguration {


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
        return new SecurityFilterDataResponseHandler();
    }

}
