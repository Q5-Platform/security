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

//
//    public void reloadDataSource() {
//        Map<String, SecurityConfig> securityConfigMap = SecurityConfigUtils.getInstance().getSecurityConfigs();
//        for (String name : securityConfigMap.keySet()) {
//            SecurityConfig securityConfig = securityConfigMap.get(name);
//            DruidDataSource dataSource = new DruidDataSource();
//            dataSource.setUrl(securityConfig.getDbUrl());
//            dataSource.setUsername(securityConfig.getDbUsername());//用户名
//            dataSource.setPassword(securityConfig.getDbPassword());//密码
//            dataSource.setInitialSize(3);
//            dataSource.setMaxActive(5);
//            dataSource.setMinIdle(0);
//            dataSource.setMaxWait(60000);
//            dataSource.setValidationQuery("SELECT 1");
//            dataSource.setTestOnBorrow(false);
//            dataSource.setTestWhileIdle(true);
//            dataSource.setPoolPreparedStatements(false);
//            DataSourceProxy.addDataSource(name, dataSource);
//        }
//    }
//
//
//    @Bean
//    public DataSourceProxy dataSource() {
//        DataSourceProxy dataSourceProxy = new DataSourceProxy();
//        reloadDataSource();
//        return dataSourceProxy;
//    }



}