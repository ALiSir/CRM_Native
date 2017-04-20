package com.powerleader.cdn.crm_cdn.person.more;

import android.util.Log;

import com.powerleader.cdn.crm_cdn.bean.Contents;
import com.powerleader.cdn.crm_cdn.bean.UserInfo;
import com.powerleader.cdn.crm_cdn.view.more.userinfo.UserInfoActivity;
import com.powerleader.cdn.crm_cdn.view.more.userinfo.UserInfoView;

/**
 * Created by ALiSir on 17/4/19.
 */

public class UserInfoPersoner {
    private UserInfoView userInfoView;
    private UserInfo userModel;

    public UserInfoPersoner(UserInfoView view,UserInfo userModel){
        this.userInfoView = view;
        this.userModel = userModel;
    }

    public void initView() {
        userInfoView.remark.setText(userModel.getRemarck());
        Log.i(UserInfoActivity.TAG, "initView: "+Contents.BASE_IMAGE_URL + userModel.getHeadImage());
        userInfoView.headerImage.setImageUrl(Contents.BASE_IMAGE_URL + userModel.getHeadImage());
    }

}
