package com.lorne.security.service;

import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/21.
 */
public interface RoleService {


    /**
     * 查询所有权限
     * @param key
     * @param nowPage
     * @param
     * @return
     * @throws ServiceException
     */
    Page<Map<String,Object>> findRolePage(String key, int nowPage, int pageSize);



    /**
     * 查询所有权限
     *
     * @param key
     * @param nowPage  当前页
     * @return
     */
    Page<Map<String,Object>> findRoleAllPage(String key, int nowPage, int pageSize, int sid);


    /**
     * 添加或修改权限
     * @param
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    boolean saveOrUpdateRole(int id, String name) throws ServiceException;


    /**
     * 删除权限
     * @param
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    boolean deleteRoleById(int id);




}
