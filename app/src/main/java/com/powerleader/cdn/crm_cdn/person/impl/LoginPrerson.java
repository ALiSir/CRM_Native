package com.powerleader.cdn.crm_cdn.person.impl;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.JsonObject;
import com.powerleader.cdn.crm_cdn.bean.Tp_user;
import com.powerleader.cdn.crm_cdn.net.LoginForm;
import com.powerleader.cdn.crm_cdn.person.LoginInterface;

/**
 * Created by Administrator on 2016/12/9.
 */

public class LoginPrerson implements LoginInterface, View.OnClickListener,LoginForm.OnLoginResult {
    private Context context;
    private Button btn;
    private EditText nameText, pwdText;
    private Tp_user tp_user;
    private LoginForm loginForm;

    public LoginPrerson(Context context) {
        this.context = context;
    }

    @Override
    public void initView(View view) {
        btn = (Button) view.findViewById(R.id.login);
        btn.setOnClickListener(this);
        nameText = (EditText) view.findViewById(R.id.name);
        pwdText = (EditText) view.findViewById(R.id.password);
        loginForm = new LoginForm();
        loginForm.setOnLoginResult(this);
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
        loginForm.loginPostQuery(tp_user);
    }

    @Override
    public void loginResult(JsonObject jsonObject) {
        Toast.makeText(context,jsonObject.toString(),Toast.LENGTH_LONG);
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
