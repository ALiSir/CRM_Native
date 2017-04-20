package com.powerleader.cdn.crm_cdn.view.more;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powerleader.cdn.crm_cdn.R;
import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.util.breakbricks.FunGameRefreshView;
import com.powerleader.cdn.crm_cdn.view.more.userinfo.UserInfoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFunFragment extends Fragment {
    View view;
    //    TextView tv;
    FunGameRefreshView refreshView;
    Context context;
//    View userView;

    MoreFlagmentAdapter adapter;
    private RecyclerView recyclerView;

    public MoreFunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_more_fun, container, false);
//        this.userView = inflater.inflate(R.layout.more_user, null, false);
        context = inflater.getContext();
        initView();
        return view;
    }

    void initView() {

        adapter = new MoreFlagmentAdapter();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(context,UserInfoActivity.class);
//                context.startActivity(intent);
//            }
//        });
//        recyclerView.findview

        refreshView = (FunGameRefreshView) view.findViewById(R.id.refresh_fun_game);
        refreshView.setLoadingText("玩个游戏解解闷");
        refreshView.setGameOverText("游戏结束");
        refreshView.setLoadingFinishedText("加载完成");
        refreshView.setTopMaskText("下拉游戏");
        refreshView.setBottomMaskText("上下滑动控制游戏");
        refreshView.setOnRefreshListener(new FunGameRefreshView.FunGameRefreshListener() {
            @Override
            public void onPullRefreshing() {
                SystemClock.sleep(2000);
            }

            @Override
            public void onRefreshComplete() {
//                baseAdapter.notifyDataSetChanged();
            }
        });

    }

    class MoreFlagmentAdapter extends RecyclerView.Adapter<MoreFlagmentAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.more_user, parent,
                    false));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(context, UserInfoActivity.class);
                    context.startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(Contents.Uname);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.Uname);
            }
        }
    }
}
