package com.jiangyi.zhaopin.controller;

import com.jiangyi.zhaopin.model.Applicant;
import com.jiangyi.zhaopin.service.ApplicatService;
import com.jiangyi.zhaopin.util.Mail;
import com.jiangyi.zhaopin.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.servlet.http.HttpServletRequest;

/**
 * 求职者
 * 控制层
 */
@RestController
public class ApplicatController {
    @Autowired
    Mail mail;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    ApplicatService applicatService;
    Md5 md5 = new Md5();
    /**
     * 用户登录
     * @param applicant
     * @param request
     * @return
     */
    @RequestMapping("user/login")
    public ModelAndView login(Applicant applicant, HttpServletRequest request){
        //解密
        applicant.setuPwd(md5.MD5Encode(applicant.getuPwd(),"utf-8"));
        ModelAndView modelAndView = new ModelAndView();
        String msg = "";
        Applicant user = applicatService.selectByName(applicant);
        if(user==null){
            msg="账号或密码错误！！";
            modelAndView.addObject("msg",msg);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.addObject("user",user);
        if(user.getuLock()<0){
            msg="账号未激活，请前往邮箱！！";
            modelAndView.addObject("msg",msg);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        //将成功登录的用户存入session
        request.getSession().setAttribute("user",user);
        //重定向至主页面
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }
    /**
     * 修改密码
     * 判断旧密码
     * @param applicant
     * @param request
     * @return
     */
    @RequestMapping("user/ColdPwd")
    public boolean ColdPwd(Applicant applicant, HttpServletRequest request){
        //解密
        applicant.setuPwd(md5.MD5Encode(applicant.getuPwd(),"utf-8"));
        Applicant user = applicatService.selectByName(applicant);
        if(user==null){
            return false;
        }
       return true;
    }

    /**
     * 更新信息
     * @param applicant
     * @param request
     * @return
     */
    @RequestMapping("user/updatauser")
    public boolean updatauser(Applicant applicant, HttpServletRequest request){
        applicatService.updatauser(applicant);
        return true;
    }
    /**
     * 更新信息
     * @param applicant
     * @param request
     * @return
     */
    @RequestMapping("user/index")
    public ModelAndView userindex(Applicant applicant, HttpServletRequest request){
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user2 = applicatService.selectAll(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user2);
        modelAndView.setViewName("/index");
        return modelAndView;
    }
    /**
     * 修改用户信息页面
     * @param request
     * @return
     */
    @RequestMapping("user/updata")
    public ModelAndView updata(HttpServletRequest request){
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user1 = applicatService.selectAll(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user1);
        modelAndView.setViewName("userdata");
        return modelAndView;
    }

    /**
     * 简历页面
     * @param request
     * @return
     */
    @RequestMapping("user/resume")
    public ModelAndView resume(HttpServletRequest request){
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user1 = applicatService.selectAll(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user1);
        modelAndView.setViewName("resume");
        return modelAndView;
    }
    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("user/logout")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 用户注册
     * @param applicant
     * @return
     */
    @RequestMapping("user/reg")
    public boolean reg(Applicant applicant){
        //发送验证邮件
        Context context = new Context();
        context.setVariable("uName",applicant.getuName());
        String mails =templateEngine.process("mailtemplate.html",context);
        mail.sendHtmlMail("2335883446@qq.com",applicant.getuEmail(),"激活账号",mails);
        //加密
        applicant.setuPwd(md5.MD5Encode(applicant.getuPwd(),"utf-8"));
        applicatService.insertApp(applicant);
        ModelAndView modelAndView = new ModelAndView();
        return true;
    }

    /**
     * 判断用户是否存在
     * @param applicant
     * @return
     */
    @RequestMapping("user/exist")
    public boolean exist(Applicant applicant){
        Applicant applicant1 = applicatService.selectAll(applicant);
        if(applicant1==null){
            return true;
        }else
       // ModelAndView modelAndView = new ModelAndView();
        return false;
    }

    /**
     *激活账号
     * @param applicant
     * @return
     */
    @RequestMapping("user/activate")
    public ModelAndView activate(Applicant applicant){
        System.out.printf(applicant.getuName());
        applicatService.updatauLock(applicant);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("activate");
        return modelAndView;
    }
    /**
     * 测试专用
     */
    @RequestMapping("testemail")
    public void testemail(){
        //TemplateEngine templateEngine = new TemplateEngine();
/*        Context context = new Context();
        context.setVariable("uName","haha");
        String mails =templateEngine.process("mailtemplate.html",context);
        mail.sendHtmlMail("2335883446@qq.com","htx2335883446@163.com","激活账号",mails);*/
    }

    @RequestMapping("testerror")
    public void testerror(){
        int a = 5;
        a = a/0;
    }
}
