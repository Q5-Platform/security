package com.lorne.security.service.impl;

import com.lorne.security.dao.SRoleDao;
import com.lorne.security.entity.SRole;
import com.lorne.security.service.RoleService;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by houcunlu on 2017/8/21.
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private SRoleDao sroleDao;



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
        return sroleDao.findRolePage(key , nowPage , pageSize);
    }


    /**
     * 查询所有权限
     * @throws ServiceException
     */
    @Override
    public Page<Map<String, Object>> findRoleAllPage(String key, int nowPage, int pageSize, int sid) {
        return sroleDao.findRolePage(key , nowPage , pageSize );
    }


    /**
     * 添加或修改权限
     * @param
     * @param
     * @param
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean saveOrUpdateRole(int id, String name) throws ServiceException {
        if(StringUtils.isEmpty(name)){
            throw  new ServiceException("角色不可为空！");
        }
        SRole r = sroleDao.findRoleByName(name);
        if (id == 0) {
            if(r!=null){
                throw new ServiceException("该角色已拥有");
            }
            r = new SRole();
            r.setName(name);
            r.setStatus(1);
            sroleDao.save(r);
            return true;
        } else {
            if(r!=null){
                throw new ServiceException("该角色已拥有");
            }
            if (sroleDao.updateRoleNameById(name, id) > 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 删除权限
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean deleteRoleById(int id) {
        return sroleDao.deleteRoleById(id);
    }


}
