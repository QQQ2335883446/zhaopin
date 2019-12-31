package com.jiangyi.zhaopin.model;

/**
 * 求职者实体类
 */
public class Applicant {
    private Integer uId;
    private String uName;
    private String uTrueName;
    private String uPwd;
    private String uTel;
    private String uEmail;
    //默认 男
    private String uSex;
    private String uAge;
    private String uStatus;
    private String uBirth;
    //所在城市，默认北京
    private String uCity;
    //激活状态，默认-1
    private Integer uLock;

    private String uSkill;

    private String uExp;

    private String uEva;

    private String uSalary;

    private String uJob;

    public String getuSalary() {
        return uSalary;
    }

    public void setuSalary(String uSalary) {
        this.uSalary = uSalary;
    }

    public String getuJob() {
        return uJob;
    }

    public void setuJob(String uJob) {
        this.uJob = uJob;
    }

    public String getuSkill() {
        return uSkill;
    }

    public void setuSkill(String uSkill) {
        this.uSkill = uSkill;
    }

    public String getuExp() {
        return uExp;
    }

    public void setuExp(String uExp) {
        this.uExp = uExp;
    }

    public String getuEva() {
        return uEva;
    }

    public void setuEva(String uEva) {
        this.uEva = uEva;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuTrueName() {
        return uTrueName;
    }

    public void setuTrueName(String uTrueName) {
        this.uTrueName = uTrueName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public String getuTel() {
        return uTel;
    }

    public void setuTel(String uTel) {
        this.uTel = uTel;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuAge() {
        return uAge;
    }

    public void setuAge(String uAge) {
        this.uAge = uAge;
    }

    public String getuStatus() {
        return uStatus;
    }

    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
    }

    public String getuBirth() {
        return uBirth;
    }

    public void setuBirth(String uBirth) {
        this.uBirth = uBirth;
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity;
    }

    public Integer getuLock() {
        return uLock;
    }

    public void setuLock(Integer uLock) {
        this.uLock = uLock;
    }
}
