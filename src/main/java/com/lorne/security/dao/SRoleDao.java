package com.lorne.security.dao;

import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.BaseDao;
import com.lorne.security.entity.SRole;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/21.
 */
public interface SRoleDao extends BaseDao<SRole> {



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
     * 查询权限
     * @param name
     * @return
     */
    SRole findRoleByName(String name);


    /**
     * 修改权限名称
     * @param name
     * @param id
     * @return
     */
    int updateRoleNameById(String name, int id);


    /**
     * 删除权限
     * @return
     * @throws ServiceException
     */
    boolean deleteRoleById(int id);

}
