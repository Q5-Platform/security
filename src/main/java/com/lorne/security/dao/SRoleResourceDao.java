package com.lorne.security.dao;

import com.lorne.mysql.framework.dao.BaseDao;
import com.lorne.security.entity.SRoleResource;

import java.util.List;

/**
 * Created by houcunlu on 2017/8/22.
 */
public interface SRoleResourceDao extends BaseDao<SRoleResource> {


    List<SRoleResource> getResourceByRoleId(String rId);



    boolean delRoleResourceById(Integer id);
}
