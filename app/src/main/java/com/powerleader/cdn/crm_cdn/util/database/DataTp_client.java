package com.powerleader.cdn.crm_cdn.util.database;

import com.powerleader.cdn.crm_cdn.bean.Tp_client;

import java.util.List;

/**
 * Created by ALiSir on 17/1/4.
 */

public interface DataTp_client {

    public boolean updateTp_client(Tp_client tp_clien);

    public Tp_client findOneTp_client(int sortId);

    public List<Tp_client> findAllTp_client();

    public boolean deletTp_client(int sortId);

    public boolean addTp_client(Tp_client Tp_client);
    
}
