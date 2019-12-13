package com.jiangyi.zhaopin.controller;


import com.jiangyi.zhaopin.model.Test;
import com.jiangyi.zhaopin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("index")
    public ModelAndView findall() {
        ModelAndView modelAndView = new ModelAndView();
        List<Test> tests = testService.selectAll();
        modelAndView.addObject("test",tests);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
