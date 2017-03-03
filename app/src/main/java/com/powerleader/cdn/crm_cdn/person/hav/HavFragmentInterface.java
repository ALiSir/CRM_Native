package com.powerleader.cdn.crm_cdn.person.hav;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ALiSir on 17/1/4.
 */

public interface HavFragmentInterface extends View.OnClickListener{
    public ArrayList<HashMap<String,String>> initData();
    public void lookOneDetail(int id);
}
