package com.codingapi.security.dao.impl;

import com.codingapi.security.entity.SResource;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.impl.BaseDaoImpl;
import com.codingapi.security.dao.ResourceDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/16.
 */
@Repository
public class ResourceDaoImpl extends BaseDaoImpl<SResource> implements ResourceDao {


    /**
     * 查询用户的全部资源
     * @param userId
     * @return
     */
    @Override
    public List<Map<String,Object>> findUserResourceByUserId( int userId ) {
        String sql =" SELECT r.* FROM s_resource r " +
                " INNER JOIN s_role_resource srr ON r.id = srr.re_id " +
                " INNER JOIN s_role ro ON ro.id = srr.ro_id " +
                " INNER JOIN s_user_role sur ON sur.rid = ro.id " +
                " WHERE sur.aId = ? ";
        return queryForMapList(sql , userId);
    }



    /**
     * 资源列表
     * @param key
     * @param nowPage
     * @param superId
     * @return
     * @throws ServiceException
     */
    @Override
    public Page<Map<String, Object>> findResourcePage(String key, int nowPage, int pageSize, int superId) {
        String sql =" SELECT * FROM  s_resource   WHERE NAME LIKE ? AND STATUS = 1 AND super_id = ? and type = 0  ORDER BY ordernum DESC   ";
        return pageForMapList(sql , nowPage , pageSize , "%"+key+"%" , superId );
    }


    /**
     * 修改 资源
     * @param map
     * @param id
     * @return
     */
    @Override
    public int updateResource(Map<String, Object> map, int id) {
        return update(" id=? ", map, id);
    }


    /**
     * 修改排序
     * @throws ServiceException
     */
    @Override
    public int updateOrderNumById(String id, int orderNum) {
        String sql =" UPDATE s_resource SET ordernum = ? WHERE id = ?  ";
        return update( sql , orderNum , id);
    }


    /**
     * 根据id 查询
     * @param id
     * @return
     */
    @Override
    public SResource findResourceById(String id) {
        String sql = " SELECT * FROM s_resource WHERE id = ? AND STATUS = 1 ";
        return queryForBean(sql , id);
    }


    /**
     * 查询子级
     * @param id
     * @return
     */
    @Override
    public List<SResource> findAllResourceBySuperId(String id) {
        String sql ="  select * from  s_resource  where status=1 and super_id=? order by ordernum desc ";
        return queryForBeanList(sql , id);
    }


    /**
     * 删除资源
     * @throws ServiceException
     */
    @Override
    public int deleteResourceById(String id) {
        String sql =" UPDATE s_resource SET STATUS = 0 WHERE id = ? ";
        return update(sql , id);
    }




    @Override
    public List<SResource> findAllResourceNoRightByAdminId(Integer id) {
        String sql = "SELECT DISTINCT res.* FROM  s_resource res  " +
                "JOIN   s_role_resource  srr  ON res.id = srr.re_id " +
                "JOIN  s_user_role sur ON sur.rid = srr.ro_id  " +
                "WHERE res.type = 0 and res.status = 1 and sur.aId = ? ORDER BY res.ordernum DESC ";
        return queryForBeanList(sql, id);
    }



    @Override
    public Page<SResource> findResourceRightBySourceId(String superId , String type , int nowPage, int pageSize ) {
        String sql = "SELECT * FROM s_resource WHERE super_id = ? AND TYPE =  ?  AND STATUS = 1";
        return pageForBeanList(sql,nowPage,pageSize,superId , type);
    }


    /**
     * 验证 用户是否有该权限
     * @throws ServiceException
     */
    @Override
    public SResource verifyRight(String userId, String resourceId) {
        String sql =" SELECT  res.* FROM  s_resource res  " +
                "                JOIN   s_role_resource  srr  ON res.id = srr.re_id " +
                "                JOIN   s_user_role sur ON sur.rid = srr.ro_id  " +
                "                WHERE  res.status = 1 AND sur.aId = ? AND res.id = ? ORDER BY res.ordernum DESC ";
        return queryForBean(sql , userId , resourceId);
    }


}
