package com.jiangyi.zhaopin.service;

import com.jiangyi.zhaopin.model.Invite;

import java.util.List;

public interface InviteService {

    List<Invite> selectAll();

    List<Invite> selectAllByCID(Integer cId);

    void deleteById(Integer iId);

    void insert(Invite invite);

    void updata(Invite invite);

    Invite selectByIId(Integer iId);

    List<Invite> selectlike(String iName);

}
