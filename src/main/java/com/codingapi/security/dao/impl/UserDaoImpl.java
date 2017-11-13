package com.codingapi.security.dao.impl;


import com.codingapi.security.entity.SAdmin;
import com.lorne.core.framework.exception.ServiceException;

import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.impl.BaseDaoImpl;
import com.codingapi.security.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * Created by houcunlu on 2017/8/16.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<SAdmin> implements UserDao {



    /**
     * 根据账户查询
     * @param username
     * @return
     */
    @Override
    public SAdmin findByUserName(String username) {
        String sql =" SELECT * FROM s_admin WHERE user_name = ?  AND STATUS = 1 ";
        return queryForBean(sql , username);
    }


    /**
     * 更新用户 最后登陆时间
     * @param id
     */
    @Override
    public void updateLastDateByUserId(int id) {
        String sql =" UPDATE s_admin SET  last_date = NOW() WHERE id = ? ";
        update(sql , id );
    }


    /**
     * 根据账户Id查询
     * @param userId
     * @return
     */
    @Override
    public SAdmin findByUserId(String userId) {
        String sql =" SELECT * FROM s_admin WHERE id = ?  AND STATUS = 1 ";
        return queryForBean(sql , userId);
    }


    /**
     * 禁用 / 解禁
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public int updateEnableByUserId(String userId, String enable) {
        String sql =" UPDATE s_admin SET  ENABLE = ? WHERE id = ? ";
        return update(sql , enable , userId);
    }


    /**
     * 修改用户
     * @param userId
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public int updateUserById(String userId, String userName, String photo) {
        String sql =" UPDATE s_admin SET  user_name = ? ,  photo = ?  WHERE id = ? ";
        return update(sql , userName ,  photo , userId);
    }


    /**
     * 用户列表
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public Page<Map<String, Object>> findUserPage(int nowPage, int pageSize, String key) {
        String sql =" SELECT id , user_name , enable , photo , last_date , created  FROM s_admin WHERE user_name LIKE ? and  STATUS = 1 ";
        return pageForMapList(sql , nowPage , pageSize , "%"+key+"%" );
    }


    /**
     * 删除管理员
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean deleteUserById(String userId) {
        String sql =" UPDATE s_admin SET STATUS = 0 WHERE id = ? ";
        return update(sql , userId) > 0;
    }


    /**
     * 修改头像
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public int updatePhoto(String userId, String photo) {
        String sql =" UPDATE s_admin SET photo = ? WHERE id = ? ";
        return update(sql , photo , userId );
    }


    @Override
    public Page<Map<String, Object>> loadUserNoHaveUser(final int rid, final int nowPage, final int pageSize) {
        String sql =" SELECT a.id aid,a.user_name,0 as state FROM s_admin a WHERE id NOT IN (SELECT a.id aid FROM s_user_role ur INNER JOIN s_admin a ON ur.aId=a.id WHERE ur.rid=?) AND a.enable=1 and a.status =1";
        return pageForMapList(sql , nowPage, pageSize, rid);
    }



}
