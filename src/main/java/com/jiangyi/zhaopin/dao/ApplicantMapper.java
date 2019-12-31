package com.jiangyi.zhaopin.dao;

import com.jiangyi.zhaopin.model.Applicant;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 求职者dao层
 */
@Repository
public interface ApplicantMapper {

    List<Applicant> select(Applicant applicant);

    List<Applicant> selectlist();

    Applicant selectByName(Applicant applicant);

    Applicant selectByuId(Integer uId);

    void insertApp(Applicant applicant);

    Applicant selectAll(Applicant applicant);

    void updatauLock(Applicant applicant);

    void updatauser(Applicant applicant);

    void updataskill(Applicant applicant);
}