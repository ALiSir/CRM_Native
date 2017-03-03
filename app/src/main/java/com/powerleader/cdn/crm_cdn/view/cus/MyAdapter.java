package com.powerleader.cdn.crm_cdn.view.cus;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap<String,String>> arrayList;
    private int rightWidth = 50;
    private String[] fromStr;
    private int[] toId;
    private int parentId;

    private IOnItemRightClickListener mListener = null;
    public interface IOnItemRightClickListener {
        void onItemClike(View v, int position, String sortid);
    }

    public MyAdapter(Context context, int parentId, ArrayList<HashMap<String,String>> arrayList, String[] fromStr, int[] toId) {
        this.context = context;
        this.arrayList = arrayList;
        this.fromStr = fromStr;
        this.toId = toId;
        this.parentId = parentId;
    }

    public void setRightWidth(int rightWidth) {
        this.rightWidth = rightWidth;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemOncliclikListener(IOnItemRightClickListener itemOncliclikListener){
            this.mListener = itemOncliclikListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int currPosition = position;
        View[] views = getAllView();
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(parentId,parent,false);
            for(int i = 0 ; i< views.length;i++){
                views[i] = convertView.findViewById(toId[i]);
            }
            convertView.setTag(views);
        }else{
            views = (View[])convertView.getTag();
        }
        String uname = arrayList.get(position).get("companyName");
        if(uname.length() > 10){
            uname = uname.substring(0,10) + "...";
        }
        ((TextView)views[0]).setText(uname);
        ((TextView)views[0]).setWidth(parent.getWidth()/10*5);
        ((TextView)views[0]).setGravity(Gravity.LEFT);
        ((TextView)views[1]).setText(arrayList.get(position).get("contactName"));
        ((TextView)views[1]).setGravity(Gravity.LEFT);

        if(mListener !=null){
            views[1].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mListener.onItemClike(v,currPosition,arrayList.get(position).get("id"));
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClike(v,currPosition,arrayList.get(position).get("id"));
                }
            });
        }
        return convertView;
    }

    private View[] getAllView(){
        return new View[fromStr.length];
    }

}
