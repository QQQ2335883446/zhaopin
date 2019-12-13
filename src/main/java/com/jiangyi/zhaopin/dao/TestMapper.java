package com.jiangyi.zhaopin.dao;

import com.jiangyi.zhaopin.model.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {
    List<Test> selectAll();
}
