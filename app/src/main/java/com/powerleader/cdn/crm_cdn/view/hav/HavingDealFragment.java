package com.powerleader.cdn.crm_cdn.view.hav;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.person.hav.HavFragmentInterface;
import com.powerleader.cdn.crm_cdn.person.hav.HavFragmentPerson;

import java.util.ArrayList;
import java.util.HashMap;

public class HavingDealFragment extends Fragment {
    private static final String TAG = HavingDealFragment.class.getSimpleName();
    private LayoutInflater inflater;
    private View view;
    private Context context;
    private SwipeListView listView;
    private ArrayList<HashMap<String,String>> arrayList;
    private HavFragmentInterface havFragmentInterface;
    private static ArrayList<LinkedTreeMap<String, Object>> allData;

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
        initView();
        Log.i(TAG, "onCreateView: HavingDealFragment进来了");
        return view;
    }

    public void freshHavDatas(){
        Log.i(TAG, "freshHavDatas: Hav刷新一下数据！");
    }

    public static ArrayList<LinkedTreeMap<String, Object>> getAllData() {
        return allData;
    }

    public static void setAllData(ArrayList<LinkedTreeMap<String, Object>> allData) {
        HavingDealFragment.allData = allData;
    }

    private void initView(){
        listView = (SwipeListView) view.findViewById(R.id.listView);
        listView.setRightViewWidth(Contents.ITEM_RIGHT_WIDTH);
        View handerView = LayoutInflater.from(context).inflate(R.layout.item_having_header,null);
        View handerSearch = handerView.findViewById(R.id.havsearch);
        handerSearch.setOnClickListener(havFragmentInterface);
        listView.addHeaderView(handerView);
        String[] dewigetStr = {"having_item_right","havfirst","havname","havtime"};
        int[] dewigetInt = {R.id.having_item_right,R.id.havfirst,R.id.havname,R.id.havtime};
        final MyAdapter myAdapter = new MyAdapter(context,R.layout.item_having,arrayList,dewigetStr,dewigetInt);
        myAdapter.setRightWidth(Contents.ITEM_RIGHT_WIDTH);
        myAdapter.setOnItemOncliclikListener(new MyAdapter.IOnItemRightClickListener() {
            @Override
            public void onItemClike(View v, int position,String sortid) {
                switch (v.getId()){
                    case R.id.havfirst:
                        Toast.makeText(context,"点击了第"+position+"删除按钮；id："+sortid,Toast.LENGTH_SHORT).show();
                        break;
                    default: havFragmentInterface.lookOneDetail(Integer.parseInt(sortid));
                }
            }
        });
        listView.setAdapter(myAdapter);
    }

}
