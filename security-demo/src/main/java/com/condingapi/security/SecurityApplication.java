package com.condingapi.security;

import com.codingapi.security.core.handler.SecurityFilterHandlerConfiguration;
import com.codingapi.security.proxy.DefDbProxyHandlerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy

@Import({DefDbProxyHandlerConfiguration.class,
        SecurityFilterHandlerConfiguration.class})

public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }


}
