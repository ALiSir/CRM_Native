package com.powerleader.cdn.crm_cdn.util.database.impl;

import com.powerleader.cdn.crm_cdn.bean.Tp_user;
import com.powerleader.cdn.crm_cdn.util.MRealm;
import com.powerleader.cdn.crm_cdn.util.database.DataTp_user;

import java.util.List;

import io.realm.Realm;

/**
 * Created by ALiSir on 17/1/3.
 */

public class DataTp_userImpl implements DataTp_user {
    private Realm realm;

    public DataTp_userImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean updateTp_user(Tp_user tp_user) {
        if(findOneTp_user(tp_user.getId()) != null){
            deletTp_user(tp_user.getId());
        }
        addTp_user(tp_user);
        return false;
    }


    @Override
    public Tp_user findOneTp_user(int id) {
        return realm.where(Tp_user.class).equalTo("id",id).findFirst();
    }

    @Override
    public List<Tp_user> findAllTp_user() {
        return realm.where(Tp_user.class).findAll();
    }

    @Override
    public boolean deletTp_user(int id) {
        try {
            realm.beginTransaction();
            Tp_user tp_user = findOneTp_user(id);
            tp_user.deleteFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addTp_user(Tp_user tp_user) {
        try{
            if(findOneTp_user(tp_user.getId()) == null){
                realm.beginTransaction();
                realm.copyToRealm(tp_user);
                realm.commitTransaction();
            }else{
                updateTp_user(tp_user);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
