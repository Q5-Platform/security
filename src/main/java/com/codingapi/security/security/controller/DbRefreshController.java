package com.codingapi.security.security.controller;

import com.codingapi.security.DataSourceConfig;
import com.codingapi.security.security.ContentController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by houcunlu on 2017/11/9.
 */
@Api(description = "重新加载Db链接")
@RestController
@RequestMapping(ContentController.DBREFRESH)
public class DbRefreshController {


    /**
     * 重新加载Db链接
     */
    @ApiOperation(value="重新加载Db链接")
    @RequestMapping( value= "/refresh" , method = {RequestMethod.POST} )
    public boolean refresh( ){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.dataSource();
        return true;
    }



}
