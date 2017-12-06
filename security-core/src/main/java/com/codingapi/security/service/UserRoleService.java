package com.codingapi.security.service;



import com.codingapi.security.db.entity.SUserRole;
import com.lorne.core.framework.exception.DBException;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;

import java.util.Map;

/**
 * 用户权限Service接口
 * Created by ludaidai on 2015/8/14.
 */
public interface UserRoleService {

    /**
     * 分页查询用户权限
     *
     * @param pageSize
     * @param nowPage
     * @return
     */
    Page<SUserRole> findUserRoleByPage(int pageSize, int nowPage);

    /**
     * 添加用户权限
     *
     * @param ids    用户编号
     * @param roleId 权限编号
     * @return 添加状态
     */
    boolean addUserRole(String ids, int roleId) throws ServiceException;

    /**
     * 根据管理员编号 查询 对应的角色编号
     *
     * @param adminId
     * @return
     * @throws ServiceException
     */
    int findRoleByAdminId(int adminId) throws ServiceException;

    /**
     * 删除用户权限
     *
     * @param id 用户权限编号
     * @return 删除状态
     */
    boolean deleteUserRole(int id);

    /**
     * 获取权限下的用户
     *
     * @param rid     权限编号
     * @param nowPage
     * @param i       @return
     */
    Page<Map<String, Object>> getUserByRoleId(int rid, int nowPage, int i) throws DBException;

    /**
     * 删除权限用户
     *
     * @param aid 用户编号
     * @param rid 权限编号
     * @return
     */
    boolean deleteUser(int aid, int rid) throws ServiceException;
}
