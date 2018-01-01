package com.codingapi.security.core.controller;

import com.codingapi.security.core.ContentController;
import com.codingapi.security.core.service.SRoleService;
import com.codingapi.security.core.service.SUserService;
import com.codingapi.security.core.service.SUserRoleService;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.core.framework.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/22.
 */
@Api(description = "用户权限管理")
@RestController
@RequestMapping(ContentController.USERROLE)
public class SUserRoleController {


    @Autowired
    private SUserRoleService userRoleService;


    @Autowired
    private SRoleService roleService;


    @Autowired
    private SUserService userService;


    /**
     * 获取权限下的管理员
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="获取权限下的管理员")
    @RequestMapping( value= "/getUserByRoleIdPage" )
    public Page<Map<String,Object>> getUserByRoleId(
            HttpServletRequest request, HttpServletResponse response
        ) throws ServiceException {
            int rid = ServletRequestUtils.getIntParameter(request, "rid", 0);
        return  userRoleService.getUserByRoleId( rid, 1, 100000  );
    }




    /**
     * 获取除拥有该权限用户的所有用户
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="获取除拥有该权限用户的所有用户")
    @RequestMapping( value= "/loadUserNoHaveUserPage" , method = {RequestMethod.POST} )
    public Page<Map<String,Object>> loadUserNoHaveUser(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServiceException {
        int rid = ServletRequestUtils.getIntParameter(request, "rid", 0);
        return  userService.loadUserNoHaveUser( rid, 1, 100000  );
    }



    /**
     * 添加权限用户
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="添加权限用户")
    @RequestMapping( value= "/addUserRole" , method = {RequestMethod.POST} )
    public boolean addUserRole(
              @ApiParam(value = "{\"ids\"\": \"用户id以逗号分割\" , \"rid\": \"权限id\" }", required = true) @RequestBody String json
    ) throws ServiceException {

            String ids =   getString (json , "ids" , "");
            int rid =   getInt (json , "rid" , 0);
        return  userService.addUserRole( ids, rid );
    }




    /**
     * 删除权限用户
     * @return
     * @throws ServiceException
     */
    @ApiOperation(value="删除权限用户")
    @RequestMapping( value= "/deleteUser" , method = {RequestMethod.POST} )
    public boolean deleteUser(
            @ApiParam(value = "{\"aid\"\": \"用户id\" , \"rid\": \"权限id\" }", required = true) @RequestBody String json
    ) throws ServiceException {
        int aid =   getInt (json , "aid" , 0);
        int rid =   getInt (json , "rid" , 0);
        return  userService.deleteUser( aid, rid );
    }



    public String getString(String json, String key, String defaultVal) {
        return JsonUtils.getString(json, key, defaultVal);
    }

    public int getInt(String json, String key, int defaultVal) {
        return JsonUtils.getInt(json, key, defaultVal);
    }


}
