package com.powerleader.cdn.crm_cdn.view.hav;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.mingle.widget.LoadingView;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.bean.UserInfo;
import com.powerleader.cdn.crm_cdn.irx.hav.HavRx;
import com.powerleader.cdn.crm_cdn.person.hav.HavFragmentInterface;
import com.powerleader.cdn.crm_cdn.person.hav.HavFragmentPerson;
import com.powerleader.cdn.crm_cdn.util.breakbricks.FunGameRefreshView;
import com.powerleader.cdn.crm_cdn.view.cus.CusLookOneDetailActivity;
import com.powerleader.cdn.crm_cdn.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

public class HavingDealFragment extends Fragment {
    private static final String TAG = HavingDealFragment.class.getSimpleName();
    private LayoutInflater inflater;
    private View view;
    private Context context;
    private SwipeListView listView;
    private ArrayList<HashMap<String,String>> arrayList;
    private HavFragmentInterface havFragmentInterface;
    private static ArrayList<LinkedTreeMap<String, Object>> allData;
//    ImageView havroadimg;
    private LoadingView loadingView;
    ProgressDialog dialog;
    MyAdapter myAdapter;

    public HavingDealFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.view = inflater.inflate(R.layout.fragment_having_deal, container, false);
        this.context = inflater.getContext();
        havFragmentInterface = new HavFragmentPerson(context);
        arrayList = havFragmentInterface.initData();
//        havroadimg = (ImageView) view.findViewById(R.id.havroadimg);
//        havroadimg.setBackgroundResource(R.drawable.wait);
//        AnimationDrawable aniDra = (AnimationDrawable)havroadimg.getBackground();
//        aniDra.start();

        initView();
        Log.i(TAG, "onCreateView: HavingDealFragment进来了");
        freshHavDatas();
        return view;
    }

    public void freshHavDatas(){
        try{
            listView.setVisibility(View.GONE);
        }catch (Exception e){
            Log.e(TAG, "listView--->freshHavDatas: ",e );
        }
        try{
            loadingView.setVisibility(View.VISIBLE);
        }catch (Exception e){
            Log.e(TAG, "loadingView--->freshHavDatas: ",e );
        }
        UserInfo user = UserInfo.init();
        Map<String,String> map =new  HashMap<String,String>();
        map.put("id",LoginActivity.getUid()+"");
        map.put("info","findAllHav");
        map.put("roleid",user.getRoleid()+"");
        havFragmentInterface.havFindAll(map);
        Log.i(TAG, "freshHavDatas: Hav刷新一下数据！");
        initRx();
    }

    public static ArrayList<LinkedTreeMap<String, Object>> getAllData() {
        return allData;
    }

    public static void setAllData(ArrayList<LinkedTreeMap<String, Object>> allData) {
        HavingDealFragment.allData = allData;
    }

    private void initView(){
        loadingView = (LoadingView) view.findViewById(R.id.loadView);
        loadingView.setLoadingText("正在加载...");

        listView = (SwipeListView) view.findViewById(R.id.listView);
        listView.setRightViewWidth(Contents.ITEM_RIGHT_WIDTH);
        View handerView = LayoutInflater.from(context).inflate(R.layout.item_having_header,null);
        View handerSearch = handerView.findViewById(R.id.havsearch);
        handerSearch.setOnClickListener(havFragmentInterface);
        listView.addHeaderView(handerView);
        String[] dewigetStr = {"having_item_right","havfirst","havname","havtime"};
        int[] dewigetInt = {R.id.having_item_right,R.id.havfirst,R.id.havname,R.id.havtime};
        myAdapter = new MyAdapter(context,R.layout.item_having,arrayList,dewigetStr,dewigetInt);
        myAdapter.setRightWidth(Contents.ITEM_RIGHT_WIDTH);
        myAdapter.setOnItemOncliclikListener(new MyAdapter.IOnItemRightClickListener() {
            @Override
            public void onItemClike(View v, int position,String sortid) {
                switch (v.getId()){
                    case R.id.havfirst:
                        havDeteleOne(sortid);
                        break;
                    default: havFragmentInterface.lookOneDetail(Integer.parseInt(sortid));
                }
            }
        });
        listView.setAdapter(myAdapter);
        initRx();
    }

    void initRx(){
        HavRx havRx = HavRx.cusRxInit();
        havRx.setLogSub(new Subscriber<HashMap<String, Object>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HashMap<String, Object> result) {
                switch ((String)result.get("info")){
                    case "findAllHav":
                        if ("0".equals((String)result.get("code")) ){
                            loadingView.setVisibility(View.GONE);
                            ArrayList<LinkedTreeMap<String,String>> datas = (ArrayList<LinkedTreeMap<String,String>>)result.get("object");
                            if (datas.size() == 0){
                                getDataErro("提示！","没有数据！请添加！");
                                return;
                            }
                            HavingDealFragment.setAllData((ArrayList<LinkedTreeMap<String,Object>>)result.get("object"));
                            arrayList = havFragmentInterface.initData(datas);
                            String[] dewigetStr = {"having_item_right","havfirst","havname","havtime"};
                            int[] dewigetInt = {R.id.having_item_right,R.id.havfirst,R.id.havname,R.id.havtime};
                            myAdapter = new MyAdapter(context,R.layout.item_having,arrayList,dewigetStr,dewigetInt);
                            myAdapter.setRightWidth(Contents.ITEM_RIGHT_WIDTH);
                            myAdapter.setOnItemOncliclikListener(new MyAdapter.IOnItemRightClickListener() {
                                @Override
                                public void onItemClike(View v, int position,String sortid) {
                                    switch (v.getId()){
                                        case R.id.havfirst:
                                            havDeteleOne(sortid);
                                            break;
                                        default: havFragmentInterface.lookOneDetail(Integer.parseInt(sortid));
                                    }
                                }
                            });
                            listView.setAdapter(myAdapter);
                            listView.setVisibility(View.VISIBLE);
                        }else{
                            getDataErro("错误提示！",(String) result.get("msg"));
                            //TODO:获取数据失败，退出应用
                        }
                        break;
                    case "deleteOneHav":
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                        if("1".equals((String)result.get("code"))){
                            Toast.makeText(context,"删除成功！",Toast.LENGTH_LONG).show();
                            freshHavDatas();
                        }else {
                            getDataErro("错误提示！",(String) result.get("msg"));
                            //TODO:获取数据失败，退出应用
                        }
                        break;
                }
            }

        });
    }

    void havDeteleOne(String id){
        dialog = ProgressDialog.show(context,"删除提示！","正在删除，请稍后...");
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("info","deleteOneHav");
        havFragmentInterface.havFindAll(map);
    }

    void getDataErro(String title,String msg){
        new AlertDialog.Builder(context).setTitle(title)//设置对话框标题
                .setMessage(msg)//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                    }
                }).show();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
            }
        }
    };

}
