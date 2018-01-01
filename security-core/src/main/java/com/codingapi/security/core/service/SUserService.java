package com.codingapi.security.core.service;

import com.codingapi.security.core.db.entity.SAdmin;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/15.
 */
public interface SUserService {

    /**
     * 登陆
     * @param userName
     * @param userPwd
     * @return
     */
    Map<String,Object> login(String userName, String userPwd) throws ServiceException;



    /**
     * 根据账号 查询 正常用户
     * @param userName
     * @return
     */
    SAdmin findUserByUserName(String userName) throws ServiceException;


    /**
     * 更新用户 最后登陆时间
     * @param id
     */
    void updateLastDateByUserId(int id);


    /**
     * 添加用户
     * @param userName
     * @param userPwd
     * @param photo
     * @return
     */
    boolean addUser(String userName, String userPwd, String photo) throws ServiceException;


    /**
     * 修改用户
     * @param userId
     * @param
     * @return
     * @throws ServiceException
     */
    boolean updateUserById(String userId, String userName, String photo) throws ServiceException;



    /**
     * 禁用 / 解禁
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    boolean updateEnableByUserId(String userId, String enable) throws ServiceException;


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
    boolean deleteUserById(String userId) throws ServiceException;


    /**
     * 修改头像
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    boolean updatePhoto(String userId, String photo) throws ServiceException;




    /**
     * 获取除拥有该权限用户的所有用户
     * @return
     * @throws ServiceException
     */
    Page<Map<String,Object>> loadUserNoHaveUser(int rid, int nowPage, int i) throws ServiceException;


    /**
     * 添加权限用户
     * @return
     * @throws ServiceException
     */
    boolean addUserRole(String ids, int rid) throws ServiceException;


    /**
     * 删除权限用户
     * @return
     * @throws ServiceException
     */
    boolean deleteUser(int aid, int rid) throws ServiceException;
}
