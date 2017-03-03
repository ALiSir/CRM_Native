package com.powerleader.cdn.crm_cdn.bean;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by ALiSir on 17/1/4.
 */

public class Tp_danju extends RealmObject {
    int id;
    int uId;
    int cId; //客户ID
    String wast;
    int nameId;
    String nextTime;
    String RemindTime;
    int status;
    int remind;
    String content;
    String dTime; //最后更新时间
    String addres;
    String carfare;
    String expenses;
    int num;

    public Tp_danju() {
    }

    public Tp_danju(int id, int uId, int cId, String wast, int nameId, String nextTime, String remindTime, int status, int remind, String content, String dTime, String addres, String carfare, String expenses, int num) {
        this.id = id;
        this.uId = uId;
        this.cId = cId;
        this.wast = wast;
        this.nameId = nameId;
        this.nextTime = nextTime;
        RemindTime = remindTime;
        this.status = status;
        this.remind = remind;
        this.content = content;
        this.dTime = dTime;
        this.addres = addres;
        this.carfare = carfare;
        this.expenses = expenses;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getWast() {
        return wast;
    }

    public void setWast(String wast) {
        this.wast = wast;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getRemindTime() {
        return RemindTime;
    }

    public void setRemindTime(String remindTime) {
        RemindTime = remindTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRemind() {
        return remind;
    }

    public void setRemind(int remind) {
        this.remind = remind;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getCarfare() {
        return carfare;
    }

    public void setCarfare(String carfare) {
        this.carfare = carfare;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Tp_danju{" +
                "id=" + id +
                ", uId=" + uId +
                ", cId=" + cId +
                ", wast='" + wast + '\'' +
                ", nameId=" + nameId +
                ", nextTime='" + nextTime + '\'' +
                ", RemindTime='" + RemindTime + '\'' +
                ", status=" + status +
                ", remind=" + remind +
                ", content='" + content + '\'' +
                ", dTime='" + dTime + '\'' +
                ", addres='" + addres + '\'' +
                ", carfare='" + carfare + '\'' +
                ", expenses='" + expenses + '\'' +
                ", num=" + num +
                '}';
    }
}
