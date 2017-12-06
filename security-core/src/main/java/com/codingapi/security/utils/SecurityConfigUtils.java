package com.codingapi.security.utils;

import com.lorne.core.framework.utils.config.ConfigHelper;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by lorne on 2017/11/27
 */
public class SecurityConfigUtils {

    private Map<String,SecurityConfig> map = new ConcurrentHashMap<>();


    private static SecurityConfigUtils instane;


    public String getDbName(String path){
        Pattern pattern = Pattern.compile("/db_.*_/");

        Matcher matcher = pattern.matcher(path);

        if(matcher.find()){

            String res = matcher.group();
            res = res.replaceAll("/","");
            res = res.replace("db_","");
            res = res.replaceAll("_","");

            return res;
        }

        return null;
    }

    private SecurityConfigUtils(){
        reloadConfig();
    }

    public SecurityConfig getSecurityConfig(String model){
        return map.get(model);
    }

    public Map<String,SecurityConfig> getSecurityConfigs(){
        return map;
    }

    public void reloadConfig(){
        ConfigHelper configHelper = new ConfigHelper("security.properties");

        String names[] = configHelper.getStringValue("security.name").split(",");

        for(String name:names){
            SecurityConfig securityConfig = new SecurityConfig();

            securityConfig.setDbUrl(configHelper.getStringValue(String.format("%s.datasource.url",name)));
            securityConfig.setDbUsername(configHelper.getStringValue(String.format("%s.datasource.username",name)));//用户名
            securityConfig.setDbPassword(configHelper.getStringValue(String.format("%s.datasource.password",name)));//密码

            String urls[] = configHelper.getStringArrayValue(String.format("%s.allow.url",name));
            securityConfig.setAlowUrls(Arrays.asList(urls));
            map.put(name,securityConfig);
        }
    }


    public static SecurityConfigUtils getInstance() {
        if (instane == null){
            synchronized (SecurityConfigUtils.class){
                if(instane==null){
                    instane = new SecurityConfigUtils();
                }
            }
        }
        return instane;
    }


}
