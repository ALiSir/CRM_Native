package com.powerleader.cdn.crm_cdn.util.database;

import com.powerleader.cdn.crm_cdn.bean.Tp_user;

import java.util.List;

/**
 * Created by ALiSir on 17/1/3.
 */

public interface DataTp_user {
    public boolean updateTp_user(Tp_user tp_user);

    public Tp_user findOneTp_user(int id);

    public List<Tp_user> findAllTp_user();

    public boolean deletTp_user(int id);

    public boolean addTp_user(Tp_user tp_user);
}
