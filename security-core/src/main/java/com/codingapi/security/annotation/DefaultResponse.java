package com.codingapi.security.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * create by lorne on 2017/12/7
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DefaultResponse {

}
