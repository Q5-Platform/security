package com.codingapi.security.controller;

import com.codingapi.security.DbConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lorne on 2017/11/9.
 */
@RestController
@RequestMapping("/db")
public class DbController {


    /**
     * 重新加载Db链接
     */
    @RequestMapping( value= "/refresh" , method = {RequestMethod.POST} )
    public boolean refresh( ){
        DbConfiguration dsc = new DbConfiguration();
        dsc.dataSource();
        return true;
    }



}
