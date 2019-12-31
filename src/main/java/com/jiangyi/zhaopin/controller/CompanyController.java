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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公司控制层
 */
@RestController
public class CompanyController {
    //企业服务
    @Autowired
    CompanyService companyService;
    //招聘信息
    @Autowired
    InviteService inviteService;
    //发送服务
    @Autowired
    SendService sendService;
    //用户
    @Autowired
    ApplicatService applicatService;
    Md5 md5 = new Md5();

    /**
     * 企业登录
     * @param company
     * @param request
     * @return
     */
    @RequestMapping("com/login")
    public ModelAndView login(Company company, HttpServletRequest request){
        //解密
        company.setcPwd(md5.MD5Encode(company.getcPwd(),"utf-8"));
        ModelAndView modelAndView = new ModelAndView();
        String msg = "";
        Company com = companyService.selectByNP(company);

        if(com==null){
            msg="账号或密码错误！！";
            modelAndView.addObject("msg",msg);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        //modelAndView.addObject("com",com);
        //将成功登录的用户存入session
        request.getSession().setAttribute("com",com);
        //重定向至主页面
        modelAndView.setViewName("redirect:/cindex");
        return modelAndView;
    }

    /**
     * 主页
     * @param company
     * @param request
     * @return
     */
    @RequestMapping("/cindex")
    public ModelAndView comindex(Company company, HttpServletRequest request){
        Company user = (Company) request.getSession().getAttribute("com");
        Company user2 = companyService.selectAll(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("com",user2);
        modelAndView.setViewName("/comindex");
        return modelAndView;
    }

    /**
     * 信息修改页面
     * @param company
     * @param request
     * @return
     */
    @RequestMapping("com/datam")
    public ModelAndView comdatamanager(Company company, HttpServletRequest request){
        Company user = (Company) request.getSession().getAttribute("com");
        Company com = companyService.selectAll(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("com",com);
        modelAndView.setViewName("/comdatamanager");
        return modelAndView;
    }

    /**
     * 智能推荐页面(协同过滤)
     * @param company
     * @param request
     * @return
     */
    @RequestMapping("com/csmart")
    public ModelAndView csmart(Company company, HttpServletRequest request){
        Company user = (Company) request.getSession().getAttribute("com");
        Company com = companyService.selectAll(user);
        List<Invite> invites = inviteService.selectAllByCID(com.getcId());
        List<Applicant> applicants = applicatService.selectlist();
        ArrayList<Applicant> list = new ArrayList<>();
        ArrayList<Invite> list2 = new ArrayList<>();
        String[] status = {"初中及以下","高中","大专","本科","硕士","博士"};
        int userstatus = 0;
        int istatus = 0;
        int flag = 0;
        //遍历在招岗位
        for(Invite invite:invites){
            for(Applicant applicant:applicants){
                //限制数量
                for(Invite invite1:list2){
                    if(invite1.getiId()==invite.getiId()){
                        flag++;
                    }
                }
                if(flag>=1){
                    flag=0;
                    break;
                }
                //过滤结果
                if(applicant.getuJob().equals(invite.getiName())){
                    for(int i=0;i<status.length;i++) {
                        if (applicant.getuStatus().equals(status[i])) {
                            userstatus = i;
                        }
                        if (invite.getiStatus().equals(status[i])) {
                            istatus = i;
                        }
                       }
                        if(userstatus>=istatus){
                            String suserm = applicant.getuSalary().replace("K","");
                            String sim = invite.getiMoney().replace("K","");
                            if(Math.abs(Integer.valueOf(sim)-Integer.valueOf(suserm))<=2){
                                    list.add(applicant);
                                    list2.add(invite);
                                    flag  = 0;
                            }
                        }
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",list);
        modelAndView.addObject("invites",list2);
        modelAndView.addObject("com",com);
        modelAndView.setViewName("/csmart");
        return modelAndView;
    }


    /**
     * 管理简历页面
     * @param request
     * @return
     */
    @RequestMapping("com/csend")
    public ModelAndView csend(HttpServletRequest request){
        Company user = (Company) request.getSession().getAttribute("com");
        Company com = companyService.selectAll(user);
        List<Send> sends = sendService.selectAllBycId(com.getcId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sends",sends);
        modelAndView.addObject("com",com);
        modelAndView.setViewName("/csend");
        return modelAndView;
    }

    /**
     * 简历页面
     * @param request
     * @return
     */
    @RequestMapping("com/resume")
    public ModelAndView resume(Integer uId,HttpServletRequest request){
        Applicant user = applicatService.selectByuId(uId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/cresume");
        return modelAndView;
    }


    /**
     * 取消投递
     * @return
     */
    @RequestMapping("com/dropsends")
    public boolean dropsends(Send send){
        sendService.deleteBysId(send.getsId());
        return true;
    }

    /**
     * 查找招聘信息
     * @return
     */
    @RequestMapping("com/useinvite")
    public Invite useinvite(Integer iId){
        return inviteService.selectByIId(iId);
    }


    /**
     * 更新简历
     * @return
     */
    @RequestMapping("com/updatasends")
    public boolean updatasends(Send send){
        sendService.updatasend(send);
        return true;
    }

    /**
     * 更新企业信息
     * @param company
     * @return
     */
    @RequestMapping("com/updatacom")
    public boolean updatac(Company company){
        companyService.updataByCName(company);
        return true;
    }

    /**
     * 通过id删除
     * @param iId
     * @return
     */
    @RequestMapping("com/deleteinvite")
    public boolean deleteById(Integer iId){
        sendService.deleteByiId(iId);
        inviteService.deleteById(iId);
        return true;
    }


    /**
     * 投递简历
     * @return
     */
    @RequestMapping("com/addsend2")
    public boolean addsend2(Send send){
        if(sendService.uniq(send)==1)
            return true;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        send.setsTime(date);
        sendService.insertsend2(send);
        return true;
    }


    /**
     * 更新招聘信息
     * @param invite
     * @return
     */
    @RequestMapping("com/updatainvite")
    public boolean updatainvite(Invite invite){
        inviteService.updata(invite);
        return true;
    }

    /**
     * 添加招聘信息
     * @param invite
     * @return
     */
    @RequestMapping("com/addinvite")
    public boolean addinvite(Invite invite){
        inviteService.insert(invite);
        return true;
    }

    /**
     * 招聘信息页面
     * @param company
     * @param request
     * @return
     */

    @RequestMapping("com/invite")
    public ModelAndView invite(Company company, HttpServletRequest request){
        Company user = (Company) request.getSession().getAttribute("com");
        Company com = companyService.selectAll(user);
        List<Invite> invites = inviteService.selectAllByCID(com.getcId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("com",com);
        modelAndView.addObject("invites",invites);
        modelAndView.setViewName("/invite");
        return modelAndView;
    }


    /**
     * 退出登录
     */
    @RequestMapping("com/logout")
    public ModelAndView comlogout(Company company, HttpServletRequest request){
        request.getSession().removeAttribute("com");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    /**
     * 判断企业是否存在
     * @return
     */
    @RequestMapping("com/exist")
    public boolean exist(Company company){
        Company company1 = companyService.selectAll(company);
        if(company1==null){
            return true;
        }else
            // ModelAndView modelAndView = new ModelAndView();
            return false;
    }

    /**
     * 注册
     * @param company
     * @return
     */
    @RequestMapping("com/reg")
    public boolean reg(Company company){
        //加密
        company.setcPwd(md5.MD5Encode(company.getcPwd(),"utf-8"));
        companyService.insertCom(company);
        return true;
    }
}
