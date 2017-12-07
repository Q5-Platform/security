package com.codingapi.security.interceptor;

import com.codingapi.security.db.service.DbChangeService;
import com.codingapi.security.db.service.impl.DefaultDbChangeService;
import com.codingapi.security.redis.RedisHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by houcunlu on 2017/8/16.
 */
@Component
public class DbChangeInterceptor implements  HandlerInterceptor {





    @Autowired
    private ApplicationContext spring;

    private Logger logger = LoggerFactory.getLogger(DbChangeInterceptor.class);

    private DefaultDbChangeService defaultDbChangeService = new DefaultDbChangeService();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        String  url = request.getRequestURI();

        DbChangeService dbChangeService;
        try {
            dbChangeService = spring.getBean(DbChangeService.class);
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            dbChangeService = defaultDbChangeService;
        }

        dbChangeService.changeDb(url);
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
