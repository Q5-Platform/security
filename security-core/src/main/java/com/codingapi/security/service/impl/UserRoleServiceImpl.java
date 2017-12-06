package com.codingapi.security.service.impl;

import com.codingapi.security.db.dao.SUserRoleDao;
import com.codingapi.security.db.entity.SUserRole;
import com.codingapi.security.service.UserRoleService;
import com.codingapi.security.utils.RegexUtils;
import com.lorne.core.framework.exception.DBException;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户权限Service
 * Created by chentao on 2015/9/9.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SUserRoleDao sUserRoleDao;

    @Override
    public Page<SUserRole> findUserRoleByPage(int pageSize, int nowPage) {
        return null;
    }

    /**
     * 添加用户权限
     *
     * @param ids    用户编号
     * @param roleId 权限编号
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean addUserRole(String ids, int roleId) throws ServiceException {
        if (StringUtils.isBlank(ids) || roleId <= 0) {
            throw new ServiceException("编号错误");
        }
        String idStrs[] = ids.split(",");
        for (String idstr : idStrs) {
            if (RegexUtils.isNumber(idstr)) {
                int id = new Integer(idstr);
                SUserRole u = new SUserRole();
                u.setAid(id);
                u.setRid(roleId);
                sUserRoleDao.save(u);
            }
        }
        return true;
    }

    /**
     * 根据管理员编号 查询 对应的角色编号
     *
     * @param adminId
     * @return
     * @throws ServiceException
     */
    @Override
    public int findRoleByAdminId(int adminId) throws ServiceException {
        return sUserRoleDao.findRoleByAdminId(adminId);
    }

    @Override
    public boolean deleteUserRole(int id) {
        return false;
    }

    /**
     * 获取权限下的用户
     *
     * @param rid      权限编号
     * @param nowPage
     * @param pageSize @return
     */
    @Override
    public Page<Map<String, Object>> getUserByRoleId(int rid, int nowPage, int pageSize) throws DBException {
        return sUserRoleDao.getUserByRoleId(rid, nowPage, pageSize);
    }

    /**
     * 删除权限用户
     *
     * @param aid 用户编号
     * @param rid 权限编号
     * @return
     */
    @Override
    public boolean deleteUser(int aid, int rid) throws ServiceException {
        if (aid <= 0 || rid <= 0) {
            throw new ServiceException("编号错误");
        }
        if (sUserRoleDao.deleteUser(aid, rid) > 0) {
            return true;
        }
        return false;
    }







}
