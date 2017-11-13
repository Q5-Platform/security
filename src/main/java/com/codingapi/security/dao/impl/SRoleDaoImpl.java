package com.codingapi.security.dao.impl;

import com.codingapi.security.dao.SRoleDao;
import com.codingapi.security.entity.SRole;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/21.
 */
@Repository
public class SRoleDaoImpl extends BaseDaoImpl<SRole> implements SRoleDao {



    /**
     * 查询所有权限
     * @param key
     * @param nowPage
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public Page<Map<String, Object>> findRolePage(String key, int nowPage, int pageSize) {
        String sql =" SELECT * FROM s_role WHERE STATUS=1 AND NAME LIKE ? ";
        return pageForMapList(sql , nowPage , pageSize , "%"+key+"%");
    }




    /**
     * 查询权限
     * @param name
     * @return
     */
    @Override
    public SRole findRoleByName(String name) {
        String sql ="  select * from s_role  where status=1 and name = ? ";
        return queryForBean(sql , name);
    }


    /**
     * 修改权限名称
     * @param name
     * @param id
     * @return
     */
    @Override
    public int updateRoleNameById(String name, int id) {
        String sql =" UPDATE s_role SET NAME = ? WHERE id = ? ";
        return update( sql , name , id);
    }


    /**
     * 删除权限
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean deleteRoleById(int id) {
        String sql =" UPDATE s_role SET STATUS = 0 WHERE id = ? ";
        return update(sql , id) > 0;
    }


}
