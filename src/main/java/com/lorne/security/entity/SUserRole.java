package com.lorne.security.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lorne.core.framework.model.BaseEntity;

/**
 * 用户角色
 */
@Table(name = "s_user_role")
public class SUserRole extends BaseEntity {

    /**
     * 编号
     */
    public Integer id;


    /**
     * 用户编号
     */
    public Integer aid;


    /**
     * 角色编号
     */
    public Integer rid;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}