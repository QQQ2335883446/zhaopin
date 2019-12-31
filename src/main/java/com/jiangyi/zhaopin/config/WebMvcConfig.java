package com.jiangyi.zhaopin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 视图配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/reg").setViewName("register");
        registry.addViewController("/regsucess").setViewName("regsucess");
        registry.addViewController("/comregsucess").setViewName("comregistersuccess");
        registry.addViewController("/activate").setViewName("activate");
        registry.addViewController("/userdata").setViewName("userdata");
        registry.addViewController("/resume").setViewName("resume");
        registry.addViewController("/comindex").setViewName("comindex");
        registry.addViewController("/comregister").setViewName("comregister");
        registry.addViewController("/comdatamanager").setViewName("comdatamanager");
        registry.addViewController("/invite").setViewName("invite");
        registry.addViewController("/invitedetail").setViewName("invitedetail");
        registry.addViewController("/sends").setViewName("sends");
        registry.addViewController("/csend").setViewName("csend");
        registry.addViewController("/cresume").setViewName("cresume");
        registry.addViewController("/csmart").setViewName("csmart");
    }
}