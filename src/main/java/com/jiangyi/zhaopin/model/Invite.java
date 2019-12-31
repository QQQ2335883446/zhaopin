package com.jiangyi.zhaopin.model;

/**
 * 招聘信息实体
 */
public class Invite {
    private Integer iId;
    private Integer cId;
    private String iName;
    private String iMoney;
    private String iCity;
    private String iStatus;
    private String iIntro;
    private String cName;

    public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiMoney() {
        return iMoney;
    }

    public void setiMoney(String iMoney) {
        this.iMoney = iMoney;
    }

    public String getiCity() {
        return iCity;
    }

    public void setiCity(String iCity) {
        this.iCity = iCity;
    }

    public String getiStatus() {
        return iStatus;
    }

    public void setiStatus(String iStatus) {
        this.iStatus = iStatus;
    }

    public String getiIntro() {
        return iIntro;
    }

    public void setiIntro(String iIntro) {
        this.iIntro = iIntro;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
