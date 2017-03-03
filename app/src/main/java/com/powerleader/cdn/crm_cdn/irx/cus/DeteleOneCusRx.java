package com.powerleader.cdn.crm_cdn.irx.cus;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ALiSir on 17/2/13.
 */

public class DeteleOneCusRx {

    private static DeteleOneCusRx cusRx;
    private Observable<HashMap<String,Object>> logOb;
    private Subscriber<HashMap<String,Object>> logSub;

    public static DeteleOneCusRx cusRxInit(){
        if(cusRx == null){
            cusRx = new DeteleOneCusRx();
        }
        return cusRx;
    }

    public Observable<HashMap<String,Object>> getLogOb() {
        return logOb;
    }

    public void setLogOb(Observable<HashMap<String,Object>> logOb) {
        this.logOb = logOb;
    }

    public Subscriber<HashMap<String,Object>> getLogSub() {
        return logSub;
    }

    public void setLogSub(Subscriber<HashMap<String,Object>> logSub) {
        this.logSub = logSub;
    }
}
