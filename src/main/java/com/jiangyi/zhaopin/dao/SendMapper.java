package com.jiangyi.zhaopin.dao;

import com.jiangyi.zhaopin.model.Send;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SendMapper {

    List<Send> selectAllByuId(Integer uId);

    List<Send> selectAllBycId(Integer cId);


    void insertsend(Send send);

    void insertsend2(Send send);

    void deleteBysId(Integer sId);

    void updatasend(Send send);

    void deleteByiId(Integer iId);

    int uniq(Send send);

}
