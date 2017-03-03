package com.powerleader.cdn.crm_cdn;

import android.os.Bundle;

import com.powerleader.cdn.crm_cdn.person.login.LoginInterface;
import com.powerleader.cdn.crm_cdn.person.login.LoginPrerson;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

public class MainActivity extends MActivity {
    private LoginInterface li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        li = new LoginPrerson(this);
        li.initView(this.getWindow().getDecorView());
    }
}
