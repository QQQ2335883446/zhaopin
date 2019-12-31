package com.jiangyi.zhaopin.service;

import com.jiangyi.zhaopin.model.Send;

import java.util.List;

public interface SendService {

    List<Send> selectAllByuId(Integer uId);

    List<Send> selectAllBycId(Integer cId);

    void insertsend(Send send);

    void deleteBysId(Integer sId);

    void updatasend(Send send);

    void deleteByiId(Integer iId);

    void insertsend2(Send send);

    int uniq(Send send);
}
