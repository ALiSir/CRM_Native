package com.powerleader.cdn.crm_cdn.bean;

import com.google.gson.Gson;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/12/23.
 */

public class Tp_user extends RealmObject {
    /*
    * DROP TABLE IF EXISTS `tp_user`;
CREATE TABLE `tp_user` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `Password` char(40) DEFAULT NULL COMMENT '密码',
  `Email` varchar(255) DEFAULT NULL COMMENT '管理员Email',
  `Roleid` tinyint(2) DEFAULT '0' COMMENT '用户角色',
  `Status` tinyint(2) DEFAULT '0' COMMENT '状态',
  `Competence` varchar(255) DEFAULT NULL COMMENT '权限列表',
  `Description` varchar(50) DEFAULT NULL COMMENT '说明',
  `Loginarea` varchar(20) DEFAULT NULL COMMENT '最后登录地',
  `Logincount` int(5) DEFAULT '0' COMMENT '登录次数',
  `Loginip` varchar(30) DEFAULT NULL COMMENT '最后登录IP',
  `Logintime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `Dtime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tp_user
-- ----------------------------
INSERT INTO `tp_user` VALUES ('1', 'admin', 'ddc78aa938eb676d26e4a71877d141c23ec65878', 'gusfa@qq.com', '1', '0', '2,3,4,5,7,8,9,10,12,13,14,15,24,25,26,27,28,18,19,20,82,83,29,30,52,53,60,61,62,63,65,66,67,68,74,75,76,78,79,80,81', '', '.', '332', null, '2016-12-05 11:30:55', '2013-12-08 17:18:54');
INSERT INTO `tp_user` VALUES ('26', '包志强', '8afec353ace8e2a5d3a43185e44019e4167dd06f', 'zhiqiang.bao@powerleader.com.c', '2', '0', '65,66,67,68,74,75,76,78,79,80,81', '', '.', '28', null, '2016-10-24 16:37:49', '2016-07-06 09:50:48');
INSERT INTO `tp_user` VALUES ('21', 'admins', 'd8406e8445cc99a16ab984cc28f6931615c766fc', 'dafad@qq.com', '2', '0', '2,3,4,5,7,8,9,10,12,13,14,15,24,25,26,27,28,18,19,20,82,83,29,30,52,53,60,61,62,63,65,66,67,68,74,75,76,78,79,80,81', '', '.', '18', null, '2016-06-28 14:44:03', '2016-06-07 15:31:36');
INSERT INTO `tp_user` VALUES ('25', '黄云龙', 'a083e3e5c9f5489e1e06394fb6573e157c97804a', 'yunlong.huang@powerleader.com', '2', '0', '65,66,67,68,74,75,76,78,79,80,81', '', '.', '33', null, '2016-12-02 15:11:57', '2016-07-06 09:49:37');
INSERT INTO `tp_user` VALUES ('27', '闻嘉', '91da0979c3902268a7c7bb1f94925aba4a64f02c', 'jia.wen@powerleader.com.cn', '2', '0', '65,66,67,68,74,75,76,78,79,80,81', '', '.', '75', null, '2016-12-13 11:43:28', '2016-07-06 09:52:13');
INSERT INTO `tp_user` VALUES ('28', '严颖', 'a083e3e5c9f5489e1e06394fb6573e157c97804a', 'yin.yan@powerleader.com.cn', '2', '0', '65,66,67,68,74,75,76,78,79,80,81', '', '.', '38', null, '2016-12-07 10:47:47', '2016-07-06 09:53:00');
INSERT INTO `tp_user` VALUES ('29', '钟旭', '10470c3b4b1fed12c3baac014be15fac67c6e815', 'xu.zhong@powerleader.com', '1', '0', '2,3,4,5,7,8,9,10,12,13,14,15,21,22,23,24,25,26,27,28,18,19,20,82,83,29,30,52,53,60,61,62,63,65,66,67,68,69,70,71,72,73,74,75,76,78,79,80,81,85', '', '.', '4', null, '2016-08-08 19:21:55', '2016-07-08 14:42:35');
INSERT INTO `tp_user` VALUES ('30', '罗伊君', '10470c3b4b1fed12c3baac014be15fac67c6e815', 'yijun.luo@powerleader.com.cn', '2', '0', '65,66,67,68,74,75,76,78,79,80,81', '', '.', '42', null, '2016-12-12 13:49:06', '2016-07-14 16:14:26');
INSERT INTO `tp_user` VALUES ('32', '舒玲', '10470c3b4b1fed12c3baac014be15fac67c6e815', 'ling.shu@powerleader.com.cn', '1', '0', '2,3,4,5,7,8,9,10,12,13,14,15,21,22,23,24,25,26,27,28,18,19,20,82,83,29,30,52,53,60,61,62,63,65,66,67,68,69,70,71,72,73,74,75,76,78,79,80,81,85', '', '.', '3', null, '2016-08-04 14:08:07', '2016-07-29 15:48:43');
INSERT INTO `tp_user` VALUES ('33', '张云', '10470c3b4b1fed12c3baac014be15fac67c6e815', 'yun.zhang@powerleader.com.cn', '1', '0', '2,3,4,5,7,8,9,10,12,13,14,15,21,22,23,24,25,26,27,28,18,19,20,82,83,29,30,52,53,60,61,62,63,65,66,67,68,69,70,71,72,73,74,75,76,78,79,80,81,85', '', null, '0', null, null, '2016-08-03 10:46:44');
INSERT INTO `tp_user` VALUES ('34', 'admins', '10470c3b4b1fed12c3baac014be15fac67c6e815', null, '1', '0', '2,3,4,5,7,8,9,10,12,13,14,15,21,22,23,24,25,26,27,28,18,19,20,82,83,29,30,52,53,60,61,62,63,65,66,67,68,69,70,71,72,73,74,75,76,78,79,80,81,85', null, '.', '30', null, '2016-12-13 11:42:15', null);
INSERT INTO `tp_user` VALUES ('35', '李岳霖', '10470c3b4b1fed12c3baac014be15fac67c6e815', 'yuelin.li@powerleader.com.cn', '1', '0', '2,3,4,5,7,8,9,10,12,13,14,15,21,22,23,24,25,26,27,28,18,19,20,82,83,29,30,52,53,60,61,62,63,65,66,67,68,69,70,71,72,73,74,75,76,78,79,80,81,85', '', '.', '4', null, '2016-12-13 10:56:10', '2016-11-02 15:18:25');

    * */
    int id;
    String username;//用户名
    String password;//密码
    String email;//管理员Email
    int roleid;//用户角色
    int status;//状态
    String competence;//权限列表
    String description;//说明
    String loginarea;//最后登录地
    String logincount;//登录次数
    String loginip;//最后登录IP
    String logintime;//最后登录时间
    String dtime;//时间

    public Tp_user() {
    }

    public Tp_user(String username, String password){
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoginarea() {
        return loginarea;
    }

    public void setLoginarea(String loginarea) {
        this.loginarea = loginarea;
    }

    public String getLogincount() {
        return logincount;
    }

    public void setLogincount(String logincount) {
        this.logincount = logincount;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /* @Override
    public String toString() {
        return "{\"Tp_user\":[{\"id\":"+id+","+
                "\"username\":\""+username+"\","+
                "\"password\":\""+password+"\","+
                "\"email\":\""+email+"\","+
                "\"roleid\":"+roleid+","+
                "\"status\":"+status+","+
                "\"competence\":\""+competence+"\","+
                "\"description\":\""+description+"\","+
                "\"loginarea\":\""+loginarea+"\","+
                "\"logincount\":\""+logincount+"\","+
                "\"loginip\":\""+loginip+"\","+
                "\"logintime\":\""+logintime+"\","+
                "\"dtime\":\""+dtime+"\"}]}";
    }*/
}
