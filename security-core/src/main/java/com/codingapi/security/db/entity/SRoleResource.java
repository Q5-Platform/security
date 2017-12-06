package com.codingapi.security.db.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lorne.core.framework.model.BaseEntity;

/**
 * 角色资源
 * Created by yuliang on 2015/10/11.
 */
@Table(name = "s_role_resource")
public class SRoleResource extends BaseEntity {

    /**
     * id
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roId;

    /**
     * 资源id
     */
    private Integer reId;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoId() {
        return roId;
    }

    public void setRoId(Integer roId) {
        this.roId = roId;
    }

    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }
}
