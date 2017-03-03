package com.powerleader.cdn.crm_cdn.net.hav;

import android.util.Log;

import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/23.
 */

public class HavLookDetailForm {
    private static final String TAG = HavLookDetailForm.class.getSimpleName();
    public OnHavLookDetailResult onLoginResult = null;

    public void loginPostQuery(int id) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        HavLookDetailServer loginServer = retrofit.create(HavLookDetailServer.class);

        Call<HashMap<String,Object>> tuUser = loginServer.postNamePwd(id);

        tuUser.enqueue(new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
                onLoginResult.havLookDetailResult(response.body());
            }

            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                HashMap<String,Object> result = new HashMap<String, Object>();
                result.put("code",-1);
                result.put("msg",t.getMessage());
                onLoginResult.havLookDetailResult(result);
            }
        });

    }

    public void setOnLoginResult(OnHavLookDetailResult onLoginResult) {
        this.onLoginResult = onLoginResult;
    }

    public interface OnHavLookDetailResult {
        void havLookDetailResult(HashMap<String, Object> result);
    }
}
