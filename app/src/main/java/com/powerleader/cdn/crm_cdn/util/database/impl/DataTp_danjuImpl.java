package com.powerleader.cdn.crm_cdn.util.database.impl;

import com.powerleader.cdn.crm_cdn.bean.Tp_danju;
import com.powerleader.cdn.crm_cdn.util.database.DataTp_danju;

import java.util.List;

import io.realm.Realm;
import io.realm.Tp_danjuRealmProxy;

/**
 * Created by ALiSir on 17/1/9.
 */

public class DataTp_danjuImpl implements DataTp_danju {
    private Realm realm;

    public DataTp_danjuImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean updateTp_danju(Tp_danju tp_user) {
        if(findOneTp_danju(tp_user.getId()) != null){
            deletTp_danju(tp_user.getId());
        }
        addTp_danju(tp_user);
        return false;
    }

    @Override
    public boolean deletAllObject() {
        try {
            //TODO 删除所有数据
            realm.delete(Tp_danju.class);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Tp_danju findOneTp_danju(int id) {
        return realm.where(Tp_danju.class).equalTo("id",id).findFirst();
    }

    @Override
    public List<Tp_danju> findAllTp_danju() {
        return realm.where(Tp_danju.class).findAll();
    }

    @Override
    public boolean deletTp_danju(int id) {
        try {
            realm.beginTransaction();
            Tp_danju tp_user = findOneTp_danju(id);
            tp_user.deleteFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addTp_danju(Tp_danju tp_user) {
        try{
            if(findOneTp_danju(tp_user.getId()) == null){
                realm.beginTransaction();
                realm.copyToRealm(tp_user);
                realm.commitTransaction();
            }else{
                updateTp_danju(tp_user);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }


}
