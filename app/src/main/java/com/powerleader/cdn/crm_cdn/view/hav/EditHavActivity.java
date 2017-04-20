package com.powerleader.cdn.crm_cdn.view.hav;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.irx.hav.HavRx;
import com.powerleader.cdn.crm_cdn.net.cus.CusNetServer;
import com.powerleader.cdn.crm_cdn.net.hav.HavLookDetailForm;
import com.powerleader.cdn.crm_cdn.net.hav.HavLookDetailServer;
import com.powerleader.cdn.crm_cdn.view.cus.EditCusActivity;
import com.powerleader.cdn.crm_cdn.view.login.LoginActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

public class EditHavActivity extends Activity implements View.OnClickListener,NumberPicker.OnValueChangeListener {
    private final static String TAG = EditCusActivity.class.getSimpleName();
    TextView tv1, tv2, tv3, tv4, tv5, tvBack, tvSave;
    EditText et1, et2, et3, et4;
    Spinner spn1, spn2, spn3, spn4, spn5, spn6;
    ProgressDialog progressDialog;
    boolean isEdit = false;
    HavLookDetailForm havNet;
    String id;

    AlertDialog dateAlertDialog;
    boolean isNextTime;
    SimpleDateFormat sDateFormat;
    StringBuffer date;
    NumberPicker np2;


    private String provider;//位置提供器
    private LocationManager locationManager;//位置服务
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hav);
        havNet = new HavLookDetailForm();
        initView();
        initRx();
        initData();
        initAddress();
    }

    void initAddress() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);//获得位置服务
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            provider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "无法获取地理位置！默认客户所在公司地理位置！", Toast.LENGTH_LONG).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            showAlertDialog("获取地理位置失败！", "权限不足！");
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            compileAdd(location);
        }else{
            Toast.makeText(this, "无法获取地理位置！默认客户所在公司地理位置！", Toast.LENGTH_LONG).show();
        }
    }

    void initData() {
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        id = getIntent().getStringExtra("id");
        if (isEdit) {

        } else {
            progressDialog = ProgressDialog.show(this, "加载数据提醒", "正在加载数据,请稍后...");
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("info", "findWithByCusId");
            havNet.havPost(map);
        }
    }

    void initRx() {
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
                    case "findWithByCusId":
                        progressDialog.dismiss();
                        if (result.get("code").equals("1") || result.get("code").equals("2")) {
                            dataCome((LinkedTreeMap<String, String>) result.get("object"));
                        } else {
                            showAlertDialog("获取服务器数据失败", result.get("msg").toString());
                        }
                        break;
                    case "addOneWith":
                        progressDialog.dismiss();
                        Log.i(TAG, "onNext: code值："+result.get("code"));
                        if (result.get("code").equals("1") ) {
                            new AlertDialog.Builder(EditHavActivity.this).setTitle("添加订单提示")//设置对话框标题
                                    .setMessage("添加订单成功！")//设置显示的内容
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                            finish();
                                        }
                                    }).show();
                        } else {
                            showAlertDialog("添加订单提示", result.get("msg").toString());
                        }
                        break;
                }
            }

        });
    }

    void initView() {
        tv1 = (TextView) findViewById(R.id.havtx1);
        tv2 = (TextView) findViewById(R.id.havtx2);
        tv3 = (TextView) findViewById(R.id.havtx3);
        tv4 = (TextView) findViewById(R.id.havtx4);
        tv5 = (TextView) findViewById(R.id.havtx5);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tvBack = (TextView) findViewById(R.id.havgoback);
        tvSave = (TextView) findViewById(R.id.havedit);
        tvBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);

        et1 = (EditText) findViewById(R.id.havet1);
        et2 = (EditText) findViewById(R.id.havet2);
        et3 = (EditText) findViewById(R.id.havet3);
        et4 = (EditText) findViewById(R.id.havet4);

        spn1 = (Spinner) findViewById(R.id.havspn1);
        spn2 = (Spinner) findViewById(R.id.havspn2);
        spn3 = (Spinner) findViewById(R.id.havspn3);
        spn4 = (Spinner) findViewById(R.id.havspn4);
        spn5 = (Spinner) findViewById(R.id.havspn5);
        spn6 = (Spinner) findViewById(R.id.havspn6);

        sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    }

    void dataCome(LinkedTreeMap<String, String> datas) {
        tv1.setText(getIntent().getStringExtra("CompanyName"));
        tv2.setText(getIntent().getStringExtra("ContactName"));
        tv5.setText(datas.get("num")==null ? "1" : (Integer.parseInt(datas.get("num")) + 1) + "");

        et3.setText(datas.get("address"));
        et4.setText(datas.get("content"));

        spn1.setSelection(getSpnSelect(datas.get("Remind"), R.array.remind));
        spn2.setSelection(getSpnSelect(datas.get("industry"), R.array.industry));
        spn3.setSelection(getSpnSelect(datas.get("goods"), R.array.goods));
        spn4.setSelection(getSpnSelect(datas.get("clientSource"), R.array.clientsource));
        spn6.setSelection(getSpnSelect(datas.get("wast"), R.array.wast));

    }

    int getSpnSelect(String value, int resouceValue) {
        Resources res = getResources();
        String[] arrays = res.getStringArray(resouceValue);
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].equals(value)) {
                return i;
            }
        }
        return 0;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.havgoback:
                finish();
                break;
            case R.id.havedit:
                saveData();
                break;
            case R.id.havtx3:
                isNextTime = true;
                nextContact();
                break;
            case R.id.havtx4:
                isNextTime = false;
                nextContact();
                break;
        }
    }

    void nextContact(){
        View view = View.inflate(this,R.layout.hav_edit_nextcontact,null);
        NumberPicker np1 = (NumberPicker) view.findViewById(R.id.np1);
        np2 = (NumberPicker) view.findViewById(R.id.np2);
        NumberPicker np3 = (NumberPicker) view.findViewById(R.id.np3);
        NumberPicker np4 = (NumberPicker) view.findViewById(R.id.np4);
        np1.setMinValue(1);
        np2.setMinValue(1);
        np3.setMinValue(1);
        np4.setMinValue(1);
        np1.setMaxValue(12);
        np3.setMaxValue(24);
        np4.setMaxValue(60);
        np1.setOnValueChangedListener(this);
        np2.setOnValueChangedListener(this);
        np3.setOnValueChangedListener(this);
        np4.setOnValueChangedListener(this);
        date = new StringBuffer(sDateFormat.format(new java.util.Date()));
        np2.setMaxValue(maxDay());
        np4.setValue(Integer.parseInt(date.toString().substring(14,16)));
        np1.setValue(Integer.parseInt(date.toString().substring(5,7)));
        np2.setValue(Integer.parseInt(date.toString().substring(8,10)));
        np3.setValue(Integer.parseInt(date.toString().substring(11,13)));
        dateAlertDialog =  new AlertDialog.Builder(this).setTitle(date)//设置对话框标题
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        if (isNextTime){
                            tv3.setText(date.toString());
                        }else{
                            tv4.setText(date.toString());
                        }
                    }
                }).show();
    }

    int maxDay(){
        int month = Integer.parseInt(date.toString().substring(5,7));
        int year = Integer.parseInt(date.toString().substring(0,4));
        if(month == 1 ||month == 3 ||month == 5 ||month == 7 ||month == 8 ||month == 10 ||month == 12){
            return 31;
        }else if (month == 2){
            if ((year%4==0)&&(year%100!=0)||(year%400==0)){
                return 29;
            }else{
                return 28;
            }
        }else{
            return 30;
        }


    }

    void saveData(){
        Map<String,String> map = new HashMap<>();
        map.put("uid", LoginActivity.getUid()+"");
        map.put("cusId", getIntent().getStringExtra("id"));
        map.put("wast", spn6.getSelectedItem().toString());
        map.put("nextTime", tv3.getText().toString());
        map.put("remindTime", tv4.getText().toString());
        map.put("expenses", et1.getText().toString());
        map.put("Carfare", et2.getText().toString());
        map.put("addres", et3.getText().toString());
        map.put("Content", et4.getText().toString());
        map.put("remind", spn1.getSelectedItem().toString());
        map.put("industry", spn2.getSelectedItem().toString());
        map.put("goods", spn3.getSelectedItem().toString());
        map.put("clientSource", spn4.getSelectedItem().toString());
        map.put("status", spn5.getSelectedItem().toString());
        map.put("num", tv5.getText().toString());
        map.put("info", "addOneWith");

        Date nextTime = null;
        try {
            nextTime = sDateFormat.parse(tv3.getText().toString());
            Date nowTime = new Date();
            Date remindTime = sDateFormat.parse(tv4.getText().toString());
            if (remindTime.getTime() <= nowTime.getTime() || remindTime.getTime() >= nextTime.getTime() ){
                showAlertDialog("填写信息错误提示","请检查下次联系日期和提醒日期的合理性！");
                return;
            }
        } catch (ParseException e) {
            showAlertDialog("填写信息错误提示","请检查下次联系日期和提醒日期的合理性！");
            return;
        }

//        initRx();
        progressDialog = ProgressDialog.show(this, "保存提示", "正在保存,请稍后...");
        havNet.havPost(map);

//        Log.i(TAG, "saveData: "+map.toString());

    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            compileAdd(location);
        }
    };

    void compileAdd(final Location location){
        Toast.makeText(this,"经纬度："+location.getLatitude()+"："+location.getLongitude(),Toast.LENGTH_SHORT).show();
        Log.i(TAG, "compileAdd: 经纬度："+location.getLatitude()+"："+location.getLongitude());
        new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    //TODO:获取到位置后,进行地理位置翻译
                    //组装反向地理编码的接口位置
//                    StringBuilder url = new StringBuilder();
//                    url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
//                    url.append(location.getLatitude()).append(",");
//                    url.append(location.getLongitude());
//                    url.append("&sensor=false");
//                    HttpClient client = new DefaultHttpClient();
//                    HttpGet httpGet = new HttpGet(url.toString());
//                    httpGet.addHeader("Accept-Language","zh-CN");
//                    HttpResponse response = client.execute(httpGet);
//                    if(response.getStatusLine().getStatusCode() == 200){
//                        HttpEntity entity = response.getEntity();
//                        String res = EntityUtils.toString(entity);
//                        //解析
//                        JSONObject jsonObject = new JSONObject(res);
//                        //获取results节点下的位置信息
//                        JSONArray resultArray = jsonObject.getJSONArray("results");
//                        if(resultArray.length() > 0){
//                            JSONObject obj = resultArray.getJSONObject(0);
//                            //取出格式化后的位置数据
//                            String address = obj.getString("formatted_address");
//
//                            Message msg = new Message();
//                            msg.what = SHOW_LOCATION;
//                            msg.obj = address;
//                            handler.sendMessage(msg);
//                        }
//                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //移除监听器
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(locationListener);
        }
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        Toast toast = null;
        //2017-11-11 11:11
        int year = Integer.parseInt(date.toString().substring(0,4));
        int month = Integer.parseInt(date.toString().substring(5,7));
        int day = Integer.parseInt(date.toString().substring(8,10));
        int hour = Integer.parseInt(date.toString().substring(11,13));
        int mine = Integer.parseInt(date.toString().substring(14,16));

        String nowDate = sDateFormat.format(new Date());
        int yearNow = Integer.parseInt(nowDate.substring(0,4));
        int monthNow = Integer.parseInt(nowDate.substring(5,7));
        int dayNow = Integer.parseInt(nowDate.substring(8,10));
        int hourNow = Integer.parseInt(nowDate.substring(11,13));
        int mineNow = Integer.parseInt(nowDate.substring(14,16));
        if (isNextTime){
            switch (numberPicker.getId()){
                case R.id.np1:
                    if((i1 - i ==1) || (i1 - i) == -11 ){
                        if((i1 - i) == -11){
                            date.replace(0,4,year+1+"");
                        }
                        date.replace(5,7,(i1+"").length() == 1 ? "0"+i1:i1+"");
                    }else{
                        if( i1 < monthNow && year <= yearNow ){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(monthNow);
                        }else{
                            if (i1 - i == 11){
                                date.replace(0,4,year-1+"");
                            }
                            date.replace(5,7,(i1+"").length() == 1 ? "0"+i1:i1+"");
                        }
                    }
                    np2.setMaxValue(maxDay());
                    break;
                case R.id.np2:
                    if(year == yearNow && month ==monthNow){
                        if(i1 < dayNow){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(dayNow);
                            date.replace(8,10,(dayNow+"").length() == 1 ? "0"+dayNow:dayNow+"");
                            return;
                        }
                    }
                    date.replace(8,10,(i1+"").length() == 1 ? "0"+i1:i1+"");
                    break;
                case R.id.np3:
                    if(year == yearNow && month ==monthNow && day == dayNow){
                        if(i1 < hour){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(hourNow);
                            date.replace(11,13,(hourNow+"").length() == 1 ? "0"+hourNow:hourNow+"");
                            return;
                        }
                    }
                    date.replace(11,13,(i1+"").length() == 1 ? "0"+i1:i1+"");
                    break;
                case R.id.np4:
                    if(year == yearNow && month ==monthNow && hour == hourNow && day == dayNow){
                        if(i1 < mine){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(mineNow);
                            date.replace(14,16,(mineNow+"").length() == 1 ? "0"+mineNow:mineNow+"");
                            return;
                        }
                    }
                    date.replace(14,16,(i1+"").length() == 1 ? "0"+i1:i1+"");
                    break;
            }

        }else{
            int yearMax = Integer.parseInt(tv3.getText().toString().substring(0,4));
            int monthMax = Integer.parseInt(tv3.getText().toString().substring(5,7));
            int dayMax = Integer.parseInt(tv3.getText().toString().substring(8,10));
            int hourMax = Integer.parseInt(tv3.getText().toString().substring(11,13));
            int mineMax = Integer.parseInt(tv3.getText().toString().substring(14,16));

            switch (numberPicker.getId()){
                case R.id.np1:
                    if((i1 - i ==1) || (i1 - i) == -11 ){
                        if((i1 - i) == -11){
                            if (year+1 > yearMax){
                                if (toast == null){
                                    toast = Toast.makeText(EditHavActivity.this,"提醒日期不能大于联系日期！",Toast.LENGTH_SHORT);
                                }
                                toast.show();
                                numberPicker.setValue(i);
                                return;
                            }
                            date.replace(0,4,year+1+"");
                        }
                        if (year == yearMax && i1 > monthMax){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能大于联系日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(i);
                            return;
                        }
                        date.replace(5,7,(i1+"").length() == 1 ? "0"+i1:i1+"");


                    }else{
                        if( i1 < monthNow && year <= yearNow ){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(monthNow);
                        }else{
                            if (i1 - i == 11){
                                date.replace(0,4,year-1+"");
                            }
                            date.replace(5,7,(i1+"").length() == 1 ? "0"+i1:i1+"");
                        }
                    }
                    np2.setMaxValue(maxDay());
                    break;
                case R.id.np2:
                    if(year == yearNow && month ==monthNow){
                        if(i1 < dayNow){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(dayNow);
                            date.replace(8,10,(dayNow+"").length() == 1 ? "0"+dayNow:dayNow+"");
                            return;
                        }
                    }
                    if(year == yearMax && month == monthMax){
                        if(i1 > dayMax){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能大于联系日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(dayMax);
                            date.replace(8,10,(dayMax+"").length() == 1 ? "0"+dayMax:dayMax+"");
                            return;
                        }
                    }
                    date.replace(8,10,(i1+"").length() == 1 ? "0"+i1:i1+"");
                    break;
                case R.id.np3:
                    if(year == yearNow && month ==monthNow && day == dayNow){
                        if(i1 < hour){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(hourNow);
                            date.replace(11,13,(hourNow+"").length() == 1 ? "0"+hourNow:hourNow+"");
                            return;
                        }
                    }
                    if (year == yearMax && month ==monthMax && day == dayMax){
                        if(i1 > hourMax){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能大于联系日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(hourMax);
                            date.replace(11,13,(hourMax+"").length() == 1 ? "0"+hourMax:hourMax+"");
                            return;
                        }
                    }
                    date.replace(11,13,(i1+"").length() == 1 ? "0"+i1:i1+"");
                    break;
                case R.id.np4:
                    if(year == yearNow && month ==monthNow && hour == hourNow && day == dayNow){
                        if(i1 < mine){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能小于当前日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(mineNow);
                            date.replace(14,16,(mineNow+"").length() == 1 ? "0"+mineNow:mineNow+"");
                            return;
                        }
                    }
                    if (year == yearMax && month ==monthMax && hourMax == hour && day == dayMax){
                        if ( i1 > mineMax ){
                            if (toast == null){
                                toast = Toast.makeText(EditHavActivity.this,"提醒日期不能大于联系日期！",Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            numberPicker.setValue(mineMax);
                            date.replace(14,16,(mineMax+"").length() == 1 ? "0"+mineMax:mineMax+"");
                            return;
                        }
                    }
                    date.replace(14,16,(i1+"").length() == 1 ? "0"+i1:i1+"");
                    break;
            }
        }
        dateAlertDialog.setTitle(date);
    }
}
