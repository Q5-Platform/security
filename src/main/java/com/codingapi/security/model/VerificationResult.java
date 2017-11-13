package com.codingapi.security.model;

/**
 * Created by lorne on 2017/7/8.
 */
public class VerificationResult {


    public final static int STATE_ERROR = 0;

    public final static int STATE_OK = 1;


    /**
     * 权限
     */
    public final static int STATE_security_OK = 2;


    /**
     * 权限
     */
    public final static int STATE_security_ERROR = 2;


    public VerificationResult() {
        state = STATE_OK;
    }

    private int state;

    private String msg;


    private String securityDate;

    public String getSecurityDate() {
        return securityDate;
    }

    public void setSecurityDate(String securityDate) {
        this.securityDate = securityDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
