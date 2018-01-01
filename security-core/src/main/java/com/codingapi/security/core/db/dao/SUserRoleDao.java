package com.codingapi.security.core.db.dao;



import com.codingapi.security.core.db.entity.SUserRole;
import com.lorne.core.framework.exception.DBException;
import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.BaseDao;

import java.util.Map;



public interface SUserRoleDao extends BaseDao<SUserRole> {

    /**
     * 获取权限下的用户
     * @param rid   权限编号
     * @param nowPage
     *@param pageSize @return
     */
    Page<Map<String,Object>> getUserByRoleId(int rid, int nowPage, int pageSize) throws DBException;



    int findRoleByAdminId(int adminId) throws DBException;

    /**
     * 删除权限用户
     * @param aid   用户编号
     * @param rid   权限编号
     * @return
     */
    int deleteUser(int aid, int rid) throws DBException;
}