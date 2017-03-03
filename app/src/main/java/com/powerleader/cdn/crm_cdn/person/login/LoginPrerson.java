package com.powerleader.cdn.crm_cdn.person.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.powerleader.cdn.crm_cdn.person.cus.CusFragInterface;
import com.powerleader.cdn.crm_cdn.view.HomeActivity;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.LoginResult;
import com.powerleader.cdn.crm_cdn.net.login.LoginForm;
import com.powerleader.cdn.crm_cdn.util.database.DataTp_danju;
import com.powerleader.cdn.crm_cdn.util.database.DataTp_user;
import com.powerleader.cdn.crm_cdn.util.database.impl.DataTp_danjuImpl;
import com.powerleader.cdn.crm_cdn.util.database.impl.DataTp_userImpl;
import com.powerleader.cdn.crm_cdn.view.hav.HavingDealFragment;
import com.powerleader.cdn.crm_cdn.view.login.LoginActivity;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.language.DoubleMetaphone;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ALiSir on 2016/12/9.
 */

public class LoginPrerson implements LoginInterface, View.OnClickListener, LoginForm.OnLoginResult {
    private static final String TAG = LoginPrerson.class.getSimpleName();
    private Activity context;
    private Button btn;
    private EditText nameText, pwdText;
    private LoginForm loginForm;
    private ProgressBar loginprogressBar;
    private DataTp_user dataTp_user;
    private DataTp_danju dataTp_danju;
    private String username,password;

    public LoginPrerson(Activity context) {
        this.context = context;
    }

    @Override
    public void initView(View view) {
        btn = (Button) view.findViewById(R.id.login);
        btn.setOnClickListener(this);
        nameText = (EditText) view.findViewById(R.id.name);
        pwdText = (EditText) view.findViewById(R.id.password);
        loginprogressBar = (ProgressBar) view.findViewById(R.id.loginprogressBar);
        loginForm = new LoginForm();
        loginForm.setOnLoginResult(this);
        dataTp_user = new DataTp_userImpl();
        dataTp_danju = new DataTp_danjuImpl();
    }

    @Override
    public void login() {
        username = nameText.getText().toString();
        password = pwdText.getText().toString();
        switch (username) {
            case "":
                nameText.setError("用户名不能为空");
                nameText.setFocusable(true);
                return;
        }
        switch (password) {
            case "":
                pwdText.setError("密码不能为空");
                pwdText.setFocusable(true);
                return;
        }
        password = new String(Hex.encodeHex(DigestUtils.sha1(new String(Hex.encodeHex(DigestUtils.md5(password))))));
        loginprogressBar.setVisibility(View.VISIBLE);
        loginForm.loginPostQuery(username,password);
    }

    @Override
    public void loginResult(HashMap<String,Object> result) {
        loginprogressBar.setVisibility(View.GONE);
        if (result.get("code").toString().equals("0")) {
            Log.i(LoginActivity.TAG, "loginResult: 登录成功!" );
            LoginActivity.setUid(((Double)result.get("uid")).intValue());
//            LoginActivity.setUid(Integer.parseInt(result.get("uid").toString().split(".")[0]));
            HavingDealFragment.setAllData((ArrayList<LinkedTreeMap<String,Object>>) result.get("object"));
            skipToHome();
        }else{
            Toast.makeText(context, result.get("msg").toString(), Toast.LENGTH_LONG).show();
        }



//            tp_user.setId(loginResult.getUid());
//            dataTp_user.updateTp_user(tp_user);
//
//            List<Tp_danju> danjus  = (List<Tp_danju>) loginResult.getObject();
//
//            if (dataTp_danju.findAllTp_danju().size() > 0) {
//                if (loginResult.getUpdate() == 1) {
//                    dataTp_danju.deletAllObject();
//                    for (int i = 0; i < danjus.size(); i++) {
//                        dataTp_danju.addTp_danju(danjus.get(i));
//                    }
//                }
//            } else {
//                for (int i = 0; i < danjus.size(); i++) {
//                    Tp_danju tss = (Tp_danju) danjus.get(i);
//                    dataTp_danju.addTp_danju(tss);
//                }


//            tp_user = new Gson().fromJson(new Gson().toJson(jsonObject.getObject()),Tp_user.class);
//            Log.i(LoginActivity.TAG, "loginResult: 登录成功"+tp_user.toString());


//            return;
//        }
//        Toast.makeText(context, loginResult.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void skipToHome() {
        Intent intent = new Intent();
        intent.setClass(context, HomeActivity.class);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                this.login();
                break;
        }
    }

}
