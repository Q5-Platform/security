package com.lorne.security.db;


import com.lorne.core.framework.exception.ServiceException;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * create by lorne on 2017/11/8
 */


public class DataSourceProxy implements DataSource {

    private static Map<String,DataSource> dataSources;

    public DataSourceProxy() {
        dataSources = new ConcurrentHashMap<String, DataSource>();
    }

    public static boolean containsKey(String key) {
        return dataSources.containsKey(key);
    }

    public static void changeDb(String key) throws ServiceException {
        if(containsKey(key)){
            DataSourceLocal dataSourceLocal = new DataSourceLocal();
            dataSourceLocal.setKey(key);
            DataSourceLocal.setCurrent(dataSourceLocal);
        }else{
            throw new ServiceException("db "+key+" 不存在");
        }
    }

    public static void addDataSource(String key,DataSource dataSource) {
        dataSources.put(key,dataSource);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSources.get(DataSourceLocal.current().getKey()).getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return dataSources.get(DataSourceLocal.current().getKey()).getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return dataSources.get(DataSourceLocal.current().getKey()).getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        dataSources.get(DataSourceLocal.current().getKey()).setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        dataSources.get(DataSourceLocal.current().getKey()).setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return dataSources.get(DataSourceLocal.current().getKey()).getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return dataSources.get(DataSourceLocal.current().getKey()).getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return dataSources.get(DataSourceLocal.current().getKey()).unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return dataSources.get(DataSourceLocal.current().getKey()).isWrapperFor(iface);
    }
}
