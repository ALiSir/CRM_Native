package com.powerleader.cdn.crm_cdn.view.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.person.login.LoginInterface;
import com.powerleader.cdn.crm_cdn.person.login.LoginPrerson;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

public class LoginActivity extends MActivity {
    public static final String TAG = "登陆：";
    private LoginInterface loginInterface;
    private boolean isExit = false;
    private static int Uid = 0;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginInterface = new LoginPrerson(this);
        loginInterface.initView(getWindow().getDecorView());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(!isExit){
                isExit = true;
                handler.sendEmptyMessageDelayed(0,2000);
                Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
            }else{
                finish();
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static int getUid() {
        return Uid;
    }

    public static void setUid(int uid) {
        Uid = uid;
    }
}
