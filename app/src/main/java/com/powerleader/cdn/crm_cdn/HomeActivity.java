package com.powerleader.cdn.crm_cdn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.powerleader.cdn.crm_cdn.person.HomeInterface;
import com.powerleader.cdn.crm_cdn.person.impl.HomePerson;

public class HomeActivity extends AppCompatActivity {
    private HomeInterface hi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        View view = getWindow().getDecorView();
        hi = new HomePerson(this, view);
        hi.start();
    }
}
