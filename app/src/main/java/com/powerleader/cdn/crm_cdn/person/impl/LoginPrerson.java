package com.powerleader.cdn.crm_cdn.person.impl;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.powerleader.cdn.crm_cdn.HomeActivity;
import com.powerleader.cdn.crm_cdn.LoginActivity;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.JsonObject;
import com.powerleader.cdn.crm_cdn.bean.Tp_user;
import com.powerleader.cdn.crm_cdn.net.LoginForm;
import com.powerleader.cdn.crm_cdn.person.LoginInterface;
import com.powerleader.cdn.crm_cdn.util.database.DataTp_user;
import com.powerleader.cdn.crm_cdn.util.database.impl.DataTp_userImpl;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ALiSir on 2016/12/9.
 */

public class LoginPrerson implements LoginInterface, View.OnClickListener,LoginForm.OnLoginResult {
    private static final String TAG = LoginPrerson.class.getSimpleName();
    private Activity context;
    private Button btn;
    private EditText nameText, pwdText;
    private Tp_user tp_user;
    private LoginForm loginForm;
    private ProgressBar loginprogressBar;
    private DataTp_user dataTp_user;

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
    }

    @Override
    public void login() {
        tp_user = new Tp_user(nameText.getText().toString(),pwdText.getText().toString());
        switch (tp_user.getUsername()) {
            case "":
                nameText.setError("用户名不能为空");
                nameText.setFocusable(true);
                return;
        }
        switch (tp_user.getPassword()) {
            case "":
                pwdText.setError("密码不能为空");
                pwdText.setFocusable(true);
                return;
        }
        tp_user.setPassword(new String(Hex.encodeHex(DigestUtils.sha1(new String(Hex.encodeHex(DigestUtils.md5(tp_user.getPassword())))))));
        loginprogressBar.setVisibility(View.VISIBLE);
        loginForm.loginPostQuery(tp_user);
    }

    @Override
    public void loginResult(JsonObject jsonObject) {
        loginprogressBar.setVisibility(View.GONE);
        if(jsonObject.getErrorCode() == 0){
            tp_user = new Gson().fromJson(new Gson().toJson(jsonObject.getObject()),Tp_user.class);
            Log.i(LoginActivity.TAG, "loginResult: 登录成功"+tp_user.toString());
            dataTp_user.updateTp_user(tp_user);
            skipToHome();
            return;
        }
        Toast.makeText(context,jsonObject.getMsg(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void skipToHome(){
        Intent intent = new Intent();
        intent.setClass(context,HomeActivity.class);
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
