package com.codingapi.security.db.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lorne.core.framework.model.BaseEntity;


/**
 * 资源
 */
@Table(name = "s_resource")
public class SResource extends BaseEntity {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;


    /**
     * 上级编号
     */
    private Integer superId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 地址
     */
    private String url;


    /**
     * 图标
     */
    private String icon;

    /**
     * 资源类型
     */
    private Integer state;


    /**
     * 排序
     */
    private Integer ordernum;

    /**
     * 类型
     *
     * (0:模块路径，1：功能)
     */
    private Integer type;

    /**
     * 权限编码
     */
    private String code;


    @Id
    @GeneratedValue
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

    public Integer getSuperId() {
        return superId;
    }

    public void setSuperId(Integer superId) {
        this.superId = superId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}