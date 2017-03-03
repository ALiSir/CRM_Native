package com.powerleader.cdn.crm_cdn.person.cus;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.irx.cus.CusRx;
import com.powerleader.cdn.crm_cdn.net.cus.CusNetServer;
import com.powerleader.cdn.crm_cdn.view.cus.CustomFragment;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Subscriber;

/**
 * Created by ALiSir on 17/2/13.
 */

public class CusFragInterface implements CusNetServer.OnCuServerResult, View.OnClickListener {
    private static String TAG = CusFragInterface.class.getSimpleName();
    private CustomFragment customFragment;
    private CusNetServer cusNetServer;
    Context context;

    public CusFragInterface(CustomFragment customFragment,Context context){
        this.customFragment = customFragment;
        cusNetServer = new CusNetServer();
        cusNetServer.setOnCusResult(this);
        this.context = context;
    }

    public void getAllData(int id,String info){
        cusNetServer.cusPostForm(id,info);
    }

    public void setAllData(HashMap<String, Object> result){
        if (result.get("code").toString().equals("0")) {
            Log.i(TAG, "CusFragInterface: 查询cus数据成功!" );
            customFragment.setAllData((ArrayList<LinkedTreeMap<String,Object>>) result.get("object"));
            final String RESULTMSG = result.get("msg").toString();
            CusRx cusRx = CusRx.cusRxInit();
            cusRx.setLogOb(rx.Observable.create(new rx.Observable.OnSubscribe<String>(){
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext(RESULTMSG);
                }
            }));
            cusRx.getLogOb().subscribe(cusRx.getLogSub());
        }else{
            Toast.makeText(customFragment.getContext(), result.get("msg").toString(), Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<HashMap<String, String>> initData() {
        ArrayList<HashMap<String,String>> datas = new ArrayList<HashMap<String, String>>();
        datas = new  ArrayList<HashMap<String,String>>();
        for(int i = 0; i < customFragment.getAllData().size(); i++) {
            LinkedTreeMap<String ,Object> tmp = customFragment.getAllData().get(i);
            HashMap<String,String> maps = new HashMap<>();
            maps.put("companyName", tmp.get("companyName").toString());
            maps.put("contactName", tmp.get("contactName").toString());
            maps.put("id",tmp.get("id").toString());
            datas.add(maps);
        }

        return datas;
    }

    @Override
    public void havCuSeverResult(HashMap<String, Object> result) {
        this.setAllData(result);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cussearch:
                Log.i(TAG, "onClick: 点击了cus的搜索按钮");
                break;
        }
    }

    public interface CusLookOneDetailClick{
        void OnGoToDetailActivity(HashMap<String, Object> result);
    }

}

