package com.powerleader.cdn.crm_cdn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.powerleader.cdn.crm_cdn.person.HomeInterface;
import com.powerleader.cdn.crm_cdn.person.impl.HomePerson;
import com.powerleader.cdn.crm_cdn.view.supper.MActivity;

public class HomeActivity extends MActivity {
    private HomeInterface hi;
    private boolean isExit = false;
    private TextView exitBtn;
    private LinearLayout homeMain;

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
        exitBtn = (TextView) view.findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        homeMain = (LinearLayout) findViewById(R.id.homeMian);
        homeMain.setPadding(0,INFO_SHOW-10,0,0);
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
