package com.codingapi.security.model;

/**
 * Created by houcunlu on 2017/8/18.
 */
public class Msg {

    private int state;
    private String msg;
    private Object res;

    public Msg() {
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Msg(int state, Object res) {
        this.state = state;
        this.res = res;
    }

    public Msg(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getRes() {
        return this.res;
    }

    public void setRes(Object res) {
        this.res = res;
    }
}
