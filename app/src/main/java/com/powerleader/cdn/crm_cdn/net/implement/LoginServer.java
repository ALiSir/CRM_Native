package com.powerleader.cdn.crm_cdn.net.implement;

import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.bean.JsonObject;
import com.powerleader.cdn.crm_cdn.bean.Tp_user;

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
    Call<JsonObject> postNamePwd(@Field("tp_user") Tp_user tp_user);
}
