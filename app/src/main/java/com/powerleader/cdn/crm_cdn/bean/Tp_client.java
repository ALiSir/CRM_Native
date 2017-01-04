package com.powerleader.cdn.crm_cdn.bean;

import com.google.gson.Gson;

import io.realm.RealmObject;

/**
 * Created by ALiSir on 17/1/4.
 */

public class Tp_client extends RealmObject {

//    DROP TABLE IF EXISTS `tp_client`;
//    CREATE TABLE `tp_client` (
//            `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
//    `Uid` int(10) DEFAULT '0' COMMENT '用户ID',
//            `Cid` int(10) DEFAULT '0' COMMENT '默认联系人ID',
//            `CompanyName` char(60) DEFAULT NULL COMMENT '公司名称',
//            `Address` char(100) DEFAULT NULL COMMENT '详细地址',
//            `ZipCode` char(10) DEFAULT NULL COMMENT '邮编',
//            `WebUrl` varchar(60) DEFAULT NULL COMMENT '网站地址',
//            `Industry` tinyint(4) DEFAULT '0' COMMENT '属所行业',
//            `ClientType` tinyint(4) DEFAULT '0' COMMENT '客户类型',
//            `ClientLevel` tinyint(4) DEFAULT '0' COMMENT '客户级别',
//            `ClientSource` tinyint(4) DEFAULT '0' COMMENT '客户来源',
//            `FollowUp` tinyint(4) DEFAULT '0' COMMENT '跟进情况',
//            `Wast` tinyint(4) DEFAULT '0' COMMENT '跟单类型',
//            `Intent` tinyint(4) DEFAULT '0' COMMENT '户客意向',
//            `MainItems` varchar(200) DEFAULT NULL COMMENT '主营项目',
//            `Message` varchar(1000) DEFAULT NULL COMMENT '备注，其它',
//            `OpenShare` tinyint(1) DEFAULT '0' COMMENT '是否共享',
//            `Share` varchar(500) DEFAULT NULL COMMENT '针对共享',
//            `Recycle` tinyint(1) DEFAULT '0' COMMENT '是否在回收站',
//            `Dtime` datetime DEFAULT NULL COMMENT '录入时间',
//            `FinalTime` datetime DEFAULT NULL COMMENT '最后更新时间',
//            `Describe` varchar(255) DEFAULT NULL,
//    `Issuccess` int(255) NOT NULL DEFAULT '0',
//    PRIMARY KEY (`ID`)
//    ) ENGINE=MyISAM AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;

    int id;  //主键
    int uid; //用户ID
    int cid;//默认联系人ID
    String companyName;//公司名称
    String address;//详细地址
    String zipCode;//邮编
    String webUrl;//网站地址
    int industry;//属所行业
    int clientType;//客户类型
    int clientLevel;//客户级别
    int clientSource;//客户来源
    int followUp;//跟进情况
    int wast;//跟单类型
    int intent;//户客意向
    String mainItems;//主营项目
    String message;//备注，其它
    int openShare;//是否共享
    String share;//针对共享
    int recycle;//是否在回收站
    String dtime;//录入时间
    String finalTime;//最后更新时间
    String describe;
    int issuccess;

    public Tp_client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public int getClientLevel() {
        return clientLevel;
    }

    public void setClientLevel(int clientLevel) {
        this.clientLevel = clientLevel;
    }

    public int getClientSource() {
        return clientSource;
    }

    public void setClientSource(int clientSource) {
        this.clientSource = clientSource;
    }

    public int getFollowUp() {
        return followUp;
    }

    public void setFollowUp(int followUp) {
        this.followUp = followUp;
    }

    public int getWast() {
        return wast;
    }

    public void setWast(int wast) {
        this.wast = wast;
    }

    public int getIntent() {
        return intent;
    }

    public void setIntent(int intent) {
        this.intent = intent;
    }

    public String getMainItems() {
        return mainItems;
    }

    public void setMainItems(String mainItems) {
        this.mainItems = mainItems;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOpenShare() {
        return openShare;
    }

    public void setOpenShare(int openShare) {
        this.openShare = openShare;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public int getRecycle() {
        return recycle;
    }

    public void setRecycle(int recycle) {
        this.recycle = recycle;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(int issuccess) {
        this.issuccess = issuccess;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
