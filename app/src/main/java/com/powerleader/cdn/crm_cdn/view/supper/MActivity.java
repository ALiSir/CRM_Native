package com.powerleader.cdn.crm_cdn.view.supper;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

/**
 * Created by ALiSir on 2016/12/6.
 *
 */

public class MActivity extends FragmentActivity {
    private static Context mContext;
    public static int INFO_SHOW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            // 透明状态栏
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mContext = getApplicationContext();
        INFO_SHOW =  getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (INFO_SHOW > 0) {
            INFO_SHOW = getResources().getDimensionPixelSize(INFO_SHOW);
        }
    }

    @Override
    protected void onResume() {
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    public static Context getContext(){
        return mContext;
    }

}
