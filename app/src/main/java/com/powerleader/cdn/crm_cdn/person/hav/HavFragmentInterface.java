package com.powerleader.cdn.crm_cdn.person.hav;

import android.view.View;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ALiSir on 17/1/4.
 */

public interface HavFragmentInterface extends View.OnClickListener{
    public ArrayList<HashMap<String,String>> initData();
    public void lookOneDetail(int id);
    public void havFindAll(Map<String,String> map);
    public ArrayList<HashMap<String, String>> initData(ArrayList<LinkedTreeMap<String,String>> datas);
}
