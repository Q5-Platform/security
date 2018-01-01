package com.codingapi.security.core.controller;

import com.codingapi.security.core.service.SUserService;
import com.codingapi.security.core.ContentController;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.core.framework.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/16.
 */
@Api(description = "用户模块")
@RestController
@RequestMapping(ContentController.ADMIN)
public class SAdminController {



    @Autowired
    private SUserService userService;


    /**
     *  登陆
     */
    @ApiOperation(value="登陆")
    @RequestMapping( value= "/login" , method = {RequestMethod.POST} )
    public Map<String,Object> login(
            @ApiParam(value = "{\"userName\": \"账户\",\"userPwd\": \"密码\"}", required = true) @RequestBody String json
    ) throws ServiceException {
        String userName =  getString(json , "userName" ,"");
        String userPwd =  getString(json , "userPwd" ,"");
        return  userService.login( userName  ,userPwd );
    }


    /**
     * 添加用户
     */
    @ApiOperation(value="添加用户")
    @RequestMapping( value= "/addUser" , method = {RequestMethod.POST} )
    public boolean addUser(
            @ApiParam(value = "{\"userName\": \"账户\" , \"userPwd\": \"密码\" , \"photo\": \"头像\" }", required = true) @RequestBody String json
    ) throws ServiceException {
        String userName =   getString (json , "userName" , "");
        String userPwd =   getString (json , "userPwd" , "");
        String photo =   getString (json , "photo" , "");
        return  userService.addUser( userName  ,userPwd , photo);
    }



    /**
     * 修改用户
     */
    @ApiOperation(value="修改用户")
    @RequestMapping( value= "/updateUserById" , method = {RequestMethod.POST})
    public boolean updateUserById(
            @ApiParam(value = "{\"userId\": \"账户id\" , \"userName\": \"账户\" , \"photo\": \"头像\" }", required = true) @RequestBody String json
    ) throws ServiceException {
         String userId =   getString (json , "userId" , "");
         String userName =   getString (json , "userName" , "");
         String photo =   getString (json , "photo" , "");
        return  userService.updateUserById( userId ,  userName   , photo);
    }




    /**
     * 禁用 / 解禁
     */
    @ApiOperation(value="禁用 / 解禁")
    @RequestMapping( value= "/updateEnableByUserId" , method = {RequestMethod.POST})
    public boolean updateEnableByUserId(
            @ApiParam(value = "{\"userId\": \"账户id\" , \"enable\": \"状态\" }", required = true) @RequestBody String json
    ) throws ServiceException {
            String userId =   getString (json , "userId" , "");
            String enable =   getString (json , "enable" , "");
        return  userService.updateEnableByUserId( userId  , enable );
    }




    /**
     * 用户列表
     */
    @ApiOperation(value="用户列表")
    @RequestMapping( value= "/findUserPage" , method = {RequestMethod.POST})
    public Page<Map<String,Object>> findUserPage(
            @ApiParam(value = "{\"offset\": \"当前页\" , \"limit\": \"显示条数\" , \"search\": \"模糊查询\"   }", required = true) @RequestBody String json
    ) throws ServiceException {
         int nowPage =  getInt(json , "offset" , 1);
         int pageSize =  getInt(json , "limit" , 20 );
         String key =  getString(json , "search" ,"");
         nowPage /= pageSize;
         nowPage++;
        return  userService.findUserPage( nowPage  , pageSize , key );
    }




    /**
     * 删除管理员
     */
    @ApiOperation(value="删除管理员")
    @RequestMapping( value= "/deleteUserById" , method = {RequestMethod.POST})
    public boolean deleteUserById(
            @ApiParam(value = "{\"userId\": \"用户id\" }",required = true)  @RequestBody String json
    ) throws ServiceException {
        String userId =  getString(json , "userId" , "");
        return  userService.deleteUserById( userId );
    }




    /**
     * 修改头像
     */
    @ApiOperation(value="修改头像")
    @RequestMapping( value= "/updatePhoto" , method = {RequestMethod.POST})
    public boolean updatePhoto(@ApiParam(value = "用户id",required = true) @RequestParam("userId") String userId , @ApiParam(value = "头像",required = true) @RequestParam("photo") String photo  ) throws ServiceException {
        return  userService.updatePhoto( userId , photo );
    }








    public String getString(String json, String key, String defaultVal) {
        return JsonUtils.getString(json, key, defaultVal);
    }

    public int getInt(String json, String key, int defaultVal) {
        return JsonUtils.getInt(json, key, defaultVal);
    }


}
