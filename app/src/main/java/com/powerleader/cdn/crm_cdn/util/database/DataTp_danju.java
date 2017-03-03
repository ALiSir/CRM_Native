package com.powerleader.cdn.crm_cdn.util.database;

import com.powerleader.cdn.crm_cdn.bean.Tp_danju;

import java.util.List;

/**
 * Created by ALiSir on 17/1/9.
 */

public interface DataTp_danju {
    public boolean updateTp_danju(Tp_danju tp_danju);

    public Tp_danju findOneTp_danju(int id);

    public List<Tp_danju> findAllTp_danju();

    public boolean deletTp_danju(int id);

    public boolean deletAllObject();

    public boolean addTp_danju(Tp_danju tp_danju);

}
