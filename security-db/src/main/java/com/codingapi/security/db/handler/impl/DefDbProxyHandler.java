package com.codingapi.security.db.handler.impl;

import com.codingapi.security.db.DataSourceLocal;
import com.codingapi.security.db.DataSourceProxy;
import com.codingapi.security.db.handler.DbProxyHandler;
import com.codingapi.security.utils.SecurityConfigUtils;
import com.lorne.core.framework.exception.ServiceException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by lorne on 2017/12/7
 */
public class DefDbProxyHandler implements DbProxyHandler {

    @Override
    public void changeDb(String url) throws ServiceException {
        String dbName = SecurityConfigUtils.getInstance().getDbName(url);
        if(StringUtils.isNotEmpty(dbName)){
            DataSourceLocal dbNameLocal = new DataSourceLocal();
            dbNameLocal.setKey(dbName);
            DataSourceLocal.setCurrent(dbNameLocal);
            DataSourceProxy.changeDb(DataSourceLocal.current().getKey());
        }
    }

    @Override
    public void changeDb(Object handler, HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURI();
        try {
            changeDb(url);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
