package com.powerleader.cdn.crm_cdn.net.login;

import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.bean.JsonObject;
import com.powerleader.cdn.crm_cdn.bean.LoginResult;
import com.powerleader.cdn.crm_cdn.bean.Tp_user;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/12/23.
 */

public interface LoginServer {

    @FormUrlEncoded
    @POST(Contents.LOGIN_FORM)
    Call<HashMap<String,Object>> postNamePwd(@Field("username") String username, @Field("password") String password, @Field("deviceid") String deviceid,@Field("CRM_Version")String version);
}