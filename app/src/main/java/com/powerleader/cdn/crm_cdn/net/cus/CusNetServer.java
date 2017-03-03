package com.powerleader.cdn.crm_cdn.net.cus;

import android.util.Log;

import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.irx.cus.AddOneCusRx;
import com.powerleader.cdn.crm_cdn.irx.cus.CusLookOneDetailRx;
import com.powerleader.cdn.crm_cdn.irx.cus.DeteleOneCusRx;
import com.powerleader.cdn.crm_cdn.irx.cus.EditGetOneCusDetail;
import com.powerleader.cdn.crm_cdn.irx.cus.UpdateOneCusRx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

import static android.R.attr.id;

/**
 * Created by ALiSir on 17/2/13.
 */

public class CusNetServer {
    private static final String TAG = CusNetServer.class.getSimpleName();
    public OnCuServerResult onCusResult = null;
    CusLookOneDetailRx cusLookOneDetailRx;

    public CusNetServer(){

    }

    public void cusPostForm(int id,String info){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        CusNet cusServer = retrofit.create(CusNet.class);

        Call<HashMap<String,Object>> tuUser = cusServer.postNamePwd(id,info);

        tuUser.enqueue(new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
//                Log.i(TAG, "onResponse: "+response.body().toString());
                onCusResult.havCuSeverResult(response.body());
            }

            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                HashMap<String,Object> result = new HashMap<String, Object>();
                result.put("code",-1);
                result.put("msg",t.getMessage());
                onCusResult.havCuSeverResult(result);
            }
        });
    }

    public void lookOneDetail(int id,String info){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        CusNet cusServer = retrofit.create(CusNet.class);

        Call<HashMap<String,Object>> tuUser = cusServer.postNamePwd(id,info);

        tuUser.enqueue(new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
                final HashMap<String,Object> result = response.body();
                cusLookOneDetailRx = CusLookOneDetailRx.cusRxInit();
                cusLookOneDetailRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                cusLookOneDetailRx.getLogOb().subscribe(cusLookOneDetailRx.getLogSub());
            }

            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                final HashMap<String,Object> result = new HashMap<String, Object>();
                result.put("code",-1);
                result.put("msg",t.getMessage());
                Log.i(TAG, "onFailure: "+result.toString());
                cusLookOneDetailRx = CusLookOneDetailRx.cusRxInit();
                cusLookOneDetailRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                cusLookOneDetailRx.getLogOb().subscribe(cusLookOneDetailRx.getLogSub());
            }
        });
    }

    public void  updateOneCus(Map<String,String> data){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        CusNet cusServer = retrofit.create(CusNet.class);

        Call<HashMap<String,Object>> tuUser = cusServer.postGetResult(data);

        tuUser.enqueue(new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
                final HashMap<String,Object> result = response.body();
                UpdateOneCusRx addOneCusRx = UpdateOneCusRx.cusRxInit();
                addOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                addOneCusRx.getLogOb().subscribe(addOneCusRx.getLogSub());
            }

            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                final HashMap<String,Object> result = new HashMap<String, Object>();
                result.put("code",-1);
                result.put("msg",t.getMessage());
                Log.i(TAG, "onFailure: "+result.toString());
                UpdateOneCusRx addOneCusRx = UpdateOneCusRx.cusRxInit();
                addOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                addOneCusRx.getLogOb().subscribe(addOneCusRx.getLogSub());
            }
        });
    }

    public void  addOneCus(Map<String,String> data){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        CusNet cusServer = retrofit.create(CusNet.class);

        Call<HashMap<String,Object>> tuUser = cusServer.postGetResult(data);

        tuUser.enqueue(new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
                final HashMap<String,Object> result = response.body();
                AddOneCusRx addOneCusRx = AddOneCusRx.cusRxInit();
                addOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                addOneCusRx.getLogOb().subscribe(addOneCusRx.getLogSub());
            }

            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                final HashMap<String,Object> result = new HashMap<String, Object>();
                result.put("code",-1);
                result.put("msg",t.getMessage());
                Log.i(TAG, "onFailure: "+result.toString());
                AddOneCusRx addOneCusRx = AddOneCusRx.cusRxInit();
                addOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                addOneCusRx.getLogOb().subscribe(addOneCusRx.getLogSub());
            }
        });
    }

    public void deteleOneCus(int id,String info){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        CusNet cusServer = retrofit.create(CusNet.class);

        Call<HashMap<String,Object>> tuUser = cusServer.postNamePwd(id,info);

        tuUser.enqueue(new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
                final HashMap<String,Object> result = response.body();
                DeteleOneCusRx deteleOneCusRx = DeteleOneCusRx.cusRxInit();
                deteleOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                deteleOneCusRx.getLogOb().subscribe(deteleOneCusRx.getLogSub());
            }

            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                final HashMap<String,Object> result = new HashMap<String, Object>();
                result.put("code",-1);
                result.put("msg",t.getMessage());
                Log.i(TAG, "onFailure: "+result.toString());
                DeteleOneCusRx deteleOneCusRx = DeteleOneCusRx.cusRxInit();
                deteleOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                deteleOneCusRx.getLogOb().subscribe(deteleOneCusRx.getLogSub());
            }
        });
    }

    public void editGetOneDetail(int id,String info){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contents.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        CusNet cusServer = retrofit.create(CusNet.class);

        Call<HashMap<String,Object>> tuUser = cusServer.postNamePwd(id,info);

        tuUser.enqueue(new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
                Log.i(TAG, "onResponse: "+response.body().toString());
                final HashMap<String,Object> result = response.body();
                EditGetOneCusDetail deteleOneCusRx = EditGetOneCusDetail.cusRxInit();
                deteleOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                deteleOneCusRx.getLogOb().subscribe(deteleOneCusRx.getLogSub());
            }

            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                final HashMap<String,Object> result = new HashMap<String, Object>();
                result.put("code",-1);
                result.put("msg",t.getMessage());
                Log.i(TAG, "onFailure: "+result.toString());
                EditGetOneCusDetail deteleOneCusRx = EditGetOneCusDetail.cusRxInit();
                deteleOneCusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<HashMap<String,Object>>(){
                    @Override
                    public void call(Subscriber<? super HashMap<String,Object>> subscriber) {
                        subscriber.onNext(result);
                    }
                }));
                deteleOneCusRx.getLogOb().subscribe(deteleOneCusRx.getLogSub());
            }
        });
    }

    public void setOnCusResult(OnCuServerResult cusResult) {
        this.onCusResult = cusResult;
    }

    public interface OnCuServerResult {
        void havCuSeverResult(HashMap<String, Object> result);
    }
}
