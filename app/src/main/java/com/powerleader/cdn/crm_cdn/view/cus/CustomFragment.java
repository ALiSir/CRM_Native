package com.powerleader.cdn.crm_cdn.view.cus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.ACFCustomActivity;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.irx.cus.CusRx;
import com.powerleader.cdn.crm_cdn.person.ACFCustomInterface;
import com.powerleader.cdn.crm_cdn.person.cus.CusFragInterface;
import com.powerleader.cdn.crm_cdn.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import rx.Subscriber;


public class CustomFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = CustomFragment.class.getSimpleName();
    private View view;
    private Context context;
    private LayoutInflater inflater;
    private SwipeListView listView;
    private TextView cusload;
    private ArrayList<LinkedTreeMap<String, Object>> allData;
    private ArrayList<HashMap<String,String>> arrayList;
    private CusFragInterface cusFragInterface;
    private Timer timer = new Timer(true);
    private MyAdapter myAdapter;
    private boolean isFirstRun = true;

    public CustomFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_custom, container, false);
        context = inflater.getContext();
        cusFragInterface = new CusFragInterface(this,context);
        cusload = (TextView) view.findViewById(R.id.cusroad);
        //启动定时器
        timer.schedule(task, 1000, 1000);
        initRx();
        return view;
    }

    private void initRx(){
        CusRx cusRx = CusRx.cusRxInit();
        cusRx.setLogSub(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String maps) {
                Log.i(TAG, "onNext: rx收到数据改变请求！");
                handler.sendEmptyMessage(0x456);
            }
        });
    }

    private void initView(ArrayList<HashMap<String,String>> datas) {
        listView = (SwipeListView) view.findViewById(R.id.cuslistView);
        listView.setRightViewWidth(0);
        if(isFirstRun){
            View handerView = LayoutInflater.from(context).inflate(R.layout.cus_head,null);
            View handerSearch = handerView.findViewById(R.id.cussearch);
            handerSearch.setOnClickListener(cusFragInterface);
            listView.addHeaderView(handerView);
        }
        isFirstRun = false;
        String[] dewigetStr = {"havname","havtime"};
        int[] dewigetInt = {R.id.cusname,R.id.custime};
        myAdapter = new MyAdapter(context,R.layout.item_cus,datas,dewigetStr,dewigetInt);
        myAdapter.setRightWidth(Contents.ITEM_RIGHT_WIDTH);
        myAdapter.setOnItemOncliclikListener(new MyAdapter.IOnItemRightClickListener() {
            @Override
            public void onItemClike(View v, int position,String sortid) {
                Log.i(TAG, "onItemClike: 查看Cus详细！");
//                cusFragInterface.lookOneDetail(Integer.parseInt(sortid),"detail");
                Intent intent = new Intent();
                intent.putExtra("id",Integer.parseInt(sortid));
                intent.putExtra("info","detail");
                intent.setClass(context,CusLookOneDetailActivity.class);
                context.startActivity(intent);
            }
        });
        listView.setAdapter(myAdapter);
    }

    public void refreshCusDatas(){
        try{
            task.run();
            listView.setVisibility(View.GONE);
            cusload.setVisibility(View.VISIBLE);
        }catch (Exception e){
            Log.i(TAG, "refreshCusDatas: "+e.getLocalizedMessage());
        }
        cusFragInterface.getAllData(LoginActivity.getUid(),"all");
        Log.i(TAG, "refreshCusDatas: Cus刷新一下数据！");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button10:
                break;
            case R.id.button11:
                Intent intent = new Intent(context, ACFCustomActivity.class);
                intent.putExtra("customNumber", ACFCustomInterface.CHENUMBER);
                context.startActivity(intent);
                break;
            case R.id.button12:
                Intent intent1 = new Intent(context, ACFCustomActivity.class);
                intent1.putExtra("customNumber", ACFCustomInterface.FINNUMBER);
                context.startActivity(intent1);
                break;
            case R.id.button7:
                Intent intent2 = new Intent(context, ACFCustomActivity.class);
                intent2.putExtra("customNumber", ACFCustomInterface.ADDNUMBER);
                context.startActivity(intent2);
                break;

            case R.id.cutbtn:
//                HomePerson.showCusView();
                break;
            case R.id.clebtn:
//                HomePerson.showcloView();
                break;
            case R.id.havbtn:
//                HomePerson.showhavView();
                break;
            case R.id.delbtn:
//                HomePerson.showcelView();
                break;
        }
    }

    public void setAllData(ArrayList<LinkedTreeMap<String, Object>> allData){
        this.allData = allData;
    }

    public ArrayList<LinkedTreeMap<String, Object>> getAllData(){
        return allData;
    }

    private Handler handler  = new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x123:
                    if(cusload.length() == 5){
                        cusload.setText("正在加载..");
                    }else if(cusload.length() == 6){
                        cusload.setText("正在加载...");
                    }else {
                        cusload.setText("正在加载.");
                    }
                    break;
                case 0x456:
                    arrayList = cusFragInterface.initData();
                    initView(arrayList);
                    listView.setVisibility(View.VISIBLE);
                    cusload.setVisibility(View.GONE);
                    myAdapter.notifyDataSetChanged();
                    task.cancel();
                    break;
            }
        }
    };

    //任务
    private TimerTask task = new TimerTask() {
        public void run() {
            Message msg = new Message();
            msg.what = 0x123;
            handler.sendMessage(msg);
        }
    };

}
