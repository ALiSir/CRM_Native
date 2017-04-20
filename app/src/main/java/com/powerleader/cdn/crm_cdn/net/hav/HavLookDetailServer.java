package com.powerleader.cdn.crm_cdn.net.hav;

import com.powerleader.cdn.crm_cdn.bean.Contents;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/12/23.
 */

public interface HavLookDetailServer {

    @FormUrlEncoded
//    @POST(Contents.LOGIN_FORM)
    Call<HashMap<String,Object>> postNamePwd(@Field("id") int id);

    @FormUrlEncoded
    @POST(Contents.HAV_ALL)
    Call<HashMap<String,Object>> havPost(@FieldMap Map<String,String > data);
}