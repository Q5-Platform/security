package com.codingapi.security.service;

import com.codingapi.security.db.entity.SResource;
import com.codingapi.security.model.MenuTree;
import com.codingapi.security.model.Tree;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/16.
 */
public interface SResourceService {



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
     * 添加或修改资源
     * @return
     */
    boolean saveOrUpdateResource(int id, String name, String code, int superId, String icon, String url, int type);


    /**
     * 修改排序
     * @throws ServiceException
     */
    boolean updateOrderNumById(String id, int orderNum) throws ServiceException;


    /**
     * 删除资源
     * @throws ServiceException
     */
    boolean deleteResourceById(String id) throws ServiceException;



    /**
     * 加载全部资源树
     * @throws ServiceException
     */
    List<Tree> loadResourceTree(String ids) throws ServiceException;



    List<Integer>  getResourceByRoleId(String rId);



    List<MenuTree> loadAdminMenu(String userId) throws ServiceException;


    Page<SResource> findResourceRightBySourceId(String superId, String type);


    boolean saveRoleResource(int roId, String ids);


    /**
     * 验证 用户是否有该权限
     * @throws ServiceException
     */
    boolean verifyRight(String userId, String resourceId) throws ServiceException;
}
