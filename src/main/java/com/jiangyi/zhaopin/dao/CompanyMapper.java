package com.jiangyi.zhaopin.dao;

import com.jiangyi.zhaopin.model.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMapper {

    Company selectAll(Company company);

    Company selectByNP(Company company);

    void insertCom(Company company);

    void updataByCName(Company company);

    Company selectBycId(Integer cId);
}
