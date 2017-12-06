package com.codingapi.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.security.db.dao.SUserRoleDao;
import com.codingapi.security.db.entity.SAdmin;
import com.codingapi.security.db.entity.SUserRole;
import com.codingapi.security.model.TokenUser;
import com.codingapi.security.db.dao.ResourceDao;
import com.codingapi.security.db.dao.UserDao;
import com.codingapi.security.service.UserService;
import com.codingapi.security.redis.RedisHelper;
import com.codingapi.security.utils.RegexUtils;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.core.framework.utils.KidUtils;
import com.lorne.core.framework.utils.encode.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by houcunlu on 2017/8/15.
 */
@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private RedisHelper ru;


    @Autowired
    private ResourceDao resourcedao;


    @Autowired
    private UserDao userDao;


    @Autowired
    private SUserRoleDao sUserRoleDao;




    /**
     *  登陆
     * @param userName
     * @param userPwd
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> login(String userName, String userPwd) throws ServiceException {
        if(StringUtils.isEmpty(userName)){
            throw  new ServiceException("账户为空！");
        }

        if(StringUtils.isEmpty(userPwd)){
            throw  new ServiceException("密码为空！");
        }

        SAdmin admin = userDao.findByUserName(userName);
        if(admin == null){
            throw  new ServiceException("用户不存在！");
        }
        if(admin.getEnable().toString().equals("0")){
            throw  new ServiceException("该用户已被禁用！");
        }

        if( !MD5Util.string2MD5(userPwd).equals(admin.getUserPwd()) ){
            throw  new ServiceException("密码不正确！");
        }

        // 修改用户最后登陆时间
        userDao.updateLastDateByUserId(admin.getId().intValue());

        String key = MD5Util.string2MD5(KidUtils.getUUID());
        TokenUser tokenUser = new TokenUser();
        tokenUser.setUserId(admin.getId());
        List<Map<String,Object>> list = resourcedao.findUserResourceByUserId(admin.getId().intValue());
        tokenUser.setResource(list);
        String json = JSONObject.toJSONString(tokenUser);
        ru.putToken(key , json , 7200); // 两小时

        Map<String,Object> map = new HashMap<>();
        map.put("toKen",key);
        map.put("userId",admin.getId().intValue());

        return map;
    }





    /**
     * 根据账号 查询 正常用户
     * @param userName
     * @return
     */
    @Override
    public SAdmin findUserByUserName(String userName) throws ServiceException {
        if(StringUtils.isEmpty(userName)){
            throw  new ServiceException("账户为空！");
        }
        return userDao.findByUserName(userName);
    }


    /**
     * 更新用户 最后登陆时间
     * @param id
     */
    @Override
    public void updateLastDateByUserId(int id) {
        userDao.updateLastDateByUserId(id);
    }


    /**
     * 添加用户
     * @param userName
     * @param userPwd
     * @param photo
     * @return
     */
    @Override
    public boolean addUser(String userName, String userPwd, String photo) throws ServiceException {
        if(StringUtils.isEmpty(userName)){
            throw  new ServiceException("账户为空！");
        }
        if(StringUtils.isEmpty(userPwd)){
            throw  new ServiceException("密码为空！");
        }
        if(StringUtils.isEmpty(photo)){
            throw  new ServiceException("头像为空！");
        }
        checkInfo(userName , userPwd);

        SAdmin  admin = userDao.findByUserName(userName);
        if(admin != null){
            throw  new ServiceException("账户已存在！");
        }

        SAdmin sadmin = new SAdmin();
        sadmin.setUserName(userName);
        sadmin.setUserPwd(MD5Util.string2MD5(userPwd));
        sadmin.setPhoto(photo);
        sadmin.setEnable(1);
        sadmin.setCreated(new Date());
        sadmin.setStatus(1);

        return userDao.save(sadmin) > 0;
    }


    /**
     * 修改用户
     * @param userId
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updateUserById(String userId, String userName , String photo) throws ServiceException {
        if(StringUtils.isEmpty(userId)){
            throw  new ServiceException("用户id为空！");
        }

        if(StringUtils.isEmpty(photo)){
            throw  new ServiceException("头像为空！");
        }


        SAdmin  admin = userDao.findByUserId(userId);
        if(admin == null){
            throw  new ServiceException("用户不存在！");
        }

        Pattern userNameP = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");
        if(!userNameP.matcher(userName).matches()){
            throw  new ServiceException("用户名：（由字母数字下划线组成且开头必须是字母，不能超过16位）！");
        }


        SAdmin  adminName = userDao.findByUserName(userName);
        if(adminName != null){
            if(!adminName.getId().toString().equals(userId)){
                throw  new ServiceException("用户名已存在！");
            }
        }
        return userDao.updateUserById(userId , userName  , photo) > 0;
    }


    /**
     * 禁用 / 解禁
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updateEnableByUserId(String userId, String enable) throws ServiceException {
        if(StringUtils.isEmpty(userId)){
            throw  new ServiceException("用户id为空！");
        }
        if(StringUtils.isEmpty(enable)){
            throw  new ServiceException("状态为空！");
        }
        SAdmin  admin = userDao.findByUserId(userId);
        if(admin == null){
            throw  new ServiceException("用户不存在！");
        }
        return userDao.updateEnableByUserId(userId , enable) > 0;
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
        return userDao.findUserPage(nowPage , pageSize , key);
    }


    /**
     * 删除管理员
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean deleteUserById(String userId) throws ServiceException {
        if(StringUtils.isEmpty(userId)){
            throw  new ServiceException("用户id不存在！");
        }
        if("1".equals(userId)){
            throw  new ServiceException("超级管理员不可删除！");
        }
        return userDao.deleteUserById(userId);
    }


    /**
     * 修改头像
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updatePhoto(String userId, String photo) throws ServiceException {
        if(StringUtils.isEmpty(userId)){
            throw  new ServiceException("用户id不存在！");
        }
        if(StringUtils.isEmpty(photo)){
            throw  new ServiceException("头像不可为空！");
        }
        return userDao.updatePhoto(userId , photo) > 0;
    }



    /**
     * 获取除拥有该权限用户的所有用户
     * @return
     * @throws ServiceException
     */
    @Override
    public Page<Map<String, Object>> loadUserNoHaveUser(int rid, int nowPage, int pageSize) throws ServiceException {
        if (rid <= 0) {
            throw new ServiceException("权限编号错误");
        }
        return userDao.loadUserNoHaveUser(rid, nowPage, pageSize);
    }


    /**
     * 添加权限用户
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
                u.setRid(roleId);
                u.setAid(id);
                sUserRoleDao.save(u);
            }
        }
        return true;
    }


    /**
     * 删除权限用户
     * @return
     * @throws ServiceException
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


    /**
     * 验证用注册信息
     * @param userName
     * @param pwd
     * @throws ServiceException
     */
    private void checkInfo(String userName ,String  pwd  ) throws ServiceException {
        Pattern userNameP = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");
        Pattern pwdP = Pattern.compile("[a-zA-Z0-9]{1,16}");
        if(!userNameP.matcher(userName).matches()){
            throw  new ServiceException("用户名：（由字母数字下划线组成且开头必须是字母，不能超过16位）！");
        }
        if(!pwdP.matcher(pwd).matches()){
            throw  new ServiceException("密码：（字母和数字构成，不能超过16位）！");
        }
    }









}
