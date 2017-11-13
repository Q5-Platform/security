package com.codingapi.security.security.db;

/**
 * create by lorne on 2017/11/8
 */
 class DataSourceLocal {


    private final static ThreadLocal<DataSourceLocal> currentLocal = new ThreadLocal<DataSourceLocal>();


    static DataSourceLocal current() {
        return currentLocal.get();
    }

    static void setCurrent(DataSourceLocal current) {
        currentLocal.set(current);
    }

    private String key;


    String getKey() {
        return key;
    }

    void setKey(String key) {
        this.key = key;
    }
}
