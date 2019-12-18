package com.jiangyi.zhaopin.dao;

import com.jiangyi.zhaopin.model.Applicant;
import org.springframework.stereotype.Repository;

/**
 * 求职者dao层
 */
@Repository
public interface ApplicantMapper {

    Applicant selectByName(Applicant applicant);

    void insertApp(Applicant applicant);

    Applicant selectAll(Applicant applicant);

    void updatauLock(Applicant applicant);
}