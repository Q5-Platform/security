package com.codingapi.model;

import com.lorne.core.framework.utils.config.ConfigUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by houcunlu on 2017/8/18.
 */
public class Config {


    public static List<String> noToken = null;

    public static String  contextPath = "";


    static {
        noToken = new ArrayList<>();
        String [] notoken  =  ConfigUtils.getStringArrayValue("noToken.properties","url");
        noToken = Arrays.asList(notoken);

        contextPath =  ConfigUtils.getString("application.properties" , "server.contextPath");
    }




}
