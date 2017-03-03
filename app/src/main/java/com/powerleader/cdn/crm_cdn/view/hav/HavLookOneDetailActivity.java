package com.powerleader.cdn.crm_cdn.view.hav;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

public class HavLookOneDetailActivity extends MActivity {
    private LinearLayout danjudeltailoneheader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hav_look_one_detail);
        initView();
    }

    private void initView(){
        danjudeltailoneheader = (LinearLayout) findViewById(R.id.danjudeltailoneheader);
        danjudeltailoneheader.setPadding(0,INFO_SHOW-10,0,0);
    }

}
