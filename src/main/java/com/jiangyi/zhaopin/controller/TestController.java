package com.jiangyi.zhaopin.controller;


import com.jiangyi.zhaopin.model.Applicant;
import com.jiangyi.zhaopin.model.Test;
import com.jiangyi.zhaopin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("index")
    public ModelAndView findall(HttpServletRequest request) {
        Applicant applicant = (Applicant) request.getSession().getAttribute("user");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",applicant);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
