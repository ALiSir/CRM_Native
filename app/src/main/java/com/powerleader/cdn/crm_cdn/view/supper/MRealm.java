package com.powerleader.cdn.crm_cdn.view.supper;

import android.os.Message;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ALiSir on 2016/12/6.
 */

public class MRealm{
    private static Realm realm;
    private final static int VERSION = 1;

    public MRealm(){

    }

    private static RealmConfiguration getMyRC(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("com.powerleader.cdn")
                .schemaVersion(VERSION)
                .build();
        return config;
    }

    public static Realm getRealm(){
        if (realm == null){
            realm = Realm.getInstance(getMyRC());
        }
        return realm;
    }

}
