package com.powerleader.cdn.crm_cdn.view.hav;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.hav.SortModel;
import com.powerleader.cdn.crm_cdn.util.CharacterParser;
import com.powerleader.cdn.crm_cdn.util.PinyinComparator;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.Source;

public class HavSearchActivity extends MActivity implements View.OnClickListener,TextWatcher {
    private static final String TAG = HavSearchActivity.class.getSimpleName();
    private ListView listView;
    private LinearLayout linearLayout;
    private TextView heardText,searchText,sreachBack;

    private PinyinComparator pinyinComparator;
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    private SortAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hav_search);

        initView();

    }

    private void initView(){

//        listView.getBackground().setAlpha(50);
        linearLayout = (LinearLayout) findViewById(R.id.activity_hav_search);
        heardText = (TextView) findViewById(R.id.statussearchheard);
        heardText.setHeight(MActivity.INFO_SHOW);
        sreachBack = (TextView) findViewById(R.id.searchback);
        sreachBack.setOnClickListener(this);
        searchText = (TextView) findViewById(R.id.searchtext);
        searchText.addTextChangedListener(this);

        characterParser = CharacterParser.getInstance();
        SourceDateList = filledData(HavingDealFragment.getAllData());
        pinyinComparator = new PinyinComparator();
        listView = (ListView) findViewById(R.id.hav_search_list);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
//                Log.i(TAG, "onItemClick: 点击了第："+position+"个，它的ID为："+id);
//                Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        adapter.setOnClickFroID(new SortAdapter.OnClickFroID() {
            @Override
            public void OnClickReturnId(int id) {
                Toast.makeText(getApplicationContext(),"点击的ID为："+id,Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);
    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.i(TAG, "onTextChanged: "+charSequence.toString());
        filterData(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchback:
                comBack();
                break;
        }
    }

    private List<SortModel> filledData(ArrayList<LinkedTreeMap<String, Object>> date){
        ArrayList<LinkedTreeMap<String, Object>> allDatas = HavingDealFragment.getAllData();
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for(int i=0; i<allDatas.size(); i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(allDatas.get(i).get("CompanyName").toString());
            sortModel.setId(Integer.parseInt(allDatas.get(i).get("ID").toString()));
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(allDatas.get(i).get("CompanyName").toString());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;


    }

    private List<SortModel> filledData(String [] date){
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for(int i=0; i<date.length; i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    private void comBack(){
        this.finish();
    }

    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                String namePin = characterParser.getSelling(name);
                String[] tmp = namePin.split(" ");
                String nameHeader = "";
                for (String strTmp :
                        tmp) {
                    if(!strTmp.isEmpty()) {
                        nameHeader += strTmp.substring(0,1);
                    }
                }
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }else if(nameHeader.startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

}
