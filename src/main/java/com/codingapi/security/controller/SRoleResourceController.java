package com.codingapi.security.controller;

import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.core.framework.utils.JsonUtils;
import com.codingapi.model.Tree;
import com.codingapi.security.ContentController;
import com.codingapi.security.service.ResourceService;
import com.codingapi.security.service.RoleService;
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
 * Created by houcunlu on 2017/8/22.
 */
@Api(description = "角色资源权限管理")
@RestController
@RequestMapping(ContentController.ROLERESOURCE)
public class SRoleResourceController {


    @Autowired
    private RoleService roleService;


    @Autowired
    private ResourceService resourceService;



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
            @ApiParam(value = "模糊查询",required = false) @RequestParam(value = "key" , required = false , defaultValue = "") String key ,
            @ApiParam(value = "当前页",required = true) @RequestParam( value =  "nowPage" , defaultValue = "1" ) int nowPage ) throws ServiceException {

        return  roleService.findRoleAllPage( key  , nowPage , 1000000 , 0  );
    }





    /**
     * 加载全部资源树
     * @throws ServiceException
     */
    @ApiOperation(value="加载全部资源树")
    @RequestMapping( value= "/loadResourceTree" , method = {RequestMethod.POST} )
    public List<Tree> findRolePage(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServiceException {
        String ids = ServletRequestUtils.getStringParameter(request, "ids", "");
        return  resourceService.loadResourceTree( ids );
    }




    /**
     *
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="加载角色的资源id")
    @RequestMapping( value= "/getResourceByRoleId" , method = {RequestMethod.POST} )
    public List<Integer>  getResourceByRoleId(
//            @ApiParam(value = "rId",required = false) @RequestParam(value = "rId" , required = false , defaultValue = "0") String rId
            @ApiParam(value = "{ \"rId\": \"权限id\" }", required = true) @RequestBody String json
    ) throws ServiceException {
            String rId =   getString (json , "rId" , "");
        return  resourceService.getResourceByRoleId( rId );
    }



    /**
     *
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="保存角色的资源")
    @RequestMapping( value= "/saveRoleResource" , method = {RequestMethod.POST} )
    public boolean saveRoleResource(
            @ApiParam(value = "{ \"roId\": \"权限id\" , \"ids\": \"资源id多个逗号分割\"  }", required = true) @RequestBody String json
    ) throws ServiceException {
              int roId =   getInt (json , "roId" , 0);
              String ids =   getString (json , "ids" , "");
        return  resourceService.saveRoleResource( roId , ids );
    }





    public String getString(String json, String key, String defaultVal) {
        return JsonUtils.getString(json, key, defaultVal);
    }

    public int getInt(String json, String key, int defaultVal) {
        return JsonUtils.getInt(json, key, defaultVal);
    }


}
