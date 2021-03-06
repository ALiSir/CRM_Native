package com.powerleader.cdn.crm_cdn.person.hav;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.Tp_client;
import com.powerleader.cdn.crm_cdn.net.hav.HavLookDetailForm;
import com.powerleader.cdn.crm_cdn.net.hav.HavLookDetailServer;
import com.powerleader.cdn.crm_cdn.person.hav.HavFragmentInterface;
import com.powerleader.cdn.crm_cdn.util.database.DataTp_client;
import com.powerleader.cdn.crm_cdn.util.database.impl.DataTp_clientImpl;
import com.powerleader.cdn.crm_cdn.view.hav.HavLookOneDetailActivity;
import com.powerleader.cdn.crm_cdn.view.hav.HavSearchActivity;
import com.powerleader.cdn.crm_cdn.view.hav.HavingDealFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;

/**
 * Created by ALiSir on 17/1/4.
 */

public class HavFragmentPerson implements HavFragmentInterface, HavLookDetailForm.OnHavLookDetailResult {
    private Context context;
    private DataTp_client dataTp_client;
    private HavLookDetailForm havLookDetailForm;

    public HavFragmentPerson(Context context) {
        this.context = context;
        dataTp_client = new DataTp_clientImpl();
        havLookDetailForm = new HavLookDetailForm();
        havLookDetailForm.setOnLoginResult(this);
    }

    @Override
    public ArrayList<HashMap<String, String>> initData() {
        ArrayList<HashMap<String, String>> datas = new ArrayList<HashMap<String, String>>();

        datas = new ArrayList<HashMap<String, String>>();
        try {
            for (int i = 0; i < HavingDealFragment.getAllData().size(); i++) {
                LinkedTreeMap<String, Object> tmp = HavingDealFragment.getAllData().get(i);
                HashMap<String, String> maps = new HashMap<>();
                maps.put("uname", tmp.get("CompanyName").toString());
                maps.put("time", tmp.get("Dtime").toString());
                maps.put("id", tmp.get("ID").toString());
                datas.add(maps);
            }
        } catch (Exception e) {
            Log.e("异常", "initData: ", e);
        }

        return datas;
    }

    @Override
    public ArrayList<HashMap<String, String>> initData(ArrayList<LinkedTreeMap<String, String>> data) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            LinkedTreeMap<String, String> tmp = data.get(i);
            HashMap<String, String> maps = new HashMap<>();
            maps.put("uname", tmp.get("CompanyName").toString());
            maps.put("time", tmp.get("Dtime").toString());
            maps.put("id", tmp.get("ID").toString());
            result.add(maps);
        }
        return result;
    }

    public void havFindAll(Map<String, String> map) {
        havLookDetailForm.havPost(map);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.havsearch:
                Intent intent = new Intent();
                intent.setClass(context, HavSearchActivity.class);
                context.startActivity(intent);
                break;
        }
    }

    @Override
    public void havLookDetailResult(HashMap<String, Object> result) {

    }

    @Override
    public void lookOneDetail(int id) {
        //TODO 联网查询
        Intent intent = new Intent();
        intent.putExtra("id",id+"");
        intent.setClass(context, HavLookOneDetailActivity.class);
        context.startActivity(intent);
    }
}
