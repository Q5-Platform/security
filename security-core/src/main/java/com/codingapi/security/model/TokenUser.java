package com.codingapi.security.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/15.
 */
public class TokenUser implements Serializable {


    /**
     * 用户id
     */
    private  Integer userId;


    /**
     * 用户权限
     */
    private List<Map<String,Object>> resource;



    @Id
    @GeneratedValue
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public  List<Map<String, Object>> getResource() {
        return resource;
    }

    public void setResource(List<Map<String, Object>> resource) {
        this.resource = resource;
    }



}
