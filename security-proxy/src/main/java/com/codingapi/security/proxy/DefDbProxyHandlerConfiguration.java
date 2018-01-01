package com.codingapi.security.proxy;

import com.codingapi.security.proxy.db.handler.DbProxyHandler;
import com.codingapi.security.proxy.db.handler.def.DefDbProxyHandler;
import org.springframework.context.annotation.Bean;

/**
 * create by lorne on 2017/8/17
 */
public class DefDbProxyHandlerConfiguration {

    @Bean
    public DbProxyHandler dbProxyHandler(){
        return new DefDbProxyHandler();
    }

}