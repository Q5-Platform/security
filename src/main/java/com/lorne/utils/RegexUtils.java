package com.lorne.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by houcunlu on 2017/8/22.
 */
public class RegexUtils {



    public static boolean isNumber(String str) {
        return matcher(str, "^[0-9]*[1-9][0-9]*$");
    }

    public static boolean matcher(String source, String regexp) {
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(source);
        return m.matches();
    }



}
