package com.codingapi.security.dao.impl;


import com.codingapi.security.dao.SUserRoleDao;
import com.codingapi.security.entity.SUserRole;
import com.lorne.core.framework.exception.DBException;
import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public class SUserRoleDaoImpl extends BaseDaoImpl<SUserRole> implements SUserRoleDao {

    /**
     * 获取权限下的用户
     *
     * @param rid      权限编号
     * @param nowPage
     * @param pageSize @return
     */
    @Override
    public Page<Map<String, Object>> getUserByRoleId(final int rid, final int nowPage, final int pageSize) throws DBException {
        return pageForMapList("SELECT a.id aid,a.user_name,r.id rid FROM  s_user_role ur INNER JOIN s_role r ON ur.rid=r.id INNER JOIN s_admin a ON ur.aId=a.id WHERE ur.rid=?", nowPage, pageSize, rid);
    }

    @Override
    public int findRoleByAdminId(int adminId) throws DBException {
        String sql = "select a.rid from s_user_role a where a.aid=?";
        return queryForInt(sql, adminId);
    }

    /**
     * 删除权限用户
     *
     * @param aid 用户编号
     * @param rid 权限编号
     * @return
     */
    @Override
    public int deleteUser(final int aid, final int rid) throws DBException {
        return delete(" aId=? and rid=? ", aid, rid);
    }
}