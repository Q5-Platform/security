package com.codingapi.security.interceptor;


/**
 * create by lorne on 2017/11/8
 */
public class DbNameLocal {


    private final static ThreadLocal<DbNameLocal> currentLocal = new ThreadLocal<DbNameLocal>();


    public static DbNameLocal current() {
        return currentLocal.get();
    }

    public static void setCurrent(DbNameLocal current) {
        currentLocal.set(current);
    }

    private String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
