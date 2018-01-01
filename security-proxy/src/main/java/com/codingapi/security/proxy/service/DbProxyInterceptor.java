package com.codingapi.security.proxy.service;

import com.codingapi.security.proxy.db.handler.DbProxyHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lorne on 2017/8/16.
 */
public class DbProxyInterceptor implements  HandlerInterceptor {


    private DbProxyHandler dbProxyHandler;


    public DbProxyInterceptor(DbProxyHandler dbProxyHandler) {
        this.dbProxyHandler = dbProxyHandler;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        dbProxyHandler.changeDb(handler,request,response);
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
