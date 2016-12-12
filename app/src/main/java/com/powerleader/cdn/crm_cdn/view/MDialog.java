package com.powerleader.cdn.crm_cdn.view;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by ALiSir on 2016/12/6.
 */

public class MDialog extends Dialog {
    public MDialog(Context context) {
        super(context);
    }

    public MDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }



}
