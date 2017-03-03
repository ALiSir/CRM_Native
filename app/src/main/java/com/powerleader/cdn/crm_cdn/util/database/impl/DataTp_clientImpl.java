package com.powerleader.cdn.crm_cdn.util.database.impl;

import com.powerleader.cdn.crm_cdn.bean.Tp_client;
import com.powerleader.cdn.crm_cdn.util.database.DataTp_client;

import java.util.List;

import io.realm.Realm;

/**
 * Created by ALiSir on 17/1/4.
 */

public class DataTp_clientImpl implements DataTp_client {
    private Realm realm;


    public DataTp_clientImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean updateTp_client(Tp_client tp_clien) {
        if(findOneTp_client(tp_clien.getId()) != null){
            deletTp_client(tp_clien.getId());
        }
        addTp_client(tp_clien);
        return false;
    }

    @Override
    public Tp_client findOneTp_client(int sortId) {
        return realm.where(Tp_client.class).equalTo("sortid",sortId).findFirst();
    }

    @Override
    public List<Tp_client> findAllTp_client() {
        return realm.where(Tp_client.class).findAll();
    }

    @Override
    public boolean deletTp_client(int sortId) {
        try {
            realm.beginTransaction();
            Tp_client tp_client = findOneTp_client(sortId);
            tp_client.deleteFromRealm();
            realm.commitTransaction();
        }catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public boolean addTp_client(Tp_client tp_client) {

        try{
            if(findOneTp_client(tp_client.getId()) == null){
                realm.beginTransaction();
                realm.copyToRealm(tp_client);
                realm.commitTransaction();
            }else{
                updateTp_client(tp_client);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
