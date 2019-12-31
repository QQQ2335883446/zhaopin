package com.jiangyi.zhaopin.service.serviceImpl;

import com.jiangyi.zhaopin.dao.CompanyMapper;
import com.jiangyi.zhaopin.model.Company;
import com.jiangyi.zhaopin.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公司服务层
 */
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    CompanyMapper companyMapper;

    /**
     * 公司
     * 用户名查找
     * @param company
     * @return
     */
    @Override
    public Company selectAll(Company company) {
        return companyMapper.selectAll(company);
    }

    /**
     * 密码用户名查找
     * @param company
     * @return
     */
    @Override
    public Company selectByNP(Company company) {
        return companyMapper.selectByNP(company);
    }

    /**
     * 插入企业
     * @param company
     */

    @Override
    public void insertCom(Company company) {
            companyMapper.insertCom(company);
    }

    /**
     * 信息更新
     * @param company
     */
    @Override
    public void updataByCName(Company company) {
        companyMapper.updataByCName(company);
    }

    @Override
    public Company selectBycId(Integer cId) {
        return companyMapper.selectBycId(cId);
    }
}
