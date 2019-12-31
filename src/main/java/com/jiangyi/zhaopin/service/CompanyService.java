package com.jiangyi.zhaopin.service;

import com.jiangyi.zhaopin.model.Company;

public interface CompanyService {

    Company selectAll(Company company);

    Company selectByNP(Company company);

    void insertCom(Company company);

    void updataByCName(Company company);

    Company selectBycId(Integer cId);

}
