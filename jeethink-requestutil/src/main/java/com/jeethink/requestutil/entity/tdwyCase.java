package com.jeethink.requestutil.entity;

public class tdwyCase {
    /**
     * 案卷状态（0登记1在柜2取出3借阅4移交）
     * */
    private Integer ajbazt;
    /**
     * 案卷编号
     * */
    private String ajbh;
    /**
     * 报关单位
     * */
    private String bgdw;
    /**
     * 保管地点
     * */
    private String bgdd;
    /**
     * 保管人
     * */
    private String bgr;
    /**
     * 录入人
     * */
    private String lrr;
    /**
     * 录入时间
     * */
    private String lrsj;
    /**
     * 来源表Id
     * */
    private String lybid;
    /**
     * 在柜状态(1在柜2不在柜)
     * */
    private Integer zgzt;

    /**
     * 保管单位编号
     * */
    private String bgdwbh;

    public Integer getAjbazt() {
        return ajbazt;
    }

    public void setAjbazt(Integer ajbazt) {
        this.ajbazt = ajbazt;
    }

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public String getBgdw() {
        return bgdw;
    }

    public void setBgdw(String bgdw) {
        this.bgdw = bgdw;
    }

    public String getBgdd() {
        return bgdd;
    }

    public void setBgdd(String bgdd) {
        this.bgdd = bgdd;
    }

    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    public String getLybid() {
        return lybid;
    }

    public void setLybid(String lybid) {
        this.lybid = lybid;
    }

    public Integer getZgzt() {
        return zgzt;
    }

    public void setZgzt(Integer zgzt) {
        this.zgzt = zgzt;
    }

    public String getBgdwbh() {
        return bgdwbh;
    }

    public void setBgdwbh(String bgdwbh) {
        this.bgdwbh = bgdwbh;
    }
}
