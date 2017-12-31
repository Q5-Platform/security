package com.codingapi.security.db;


import com.lorne.core.framework.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

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

    private final  static org.slf4j.Logger logger = LoggerFactory.getLogger(DataSourceProxy.class);

    private static Map<String,DataSource> dataSources;

    private static String defaultKey = "default";

    public static void setDefaultKey(String key) {
        defaultKey = key;
    }

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
        logger.info("add key -> " +key);
        dataSources.put(key,dataSource);
    }


    private String getKey(){
        if(DataSourceLocal.current()==null){
            return defaultKey;
        }
        String key = DataSourceLocal.current().getKey();
        if(StringUtils.isEmpty(key)){
            key = defaultKey;
        }
        return key;
    }

    @Override
    public Connection getConnection() throws SQLException {
        String key =getKey();
        logger.info("current key -> " +key);
        return dataSources.get(key).getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        String key =getKey();
        logger.info("current key -> " +key);
        return dataSources.get(key).getConnection(username, password);
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
