package com.powerleader.cdn.crm_cdn.bean;

/**
 * Created by ALiSir on 17/1/9.
 */

public class LoginResult {

    int code;
    Object object;
    String msg;
    int uid;
    int update;

    public LoginResult() {
    }

    public LoginResult(int code, Object object, String msg, int uid, int update) {
        this.code = code;
        this.object = object;
        this.msg = msg;
        this.uid = uid;
        this.update = update;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "code=" + code +
                ", object=" + object +
                ", msg='" + msg + '\'' +
                ", uid=" + uid +
                ", update=" + update +
                '}';
    }
}
