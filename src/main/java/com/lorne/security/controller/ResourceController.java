package com.lorne.security.controller;

import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.core.framework.utils.JsonUtils;
import com.lorne.model.MenuTree;
import com.lorne.security.ContentController;
import com.lorne.security.entity.SResource;
import com.lorne.security.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/21.
 */
@Api(description = "资源模块")
@RestController
@RequestMapping(ContentController.RESOURCE)
public class ResourceController {



    @Autowired
    private ResourceService resourceService;


    /**
     * 资源列表
     * @param
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="资源列表")
    @RequestMapping( value= "/findResourcePage" , method = {RequestMethod.POST} )
    public Page<Map<String,Object>> login(HttpServletRequest request, HttpServletResponse response ,
          @ApiParam(value = "{\"search\": \"模糊查询\",\"offset\": \"当前页\" ,\"superId\": \"父级id\"}", required = true) @RequestBody String json
    ) throws ServiceException {
            String key =  getString(json , "search" ,"");
            int nowPage =  getInt(json , "offset" ,0);
            int superId = ServletRequestUtils.getIntParameter(request, "superId", 0);
        return  resourceService.findResourcePage( key  , nowPage , 100000 ,  superId );
    }


    /**
     * 添加或修改资源
     * @return
     */
    @ApiOperation(value="添加或修改资源")
    @RequestMapping( value= "/saveOrUpdateResource" , method = {RequestMethod.POST} )
    public boolean saveOrUpdateResource(
            @ApiParam(value = "{\"id\": \"资源id\" , \"name\": \"资源名称\" , \"superId\": \"父级id\" , \"icon\": \"图标\" , \"url\": \"地址\" , \"code\": \"权限编码\" , \"type\": \"类型 (0:模块路径，1：功能 ， 2：接口)\"     }", required = true) @RequestBody String json
    ) throws ServiceException {

         int id =   getInt (json , "id" , 0);
         String name =   getString (json , "name" , "");
         String code =   getString (json , "code" , "");
         int superId =   getInt (json , "superId" , 0);
         String icon =   getString (json , "icon" , "");
         String url =   getString (json , "url" , "");
         int type =   getInt (json , "type" , 0);
        return  resourceService.saveOrUpdateResource(id, name,code, superId , icon , url ,type );
    }


    /**
     * 修改排序
     * @throws ServiceException
     */
    @ApiOperation(value="修改排序")
    @RequestMapping( value= "/updateOrderNumById" , method = {RequestMethod.POST} )
    public boolean updateOrderNumById(
            @ApiParam(value = "{\"id\": \"资源id\",\"orderNum\": \"排序\" }", required = true) @RequestBody String json
    ) throws ServiceException {
              String id =  getString(json , "id" ,"");
              int orderNum =  getInt(json , "orderNum" ,0);
        return  resourceService.updateOrderNumById( id  , orderNum );
    }



    /**
     * 删除资源
     * @throws ServiceException
     */
    @ApiOperation(value="删除资源")
    @RequestMapping( value= "/deleteResourceById" , method = {RequestMethod.POST} )
    public boolean deleteResourceById(
            @ApiParam(value = "{\"id\": \"资源id\"}", required = true) @RequestBody String json
    ) throws ServiceException {
        String id =  getString(json , "id" ,"");
        return  resourceService.deleteResourceById( id );
    }



    /**
     * 加载菜单
     * @throws ServiceException
     */
    @ApiOperation(value="加载菜单")
    @RequestMapping( value= "/loadAdminMenu" , method = {RequestMethod.POST} )
    public List<MenuTree> loadAdminMenu(
            @ApiParam(value = "{\"userId\": \"用户id\"}", required = true) @RequestBody String json
    ) throws ServiceException {
        String userId =  getString(json , "userId" ,"");
        return  resourceService.loadAdminMenu(userId);
    }



    /**
     *
     * @throws ServiceException
     */
    @ApiOperation(value="")
    @RequestMapping( value= "/findResourceRightPage" , method = {RequestMethod.POST} )
    public Page<SResource>  findResourceRightBySourceId(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServiceException {
        String superId = ServletRequestUtils.getStringParameter(request, "superId", "");
        String type = ServletRequestUtils.getStringParameter(request, "type", "");
        return  resourceService.findResourceRightBySourceId(superId , type);
    }





    /**
     * 验证 用户是否有该权限
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="验证 用户是否有该权限")
    @RequestMapping( value= "/verifyRight" , method = {RequestMethod.POST})
    public boolean verifyRight(
            @ApiParam(value = "{\"userId\": \"用户id\" , \"resourceId\": \"资源id\"  }", required = true) @RequestBody String json
    ) throws ServiceException {

        String userId =  getString(json , "userId" ,"");
        String resourceId =  getString(json , "resourceId" ,"");

        return  resourceService.verifyRight( userId , resourceId );
    }




    public String getString(String json, String key, String defaultVal) {
        return JsonUtils.getString(json, key, defaultVal);
    }

    public int getInt(String json, String key, int defaultVal) {
        return JsonUtils.getInt(json, key, defaultVal);
    }


}
