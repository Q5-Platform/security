package com.codingapi.security.proxy.db.handler.def;

import com.codingapi.security.proxy.db.DataSourceLocal;
import com.codingapi.security.proxy.db.DataSourceProxy;
import com.codingapi.security.proxy.db.handler.DbProxyHandler;
import com.codingapi.security.proxy.utils.SecurityConfigUtils;
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
