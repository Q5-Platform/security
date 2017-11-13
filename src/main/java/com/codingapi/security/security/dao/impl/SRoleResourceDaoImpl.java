package com.codingapi.security.security.dao.impl;

import com.codingapi.security.security.dao.SRoleResourceDao;
import com.lorne.mysql.framework.dao.impl.BaseDaoImpl;
import com.codingapi.security.security.entity.SRoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by houcunlu on 2017/8/22.
 */
@Repository
public class SRoleResourceDaoImpl extends BaseDaoImpl<SRoleResource> implements SRoleResourceDao {



    @Override
    public List<SRoleResource> getResourceByRoleId(String rId) {
       return queryForBeanList(" select * from s_role_resource where ro_id = ? ", rId);
    }



    @Override
    public boolean delRoleResourceById(Integer id) {
        return delete(" id=? ", id) > 0;
    }



}
