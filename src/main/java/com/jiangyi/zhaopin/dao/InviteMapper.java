package com.jiangyi.zhaopin.dao;

import com.jiangyi.zhaopin.model.Invite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteMapper {
    List<Invite> selectAll();

    List<Invite> selectlike(String iName);

    List<Invite> selectAllByCID(Integer cId);

    Invite selectByIId(Integer iId);

    void deleteById(Integer iId);

    void insert(Invite invite);

    void updata(Invite invite);
}
