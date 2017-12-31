package com.condingapi.security.helper;

import com.codingapi.filter.zuul.helper.FilterZuulCheckHelper;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/12/31
 */
@Service
public class MyFilterZuulCheckHelper implements FilterZuulCheckHelper {

    @Override
    public boolean needUrlTokenCheck(String s) {
        return false;
    }

    @Override
    public boolean needUrlVerifyCheck(String s) {
        return false;
    }
}
