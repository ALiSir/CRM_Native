package com.powerleader.cdn.crm_cdn.bean;

/**
 * Created by Administrator on 2016/12/23.
 */

public class JsonObject {
    int errorCode;
    Object object;
    String msg;

    public JsonObject() {
    }

    public JsonObject(int errorCode, Object object, String msg) {
        this.errorCode = errorCode;
        this.object = object;
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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

    @Override
    public String toString() {
        return "JsonObject{" +
                "errorCode=" + errorCode +
                ", object=" + object +
                ", msg='" + msg + '\'' +
                '}';
    }
}
