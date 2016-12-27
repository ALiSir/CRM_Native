package com.powerleader.cdn.crm_cdn;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.powerleader.cdn.crm_cdn.person.HomeInterface;
import com.powerleader.cdn.crm_cdn.person.impl.HomePerson;

public class HomeActivity extends AppCompatActivity {
    private HomeInterface hi;
    private boolean isExit = false;

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
        setContentView(R.layout.activity_home);
        View view = getWindow().getDecorView();
        hi = new HomePerson(this, view);
        hi.start();
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
}
