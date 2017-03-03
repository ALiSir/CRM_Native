package com.powerleader.cdn.crm_cdn.net.cus;

import com.powerleader.cdn.crm_cdn.bean.Contents;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ALiSir on 17/2/13.
 */

public interface CusNet {

    @FormUrlEncoded
    @POST(Contents.CLIENT_ALL)
    Call<HashMap<String,Object>> postNamePwd(@Field("id") int id,@Field("info") String info);

    @FormUrlEncoded
    @POST(Contents.CLIENT_ALL)
    Call<HashMap<String,Object>> postGetResult(@FieldMap Map<String,String > data);
}
