package com.lorne.security.service.impl;


import com.lorne.security.dao.ResourceDao;
import com.lorne.security.dao.SRoleResourceDao;
import com.lorne.security.dao.UserDao;
import com.lorne.security.entity.SAdmin;
import com.lorne.security.entity.SResource;
import com.lorne.security.entity.SRoleResource;
import com.lorne.model.MenuTree;
import com.lorne.model.Tree;
import com.lorne.security.service.ResourceService;
import com.lorne.core.framework.exception.ServiceException;
import com.lorne.core.framework.model.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/16.
 */
@Service
public class ResourceServiceImpl implements ResourceService {



    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SRoleResourceDao sRoleResourceDao;



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

        return resourceDao.findResourcePage(key , nowPage , pageSize , superId);
    }


    /**
     * 添加或修改资源
     * @return
     */
    @Override
    public boolean saveOrUpdateResource(int id, String name, String code, int superId, String icon, String url, int type) {
        if (id == 0) {
            SResource r = new SResource();
            r.setName(name);
            r.setStatus(1);
            r.setSuperId(superId);
            r.setUrl(url);
            r.setIcon(icon);
            r.setCode(code);
            r.setType(type);
            r.setOrdernum(0);
            resourceDao.save(r);
            return true;
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("icon", icon);
            map.put("url", url);
            map.put("code", code);
            if (resourceDao.updateResource(map, id) > 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 修改排序
     * @throws ServiceException
     */
    @Override
    public boolean updateOrderNumById(String id, int orderNum) throws ServiceException {
        if(StringUtils.isEmpty(id)){
            throw  new ServiceException("资源不存在!");
        }
        return resourceDao.updateOrderNumById(id , orderNum)>0;
    }


    /**
     * 删除资源
     * @throws ServiceException
     */
    @Override
    public boolean deleteResourceById(String id) throws ServiceException {
        if(StringUtils.isEmpty(id)){
            throw  new ServiceException("资源Id不存在！");
        }

        SResource sresource =  resourceDao.findResourceById(id);
        if(sresource == null){
            throw  new ServiceException("资源不存在！");
        }

        if (resourceDao.findAllResourceBySuperId(id).size() > 0) {
            throw new ServiceException("拥有子集，请删除子集");
        }

        return resourceDao.deleteResourceById(id) > 0;
    }


    /**
     * 加载全部资源树
     * @throws ServiceException
     */
    @Override
    public List<Tree> loadResourceTree(String ids) throws ServiceException {
        List<SResource> resources = resourceDao.findAllResourceBySuperId("0");

        List<Tree> trees = new ArrayList<Tree>();
        if (resources != null && resources.size() > 0) {
            for (SResource resource : resources) {
                Tree tree = new Tree();
                tree.setName(resource.getName());
                tree.setId(String.valueOf(resource.getId()));
                if (!"".equals(ids)) {
                    for (String id : ids.split(",")) {
                        if (tree.getId().equals(id)) {
                            tree.setChecked(true);
                        }
                    }
                }
                if(resource.getType().intValue() == 1){
                    tree.setIcon("../../plugins/ztree/css/zTreeStyle/img/diy/9.png");
                }
                if(resource.getType().intValue() == 2){
                    tree.setIcon("../../plugins/ztree/css/zTreeStyle/img/diy/3.png");
                }
                trees.add(tree);
            }
        }
        addTree(trees, ids);
        return trees;
    }



    @Override
    public List<Integer> getResourceByRoleId(String rId) {
        List<Integer> ids = new ArrayList<Integer>();
        List<SRoleResource> roleResources = sRoleResourceDao.getResourceByRoleId(rId);
        if (roleResources != null && roleResources.size() > 0) {
            for (SRoleResource role : roleResources) {
                ids.add(role.getReId());
            }
        }
        return ids;
    }


    private void addTree(List<Tree> trees, String ids) throws ServiceException {
        for (Tree tree : trees) {
            List<SResource> resources = resourceDao.findAllResourceBySuperId(tree.getId());
            if (resources != null && resources.size() > 0) {
                List<Tree> childrens = new ArrayList<Tree>();
                for (SResource resource : resources) {
                    Tree t = new Tree();
                    t.setName(resource.getName());
                    t.setId(String.valueOf(resource.getId()));
                    if (!"".equals(ids)) {
                        for (String id : ids.split(",")) {
                            if (t.getId().equals(id)) {
                                t.setChecked(true);
                                System.out.println("");
                            }
                        }
                    }
                    if(resource.getType().intValue() == 1){
                        t.setIcon("../../plugins/ztree/css/zTreeStyle/img/diy/9.png");
                    }
                    if(resource.getType().intValue() == 2){
                        t.setIcon("../../plugins/ztree/css/zTreeStyle/img/diy/3.png");
                    }
                    childrens.add(t);
                }
                tree.setChildren(childrens);
                addTree(childrens, ids);
            }
        }
    }








    @Override
    public List<MenuTree> loadAdminMenu(String userId) throws ServiceException {
        if(StringUtils.isEmpty(userId)){
            throw  new ServiceException("用户id不存在！");
        }

        SAdmin sadmin  = userDao.findByUserId(userId);
        if(sadmin == null){
            throw  new ServiceException("用户不存在！");
        }

        List<MenuTree> menus = new ArrayList<MenuTree>();
        if (sadmin != null) {
            List<SResource> sources = resourceDao.findAllResourceNoRightByAdminId(sadmin.getId());
            if (sources != null && sources.size() > 0) {
                for (SResource r : sources) {
                    if (r.getSuperId() == 0) {
                        MenuTree menuTree = new MenuTree();
                        menuTree.setName(r.getName());
                        menuTree.setUrl(r.getUrl());
                        menuTree.setIcon(r.getIcon());
                        menuTree.setId(r.getId());
                        menus.add(menuTree);
                    }
                }
            }
            addAdminTree(menus, sources);
        }
        return menus;
    }




    private void addAdminTree(List<MenuTree> menus, List<SResource> sources) {
        for (MenuTree m : menus) {
            List<SResource> rs = getAdminResourceByMenu(m, sources);
            if (rs != null && rs.size() > 0) {
                List<MenuTree> childs = new ArrayList<MenuTree>();
                for (SResource r : rs) {
                    MenuTree menuTree = new MenuTree();
                    menuTree.setName(r.getName());
                    menuTree.setId(r.getId());
                    menuTree.setUrl(r.getUrl());
                    menuTree.setIcon(r.getIcon());
                    childs.add(menuTree);
                }
                m.setChilds(childs);
                addAdminTree(m.getChilds(), sources);
            }
        }
    }


    private List<SResource> getAdminResourceByMenu(MenuTree m, List<SResource> sources) {
        List<SResource> rs = new ArrayList<SResource>();
        for (SResource r : sources) {
            if (r.getSuperId().equals(m.getId())) {
                rs.add(r);
            }
        }
        return rs;
    }





    @Override
    public Page<SResource> findResourceRightBySourceId(String superId , String type) {
        return resourceDao.findResourceRightBySourceId(superId ,type ,1,10000);
    }



    @Override
    public boolean saveRoleResource(int roId, String ids) {
        List<SRoleResource> roleResources = sRoleResourceDao.getResourceByRoleId( String.valueOf(roId) );
        for (SRoleResource r : roleResources) {
            sRoleResourceDao.delRoleResourceById(r.getId());
        }
        if (!"".equals(ids)) {
            for (String id : ids.split(",")) {
                SRoleResource rr = new SRoleResource();
                rr.setReId(Integer.parseInt(id));
                rr.setRoId(roId);
                sRoleResourceDao.save(rr);
            }
            return true;
        }
        return false;
    }


    /**
     * 验证 用户是否有该权限
     * @throws ServiceException
     */
    @Override
    public boolean verifyRight(String userId, String resourceId) throws ServiceException {
        if(StringUtils.isEmpty(userId)){
            throw  new ServiceException("用户不存在！");
        }
        if(StringUtils.isEmpty(resourceId)){
            throw  new ServiceException("资源不存在！");
        }

        SResource sResource = resourceDao.verifyRight(userId , resourceId);
        if(sResource == null){
            return  false;
        }
        return true;
    }




}
