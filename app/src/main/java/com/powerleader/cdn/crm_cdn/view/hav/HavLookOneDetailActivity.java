package com.powerleader.cdn.crm_cdn.view.hav;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.irx.hav.HavRx;
import com.powerleader.cdn.crm_cdn.net.hav.HavLookDetailForm;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

public class HavLookOneDetailActivity extends MActivity implements View.OnClickListener{
    private final static String TAG = HavLookOneDetailActivity.class.getSimpleName();
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tvGoBack;
    EditText et1,et2;
    HavLookDetailForm havNet;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hav_look_one_detail);
        havNet = new HavLookDetailForm();
        initView();
        initRX();
        initData();
    }

    void initRX(){
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
                switch ((String) result.get("info")) {
                    case "oneDetailData":
                        progressDialog.dismiss();
                        if(result.get("code").equals("0")){
                            dataCome((LinkedTreeMap<String,String>)result.get("object"));
                        }else {
                            new AlertDialog.Builder(HavLookOneDetailActivity.this).setTitle("查询数据提示")//设置对话框标题
                                    .setMessage("查询数据失败！")//设置显示的内容
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                            finish();
                                        }
                                    }).show();
                        }
                        break;
                }
            }

        });
    }

    void dataCome(LinkedTreeMap<String,String> data){
        tv1.setText(data.get("companyName"));
        tv2.setText(data.get("contactName"));
        tv3.setText(data.get("wast"));
        tv4.setText(data.get("nextTime"));
        tv5.setText(data.get("remindTime"));
        tv6.setText(data.get("carfare"));
        tv7.setText(data.get("expenses"));
        tv8.setText(data.get("remind"));
        tv9.setText(data.get("industry"));
        tv10.setText(data.get("goods"));
        tv11.setText(data.get("clientSource"));
        tv12.setText(data.get("status").equals("1") ? "是":"否" );
        tv13.setText(data.get("num"));

        et1.setText(data.get("addres"));
        et2.setText(data.get("content"));
    }

    void showAlertDialog(String title, String msg) {
        new AlertDialog.Builder(this).setTitle(title)//设置对话框标题
                .setMessage(msg)//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                    }
                }).show();
    }

    void initData(){
        Map<String,String> map = new HashMap<>();
        map.put("id",getIntent().getStringExtra("id"));
        map.put("info","oneDetailData");
        havNet.havPost(map);
        progressDialog = ProgressDialog.show(this,"加载数据提示","正在加载数据，请稍后...");
    }

    private void initView(){
        tv1 = (TextView) findViewById(R.id.dtv1);
        tv2 = (TextView) findViewById(R.id.dtv2);
        tv3 = (TextView) findViewById(R.id.dtv3);
        tv4 = (TextView) findViewById(R.id.dtv4);
        tv5 = (TextView) findViewById(R.id.dtv5);
        tv6 = (TextView) findViewById(R.id.dtv6);
        tv7 = (TextView) findViewById(R.id.dtv7);
        tv8 = (TextView) findViewById(R.id.dtv8);
        tv9 = (TextView) findViewById(R.id.dtv9);
        tv10 = (TextView) findViewById(R.id.dtv10);
        tv11 = (TextView) findViewById(R.id.dtv11);
        tv12 = (TextView) findViewById(R.id.dtv12);
        tv13 = (TextView) findViewById(R.id.dtv13);

        et1 = (EditText) findViewById(R.id.dtet1);
        et2 = (EditText) findViewById(R.id.dtet2);

        tvGoBack = (TextView) findViewById(R.id.havdetailgoback);
        tvGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.havdetailgoback:
                finish();
                break;
        }
    }
}
