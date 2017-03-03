package com.powerleader.cdn.crm_cdn.view.cus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.irx.cus.AddOneCusRx;
import com.powerleader.cdn.crm_cdn.irx.cus.EditGetOneCusDetail;
import com.powerleader.cdn.crm_cdn.irx.cus.UpdateOneCusRx;
import com.powerleader.cdn.crm_cdn.net.cus.CusNetServer;
import com.powerleader.cdn.crm_cdn.view.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

public class EditCusActivity extends Activity implements View.OnClickListener {
    private static final String TAG = EditCusActivity.class.getSimpleName();
    EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8;
    Spinner spn1, spn2, spn3, spn4, spn5, spn6;
    TextView str1, str2,cuseditile;
    boolean isEdit = false;
    CusNetServer cusNet;
    ProgressDialog dialog;
    static EditRefreshData edtdata;
    LinkedTreeMap<String, Object> result;
    String cid,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_add_one);
        cusNet = new CusNetServer();
        initView();
        startSelf();

    }

    void startSelf() {
        EditGetOneCusDetail deletRx = EditGetOneCusDetail.cusRxInit();
        deletRx.setLogSub(new Subscriber<HashMap<String, Object>>() {
                              @Override
                              public void onCompleted() {
                              }

                              @Override
                              public void onError(Throwable e) {
                              }

                              @Override
                              public void onNext(HashMap<String, Object> result) {
                                  dialog.dismiss();
                                  if (result.get("code").toString().equals("0")) {
                                      Message msg = new Message();
                                      msg.what = 0x123;
                                      msg.obj = result.get("object");
                                      handler.sendMessage(msg);
//                                      changeDataCome((LinkedTreeMap<String, String>) result.get("object"));
//                                      changeDataCome(()result.get("object"));
//                                      new AlertDialog.Builder(CusLookOneDetailActivity.this).setTitle("删除提醒！")//设置对话框标题
//                                              .setMessage("删除成功！")//设置显示的内容
//                                              .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
//                                                  @Override
//                                                  public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
//                                                      finish();
//                                                      EditCusActivity.getEdtData().onRefreshData();
//                                                  }
//                                              }).show();//在按键响应事件中显示此对话框
                                  } else {
                                      alertShowMsg("读取数据失败！", result.get("msg").toString());
                                  }
                              }
                          }
        );

        AddOneCusRx cusRx = AddOneCusRx.cusRxInit();
        cusRx.setLogSub(new Subscriber<HashMap<String, Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HashMap<String, Object> result) {
                dialog.dismiss();
                if (result.get("code").toString().equals("0")) {
                    alertShowMsg("保存提示!", "保存成功！");
                    saveSucces();
                } else {
                    alertShowMsg("保存出错!", result.get("msg").toString());
                }
            }
        });

        UpdateOneCusRx updateCusRx = UpdateOneCusRx.cusRxInit();
        updateCusRx.setLogSub(new Subscriber<HashMap<String, Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HashMap<String, Object> result) {
                dialog.dismiss();
                if (result.get("code").toString().equals("0")) {
                    alertShowMsg("修改提示!", "修改成功！");
                } else {
                    alertShowMsg("修改出错!", result.get("msg").toString());
                }
            }
        });

        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (isEdit) {
            str2.setText("修改");
            cuseditile.setText("修改客户");
            initChange(getIntent().getIntExtra("id",0));
        }else{
            str2.setText("保存");
            cuseditile.setText("添加客户");
        }

    }

    void changeDataCome(LinkedTreeMap<String,String> data){
        id = data.get("id");
        cid = data.get("cid");
        edt1.setText(data.get("companyName"));
        edt2.setText(data.get("address"));
        edt3.setText(data.get("contactName"));
        edt4.setText(data.get("tel"));
        edt5.setText(data.get("qq"));
        edt6.setText(data.get("skype"));
        edt7.setText(data.get("webUrl"));
        edt8.setText(data.get("message"));

        spn1.setSelection(getSpnSelect(data.get("industry"),R.array.industry));
        spn2.setSelection(getSpnSelect(data.get("clientType"),R.array.clientType));
        spn3.setSelection(getSpnSelect(data.get("clientLevel"),R.array.clientLevel));
        spn4.setSelection(getSpnSelect(data.get("followUp"),R.array.followUp));
        spn5.setSelection(getSpnSelect(data.get("wast"),R.array.wast));
        spn6.setSelection(getSpnSelect(data.get("intent"),R.array.intent));
    }

    int getSpnSelect(String value,int resouceValue){
        Resources res = getResources () ;
        String [] arrays = res.getStringArray (resouceValue);
        for (int i = 0;i <arrays.length;i++ ){
            if (arrays[i].equals(value) ){
                return i;
            }
        }
        return 0;
    }

    void initChange(int id){
        dialog = ProgressDialog.show(this, "加载提示！", "正在加载，请稍后...");
        Log.i(TAG, "initChange: "+id);
        cusNet.editGetOneDetail(id,"detail");
    }

    void saveSucces() {
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        edt5.setText("");
        edt6.setText("");
        edt7.setText("");
        edt8.setText("");
    }

    void initView() {
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
        edt4 = (EditText) findViewById(R.id.edt4);
        edt5 = (EditText) findViewById(R.id.edt5);
        edt6 = (EditText) findViewById(R.id.edt6);
        edt7 = (EditText) findViewById(R.id.edt7);
        edt8 = (EditText) findViewById(R.id.edt8);

        spn1 = (Spinner) findViewById(R.id.spn1);
        spn2 = (Spinner) findViewById(R.id.spn2);
        spn3 = (Spinner) findViewById(R.id.spn3);
        spn4 = (Spinner) findViewById(R.id.spn4);
        spn5 = (Spinner) findViewById(R.id.spn5);
        spn6 = (Spinner) findViewById(R.id.spn6);

        str1 = (TextView) findViewById(R.id.goBack);
        str2 = (TextView) findViewById(R.id.goEdit);
        cuseditile = (TextView) findViewById(R.id.cuseditile);
        str1.setOnClickListener(this);
        str2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goBack:
                edtdata.onRefreshData();
                finish();
                break;
            case R.id.goEdit:
                saveData();
                break;
        }
    }

    void alertShowMsg(String title, String msg) {
        new AlertDialog.Builder(EditCusActivity.this).setTitle(title)//设置对话框标题
                .setMessage(msg)//设置显示的内容
                .setPositiveButton("确定", null).show();//在按键响应事件中显示此对话框
    }

    void saveData() {
        if (edt1.getText().toString().equals("")) {
            alertShowMsg("错误提示！", "请输入公司/客户名！");
            return;
        }
        if (edt2.getText().toString().equals("")) {
            alertShowMsg("错误提示！", "请输入详细地址！");
            return;
        }
        if (edt3.getText().toString().equals("")) {
            alertShowMsg("错误提示！", "请输入联系人姓名！");
            return;
        }
        if (edt4.getText().toString().equals("") && edt5.getText().toString().equals("") && edt6.getText().toString().equals("")) {
            alertShowMsg("错误提示！", "请至少输入一种通讯地址（电话/QQ/微信）！");
            return;
        }
        if (edt7.getText().toString().equals("")) {
            alertShowMsg("错误提示！", "请公司网址！");
            return;
        }

        if (isEdit) {
            Map<String, String> data = new HashMap<>();
            data.put("Uid", LoginActivity.getUid() + "");
            data.put("CompanyName", edt1.getText().toString());
            data.put("Address", edt2.getText().toString());
            data.put("ContactName", edt3.getText().toString());
            data.put("Phone", edt4.getText().toString());
            data.put("Qq", edt5.getText().toString());
            data.put("Skype", edt6.getText().toString());
            data.put("WebUrl", edt7.getText().toString());
            data.put("Describe", edt8.getText().toString());

            data.put("info", "updateOneCus");
            data.put("id",id);
            data.put("Cid",cid);

            data.put("Industry", spn1.getSelectedItem().toString());
            data.put("ClientType", spn2.getSelectedItem().toString());
            data.put("ClientLevel", spn3.getSelectedItem().toString());
            data.put("FollowUp", spn4.getSelectedItem().toString());
            data.put("Wast", spn5.getSelectedItem().toString());
            data.put("Intent", spn6.getSelectedItem().toString());

            cusNet.updateOneCus(data);

            dialog = ProgressDialog.show(this, "修改提示！", "正在提交修改数据，请稍后...");
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("Uid", LoginActivity.getUid() + "");
            data.put("CompanyName", edt1.getText().toString());
            data.put("Address", edt2.getText().toString());
            data.put("ContactName", edt3.getText().toString());
            data.put("Phone", edt4.getText().toString());
            data.put("Qq", edt5.getText().toString());
            data.put("Skype", edt6.getText().toString());
            data.put("WebUrl", edt7.getText().toString());
            data.put("Describe", edt8.getText().toString());

            data.put("info", "addOneCus");

            data.put("Industry", spn1.getSelectedItem().toString());
            data.put("ClientType", spn2.getSelectedItem().toString());
            data.put("ClientLevel", spn3.getSelectedItem().toString());
            data.put("FollowUp", spn4.getSelectedItem().toString());
            data.put("Wast", spn5.getSelectedItem().toString());
            data.put("Intent", spn6.getSelectedItem().toString());

            cusNet.addOneCus(data);
            dialog = ProgressDialog.show(this, "保存提示！", "正在保存，请稍后...");
        }
    }

    public static EditRefreshData getEdtData(){
        return EditCusActivity.edtdata;
    }

    public static void setEdtdata(EditRefreshData edtdata) {
        EditCusActivity.edtdata = edtdata;
    }

    public interface EditRefreshData{
        void onRefreshData();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x123:
                    changeDataCome(((ArrayList<LinkedTreeMap<String,String>>)msg.obj).get(0));
                    break;
            }
        }
    };

}