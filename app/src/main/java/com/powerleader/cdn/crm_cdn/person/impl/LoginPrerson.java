package com.powerleader.cdn.crm_cdn.person.impl;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.person.LoginInterface;

/**
 * Created by Administrator on 2016/12/9.
 */

public class LoginPrerson implements LoginInterface,View.OnClickListener{
    private Context context;
    private Button btn;

    public LoginPrerson(Context context) {
        this.context = context;
    }

    @Override
    public void initView(View view) {
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void login() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Intent intent = new Intent();
                break;
        }
    }
}
