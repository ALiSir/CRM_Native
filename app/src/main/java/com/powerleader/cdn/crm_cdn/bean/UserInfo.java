package com.powerleader.cdn.crm_cdn.bean;

/**
 * Created by ALiSir on 17/4/18.
 */

public class UserInfo {
    private static UserInfo info;

    int id; //收到的id   =》  UID
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
    String headImage;
    String remarck;

    public static UserInfo init(){
        if(info == null){
            info = new UserInfo();
        }
        return info;
    }

    private UserInfo() {
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

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getRemarck() {
        return remarck;
    }

    public void setRemarck(String remarck) {
        this.remarck = remarck;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleid=" + roleid +
                ", status=" + status +
                ", competence='" + competence + '\'' +
                ", description='" + description + '\'' +
                ", loginarea='" + loginarea + '\'' +
                ", logincount='" + logincount + '\'' +
                ", loginip='" + loginip + '\'' +
                ", logintime='" + logintime + '\'' +
                ", dtime='" + dtime + '\'' +
                ", headImage='" + headImage + '\'' +
                ", remarck='" + remarck + '\'' +
                '}';
    }
}
