package com.codingapi.security.proxy;

import com.codingapi.filter.core.interceptor.handler.FilterInterceptor;
import com.codingapi.security.proxy.service.FilterInterceptorImpl;
import org.springframework.context.annotation.Bean;

/**
 * create by lorne on 2018/1/1
 */
public class FilterInterceptorConfiguration {

   @Bean
   public FilterInterceptor filterInterceptor(){
       return new FilterInterceptorImpl();
   }
}
