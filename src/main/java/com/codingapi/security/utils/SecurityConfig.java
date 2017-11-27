package com.codingapi.security.utils;

import java.util.List;

/**
 * create by lorne on 2017/11/27
 */
public class SecurityConfig {


    private String model;

    private String dbUrl;

    private String dbUsername;

    private String dbPassword;

    private List<String> alowUrls;


    protected SecurityConfig(){

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public List<String> getAlowUrls() {
        return alowUrls;
    }

    public void setAlowUrls(List<String> alowUrls) {
        this.alowUrls = alowUrls;
    }
}
