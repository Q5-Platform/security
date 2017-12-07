package com.condingapi.security;

import com.codingapi.security.db.service.DbChangeService;
import com.codingapi.security.db.service.impl.DefaultDbChangeService;
import com.lorne.core.framework.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by lorne on 2017/12/7
 */
@Configuration
public class DbChangeServiceConfig {

    private Logger logger  = LoggerFactory.getLogger(DbChangeServiceConfig.class);

    @Bean
    public DbChangeService dbChangeService(){
        return new DefaultDbChangeService(){
            @Override
            public void changeDb(String url) throws ServiceException {
                super.changeDb(url);
                logger.info("changeDb - > url:"+url);
            }
        };
    }
}
