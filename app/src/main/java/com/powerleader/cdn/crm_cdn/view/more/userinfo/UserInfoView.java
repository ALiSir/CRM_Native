package com.powerleader.cdn.crm_cdn.view.more.userinfo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.util.RoundUrlImageView;

/**
 * Created by ALiSir on 17/4/19.
 */
public class UserInfoView {
    Context context;
    PullToZoomListViewEx listView;
    View views;
    public TextView remark;
    TextView bck;
    UserContDelegete userContDelegete;
    public RoundUrlImageView headerImage;

    public UserInfoView(Context context, View view) {
        this.context = context;
        this.views = view;
        initView();
    }

    void initView() {
        listView = (PullToZoomListViewEx) views.findViewById(R.id.listview);
        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, adapterData));
        listView.getPullRootView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("zhuwenwu", "position = " + position);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("zhuwenwu", "position = " + position);
            }
        });

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int mScreenWidth = wm.getDefaultDisplay().getWidth();
        Log.i("---->", "initView: " + mScreenWidth);
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        listView.setHeaderLayoutParams(localObject);

        remark = (TextView) views.findViewById(R.id.tv_user_name);
        bck = (TextView) views.findViewById(R.id.tv_user_name_back);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userContDelegete.exitActivity();
            }
        });
        headerImage = (RoundUrlImageView) views.findViewById(R.id.iv_user_head);
    }

    public void setUserContDelegete(UserContDelegete delegete) {
        this.userContDelegete = delegete;
    }

    public interface UserContDelegete {
        void exitActivity();
    }
}
