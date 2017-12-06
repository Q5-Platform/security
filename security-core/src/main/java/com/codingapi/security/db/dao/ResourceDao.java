package com.codingapi.security.db.dao;

import com.codingapi.security.db.entity.SResource;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import com.lorne.mysql.framework.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/16.
 */
public interface ResourceDao extends BaseDao<SResource> {


    List<Map<String,Object>> findUserResourceByUserId(int userId);



    /**
     * 资源列表
     * @param key
     * @param nowPage
     * @param superId
     * @return
     * @throws ServiceException
     */
    Page<Map<String,Object>> findResourcePage(String key, int nowPage, int pageSize, int superId);


    /**
     * 修改 资源
     * @param map
     * @param id
     * @return
     */
    int updateResource(Map<String, Object> map, int id);


    /**
     * 修改排序
     * @throws ServiceException
     */
    int updateOrderNumById(String id, int orderNum);


    /**
     * 根据id 查询
     * @param id
     * @return
     */
    SResource findResourceById(String id);

    /**
     * 查询子级
     * @param id
     * @return
     */
    List<SResource> findAllResourceBySuperId(String id);


    /**
     * 删除资源
     * @throws ServiceException
     */
    int deleteResourceById(String id);




    List<SResource> findAllResourceNoRightByAdminId(Integer id);



    Page<SResource> findResourceRightBySourceId(String superId, String type, int i, int i1);


    /**
     * 验证 用户是否有该权限
     * @throws ServiceException
     */
    SResource verifyRight(String userId, String resourceId);
}
