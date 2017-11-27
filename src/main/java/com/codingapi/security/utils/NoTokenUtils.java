package com.codingapi.security.utils;

import com.lorne.core.framework.utils.config.ConfigUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by houcunlu on 2017/8/18.
 */
public class NoTokenUtils {

    public static List<String> urls;


    static {
        urls = new ArrayList<>();
        String [] notoken  =  ConfigUtils.getStringArrayValue("noToken.properties","url");
        urls = Arrays.asList(notoken);
    }




}
