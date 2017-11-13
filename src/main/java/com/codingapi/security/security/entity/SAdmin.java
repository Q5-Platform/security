package com.codingapi.security.security.entity;





import com.lorne.core.framework.model.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * Created by houcunlu on 2017/8/16.
 */
/**
 * 资源
 */
@Table(name = "s_admin")
public class SAdmin extends BaseEntity {


    /**
     * 编号
     */
    public Integer id;



    /**
     * 用户名
     */
    public String userName;


    /**
     * 密码
     */
    public String userPwd;



    /**
     * 禁用状态  0:禁用 1：正常
     */
    public Integer enable;


    /**
     * 头像
     */
    public String photo;



    /**
     * 最后登录时间
     */
    public Date lastDate;



    /**
     * 状态  0:删除 1：正常
     */
    public Integer status;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     */
    public Date created;


    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
