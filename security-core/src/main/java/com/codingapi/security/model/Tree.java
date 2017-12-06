package com.codingapi.security.model;

import java.util.List;

/**
 * Created by yuliang on 2015/8/27.
 */
public class Tree {

    private String name;
    private String id;
    private Integer isBig;

    private boolean checked;


    private List<Tree> children;
    private String icon;

    public Integer getIsBig() {
        return isBig;
    }

    public void setIsBig(Integer isBig) {
        this.isBig = isBig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
