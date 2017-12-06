package com.codingapi.security;

import com.codingapi.security.filter.PostFilter;
import com.codingapi.security.filter.PreRequestLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by lorne on 2017/12/6
 */
@Configuration
public class SecurityFilterConfig {



    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

    @Bean
    public PostFilter PostFilter() {
        return new PostFilter();
    }

}
