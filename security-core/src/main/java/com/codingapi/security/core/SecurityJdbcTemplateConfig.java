package com.codingapi.security.core;

import com.lorne.mysql.framework.dao.impl.JdbcTemplateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * create by lorne on 2017/8/17
 */
@Configuration
public class SecurityJdbcTemplateConfig {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public JdbcTemplateProxy jdbcTemplateProxy() {
        JdbcTemplateProxy jdbcTemplateProxy = new JdbcTemplateProxy();
        jdbcTemplateProxy.setJdbcTemplate(jdbcTemplate);
        return jdbcTemplateProxy;
    }

}