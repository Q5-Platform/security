package com.codingapi.security.controller;

import com.codingapi.security.ContentController;
import com.codingapi.security.service.SRoleService;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.core.framework.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/21.
 */
@Api(description = "权限管理")
@RestController
@RequestMapping(ContentController.ROLE)
public class SRoleController  {



    @Autowired
    private SRoleService roleService;


    /**
     * 查询所有权限
     * @param
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="查询所有权限")
    @RequestMapping( value= "/findRolePage" , method = {RequestMethod.POST} )
    public Page<Map<String,Object>> findRolePage(
            @ApiParam(value = "{\"search\"\": \"模糊查询\" , \"nowPage\": \"当前页\" , \"pageSize\": \"显示条数\" }", required = true) @RequestBody String json
    ) throws ServiceException {

         String key =   getString (json , "search" , "");
         int nowPage =   getInt (json , "nowPage" , 1);
         int pageSize =   getInt (json , "pageSize" , 20);
         nowPage /= pageSize;
         nowPage++;

        return  roleService.findRolePage( key  , nowPage , pageSize  );
    }



    /**
     * 添加或修改权限
     * @param
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="添加或修改权限")
    @RequestMapping( value= "/saveOrUpdateRole" , method = {RequestMethod.POST} )
    public boolean saveOrUpdateRole(
            @ApiParam(value = "{\"id\"\": \"角色id\" , \"name\": \"角色名称\" }", required = true) @RequestBody String json
    ) throws ServiceException {
        int  id =   getInt (json , "id" , 0);
        String  name =   getString (json , "name" , "");
        return  roleService.saveOrUpdateRole( id  , name );
    }




    /**
     * 删除权限
     * @param
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="删除权限")
    @RequestMapping( value= "/deleteRoleById" , method = {RequestMethod.POST} )
    public boolean deleteRoleById(
            @ApiParam(value = "{\"id\"\": \"角色id\" }", required = true) @RequestBody String json
    ) throws ServiceException {
        int  id =   getInt (json , "id" , 0);
        return  roleService.deleteRoleById( id);
    }



    public String getString(String json, String key, String defaultVal) {
        return JsonUtils.getString(json, key, defaultVal);
    }

    public int getInt(String json, String key, int defaultVal) {
        return JsonUtils.getInt(json, key, defaultVal);
    }

}
