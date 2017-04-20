package com.powerleader.cdn.crm_cdn.view.more.userinfo;

import android.os.Bundle;

import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.UserInfo;
import com.powerleader.cdn.crm_cdn.person.more.UserInfoPersoner;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

public class UserInfoActivity extends MActivity implements UserInfoView.UserContDelegete {
    public static final String TAG = "UserInfo----->log";
    UserInfo userModel;
    UserInfoView userView;
    UserInfoPersoner userPersoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userModel = UserInfo.init();
        userView = new UserInfoView(this,getWindow().getDecorView());
        userView.setUserContDelegete(this);
        userPersoner = new UserInfoPersoner(userView, userModel);
        userPersoner.initView();
    }

    @Override
    public void exitActivity() {
        finish();
//        overridePendingTransition(R.anim.in_left_to_right,R.anim.out_right_to_left);
    }
}
