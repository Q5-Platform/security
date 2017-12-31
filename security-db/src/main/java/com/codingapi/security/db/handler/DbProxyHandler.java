package com.codingapi.security.db.handler;

import com.lorne.core.framework.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by lorne on 2017/12/7
 */
public interface DbProxyHandler {

    void changeDb(String url)throws ServiceException;

    void changeDb(Object handler, HttpServletRequest request, HttpServletResponse response);
}
