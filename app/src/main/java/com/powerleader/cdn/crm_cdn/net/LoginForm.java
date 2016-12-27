package com.powerleader.cdn.crm_cdn.net;

import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.bean.JsonObject;
import com.powerleader.cdn.crm_cdn.bean.Tp_user;
import com.powerleader.cdn.crm_cdn.net.implement.LoginServer;

import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2016/12/23.
 */

public class LoginForm {
    public OnLoginResult onLoginResult = null;

    public void loginPostQuery(Tp_user tp_user) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginServer loginServer = retrofit.create(LoginServer.class);

        Call<JsonObject> tuUser = loginServer.postNamePwd(tp_user);

        tuUser.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                onLoginResult.loginResult(response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //t.printStackTrace();
                onLoginResult.loginResult(new JsonObject(-1, null, t.getMessage()));
            }
        });

    }

    public void setOnLoginResult(OnLoginResult onLoginResult) {
        this.onLoginResult = onLoginResult;
    }

    public interface OnLoginResult {
        void loginResult(JsonObject jsonObject);
    }
}
