package com.codingapi.security.security.dao;


import com.codingapi.security.security.entity.SAdmin;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.BaseDao;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/16.
 */
public interface UserDao extends BaseDao<SAdmin> {

    /**
     * 根据账户查询
     * @param username
     * @return
     */
    SAdmin findByUserName(String username);



    /**
     * 更新用户 最后登陆时间
     * @param id
     */
    void updateLastDateByUserId(int id);



    /**
     * 根据账户Id查询
     * @param userId
     * @return
     */
    SAdmin findByUserId(String userId);


    /**
     * 禁用 / 解禁
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    int updateEnableByUserId(String userId, String enable);


    /**
     * 修改用户
     * @param userId
     * @param
     * @return
     * @throws ServiceException
     */
    int updateUserById(String userId, String userName, String photo);


    /**
     * 用户列表
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    Page<Map<String,Object>> findUserPage(int nowPage, int pageSize, String key);


    /**
     * 删除管理员
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    boolean deleteUserById(String userId);


    /**
     * 修改头像
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    int updatePhoto(String userId, String photo);


    /**
     * 获取除拥有该权限用户的所有用户
     * @return
     * @throws ServiceException
     */
    Page<Map<String,Object>> loadUserNoHaveUser(int rid, int nowPage, int pageSize);
}
