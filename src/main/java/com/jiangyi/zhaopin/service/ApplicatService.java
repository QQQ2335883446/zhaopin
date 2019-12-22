package com.jiangyi.zhaopin.service;


import com.jiangyi.zhaopin.model.Applicant;

/**
 * 求职者服务接口
 */
public interface ApplicatService {

    Applicant selectByName(Applicant applicant);

    void insertApp(Applicant applicant);

    Applicant selectAll(Applicant applicant);

    void updatauLock(Applicant applicant);

    void updatauser(Applicant applicant);
}
