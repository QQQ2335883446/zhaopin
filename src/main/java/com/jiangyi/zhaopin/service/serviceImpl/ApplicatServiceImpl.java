package com.jiangyi.zhaopin.service.serviceImpl;

import com.jiangyi.zhaopin.dao.ApplicantMapper;
import com.jiangyi.zhaopin.model.Applicant;
import com.jiangyi.zhaopin.service.ApplicatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 求职者服务层
 */
@Service
public class ApplicatServiceImpl implements ApplicatService{
    @Autowired
    ApplicantMapper applicantMapper;

    /**
     * 通过密码登录
     * @param applicant
     * @return
     */
    @Override
    public Applicant selectByName(Applicant applicant) {
        return applicantMapper.selectByName(applicant);
    }

    /**
     * 添加用户
     * @param applicant
     */
    @Override
    public void insertApp(Applicant applicant) {
        applicantMapper.insertApp(applicant);
    }

    /**
     * 获取用户
     * 通过用户名
     * @param applicant
     * @return
     */
    @Override
    public Applicant selectAll(Applicant applicant) {
        return applicantMapper.selectAll(applicant);
    }

    /**
     * 通过用户名
     * 解锁账户
     * @param applicant
     */
    @Override
    public void updatauLock(Applicant applicant) {
        applicantMapper.updatauLock(applicant);
    }

    /**
     * 更新用户详细信息
     * @param applicant
     */
    @Override
    public void updatauser(Applicant applicant) {
        applicantMapper.updatauser(applicant);
    }
}
