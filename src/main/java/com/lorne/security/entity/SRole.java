package com.lorne.security.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lorne.core.framework.model.BaseEntity;

/**
 * 角色
 */
@Table(name = "s_role")
public class SRole extends BaseEntity {

    /**
     * 编号
     */
    public Integer id;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 名称
     */
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 状态
     */
    public Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}