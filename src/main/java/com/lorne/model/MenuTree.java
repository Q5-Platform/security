package com.lorne.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuliang on 2015/9/29.
 */
public class MenuTree {

    /**
     * id
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 菜单图片
     */
    private String icon;

    /**
     * 子类别
     */
    private List<MenuTree> childs;


    public MenuTree() {
        this.childs = new ArrayList<MenuTree>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuTree> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuTree> childs) {
        this.childs = childs;
    }
}
