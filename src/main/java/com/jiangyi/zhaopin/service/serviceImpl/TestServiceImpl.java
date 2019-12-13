package com.jiangyi.zhaopin.service.serviceImpl;

import com.jiangyi.zhaopin.dao.TestMapper;
import com.jiangyi.zhaopin.model.Test;
import com.jiangyi.zhaopin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService{
    @Autowired
    TestMapper testMapper;
    @Override
    public List<Test> selectAll() {
        return testMapper.selectAll();
    }
}
