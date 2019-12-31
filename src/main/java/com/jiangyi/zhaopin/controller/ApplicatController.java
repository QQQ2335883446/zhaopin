package com.jiangyi.zhaopin.controller;

import com.jiangyi.zhaopin.model.Applicant;
import com.jiangyi.zhaopin.model.Company;
import com.jiangyi.zhaopin.model.Invite;
import com.jiangyi.zhaopin.model.Send;
import com.jiangyi.zhaopin.service.ApplicatService;
import com.jiangyi.zhaopin.service.CompanyService;
import com.jiangyi.zhaopin.service.InviteService;
import com.jiangyi.zhaopin.service.SendService;
import com.jiangyi.zhaopin.util.Mail;
import com.jiangyi.zhaopin.util.Md5;
import com.jiangyi.zhaopin.util.UCF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 求职者
 * 控制层
 */
@RestController
public class ApplicatController {
    //邮箱工具类
    @Autowired
    Mail mail;
    //邮箱网页模板
    @Autowired
    TemplateEngine templateEngine;
    //招聘服务
    @Autowired
    InviteService inviteService;
    //求职者服务
    @Autowired
    ApplicatService applicatService;
    //企业服务
    @Autowired
    CompanyService companyService;
    //发送服务
    @Autowired
    SendService sendService;
    //MD5加密
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
        modelAndView.setViewName("redirect:/user/index");
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
     * 求职者主页
     * @param applicant
     * @param request
     * @return
     */
    @RequestMapping("user/index")
    public ModelAndView userindex(Applicant applicant, HttpServletRequest request){
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user2 = applicatService.selectAll(user);
        List<Invite> invites = inviteService.selectAll();
        //request.getSession().setAttribute("user",user2);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("invites",invites);
        modelAndView.addObject("user",user2);
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    /**
     * 投递简历
     * @return
     */
    @RequestMapping("user/addsend")
    public boolean addsend(Send send){
        //判断是否存在
        if(sendService.uniq(send)==1)
            return true;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        send.setsTime(date);
        sendService.insertsend(send);
        return true;
    }

    /**
     * 招聘详情页
     * @param request
     * @return
     */
    @RequestMapping("user/invitedetial")
    public ModelAndView invitedetial(Invite invite,HttpServletRequest request){
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user2 = applicatService.selectAll(user);
        Invite invite1 = inviteService.selectByIId(invite.getiId());
        Company company = companyService.selectBycId(invite.getcId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("com",company);
        modelAndView.addObject("invite",invite1);
        modelAndView.addObject("user",user2);
        modelAndView.setViewName("/invitedetail");
        return modelAndView;
    }

    /**
     * 投递详情页
     * @param request
     * @return
     */
    @RequestMapping("user/usersends")
    public ModelAndView usersends(HttpServletRequest request){
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user2 = applicatService.selectAll(user);
        List<Send> sends = sendService.selectAllByuId(user2.getuId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user2);
        modelAndView.addObject("sends",sends);
        modelAndView.setViewName("/sends");
        return modelAndView;
    }

    /**
     * 取消投递
     * @return
     */
    @RequestMapping("user/dropsends")
    public boolean dropsends(Send send){
        sendService.deleteBysId(send.getsId());
        return true;
    }

    /**
     * 求职者搜索框
     * @param request
     * @return
     */
    @RequestMapping("user/search")
    public ModelAndView usersearch(String iName, HttpServletRequest request){
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user2 = applicatService.selectAll(user);
        List<Invite> invites = inviteService.selectlike(iName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("invites",invites);
        modelAndView.addObject("user",user2);
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    /**
     * 求职者智能推荐(协同过滤)
     * @param request
     * @return
     */
    @RequestMapping("user/smart2")
    public ModelAndView smart2(HttpServletRequest request){
        //获取当前登录用户
        Applicant user = (Applicant) request.getSession().getAttribute("user");
        Applicant user2 = applicatService.selectAll(user);
        List<Applicant> select = applicatService.select(user2);
        boolean flag = true;
        UCF ucf = new UCF();
        List<Applicant> maxSimilarity = ucf.getMaxSimilarity(user2, select);
        Set<Invite> invites = new HashSet<Invite>();
        //获得相似用户集合投递简历进行排序，返回结果
        for (Applicant a :
                maxSimilarity) {
            List<Send> sends = sendService.selectAllByuId(a.getuId());
            if(sends.size() != 0) {
                for (Send s : sends) {
                    for (Invite invite : invites) {
                        if(invite.getiId()==s.getiId()){
                            flag = false;
                        }
                    }
                    if(flag){
                        invites.add(inviteService.selectByIId(s.getiId()));
                        flag = true;
                    }

                }
            }

            if(invites.size()>=8){
                break;
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("invites",invites);
        modelAndView.addObject("user",user2);
        modelAndView.setViewName("/index");
        return modelAndView;
    }


    /**
     * 更新简历
     * @param applicant
     * @param request
     * @return
     */
    @RequestMapping("user/updataskill")
    public boolean updataskill(Applicant applicant, HttpServletRequest request){
        applicatService.updataskill(applicant);
        return true;
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
        //从session中删除
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
     *激活账号页面
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
