package com.jeethink.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 天地伟业案卷对象 tdwy_case
 * 
 * @author yhb
 * @date 2021-02-05
 */
public class TdwyCase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String sId;

    /** null */
    @Excel(name = "null")
    private String ajbh;

    /** null */
    @Excel(name = "null")
    private String ajmc;

    /** null */
    @Excel(name = "null")
    private String ajlb;

    /** null */
    @Excel(name = "null")
    private String ajlx;

    /** null */
    @Excel(name = "null")
    private String ajly;

    /** null */
    @Excel(name = "null")
    private String ajzt;

    /** null */
    @Excel(name = "null")
    private String gaah;

    /** null */
    @Excel(name = "null")
    private String aqms;

    /** null */
    @Excel(name = "null")
    private String aymc;

    /** null */
    @Excel(name = "null")
    private String barxm;

    /** null */
    @Excel(name = "null")
    private String basj;

    /** null */
    @Excel(name = "null")
    private String afcs;

    /** null */
    @Excel(name = "null")
    private String afsj;

    /** null */
    @Excel(name = "null")
    private String afcsmc;

    /** null */
    @Excel(name = "null")
    private String afdgxjg;

    /** null */
    @Excel(name = "null")
    private String afdgxjglxdh;

    /** null */
    @Excel(name = "null")
    private String afdq;

    /** null */
    @Excel(name = "null")
    private String afdqmc;

    /** null */
    @Excel(name = "null")
    private String ajbhZc;

    /** null */
    @Excel(name = "null")
    private String ajbhZr;

    /** null */
    @Excel(name = "null")
    private String bczcsj;

    /** null */
    @Excel(name = "null")
    private String bylasj;

    /** null */
    @Excel(name = "null")
    private String cbdw;

    /** null */
    @Excel(name = "null")
    private String cbdwmc;

    /** null */
    @Excel(name = "null")
    private String cbr;

    /** null */
    @Excel(name = "null")
    private String cbrxm;

    /** null */
    @Excel(name = "null")
    private String cfkyws;

    /** null */
    @Excel(name = "null")
    private String cxajsj;

    /** null */
    @Excel(name = "null")
    private String djsj;

    /** null */
    @Excel(name = "null")
    private String fadxz;

    /** null */
    @Excel(name = "null")
    private String sfsacw;

    /** null */
    @Excel(name = "null")
    private String sfsw;

    /** null */
    @Excel(name = "null")
    private String ladwdm;

    /** null */
    @Excel(name = "null")
    private String ladwxc;

    /** null */
    @Excel(name = "null")
    private String lasj;

    /** null */
    @Excel(name = "null")
    private String zhgxsj;

    /** null */
    @Excel(name = "null")
    private String lazcjl;

    /** null */
    @Excel(name = "null")
    private String lazcy;

    /** null */
    @Excel(name = "null")
    private String lazcyxm;

    /** null */
    @Excel(name = "null")
    private String pasj;

    /** null */
    @Excel(name = "null")
    private String jasj;

    /** null */
    @Excel(name = "null")
    private String jd;

    /** null */
    @Excel(name = "null")
    private String jjdjid;

    /** null */
    @Excel(name = "null")
    private String jqbh;

    /** null */
    @Excel(name = "null")
    private String pzbh;

    /** null */
    @Excel(name = "null")
    private String sfcx;

    /** null */
    @Excel(name = "null")
    private String sfzyzfjgjbaj;

    /** null */
    @Excel(name = "null")
    private String shrxm;

    /** null */
    @Excel(name = "null")
    private String sldw;

    /** null */
    @Excel(name = "null")
    private String sldwmc;

    /** null */
    @Excel(name = "null")
    private String slr;

    /** null */
    @Excel(name = "null")
    private String slrxm;

    /** null */
    @Excel(name = "null")
    private String slsj;

    /** null */
    @Excel(name = "null")
    private String tfdw;

    /** null */
    @Excel(name = "null")
    private String tfdwmc;

    /** null */
    @Excel(name = "null")
    private String tfr;

    /** null */
    @Excel(name = "null")
    private String xbr;

    /** null */
    @Excel(name = "null")
    private String xbdw;

    /**
     * 承办人姓名
     * */
    private String cbrmx;

    /**
     * 协办人姓名
     * */
    private String xbrxm;
    /**
     * 机构全称
     * */
    private String qc;
    /**
     * 承办人警号
     * */
    private String cbrjh;
    /**
     * 协办人警号
     * */
    private String xbrjh;

    public void setsId(String sId) 
    {
        this.sId = sId;
    }

    public String getsId() 
    {
        return sId;
    }
    public void setAjbh(String ajbh) 
    {
        this.ajbh = ajbh;
    }

    public String getAjbh() 
    {
        return ajbh;
    }
    public void setAjmc(String ajmc) 
    {
        this.ajmc = ajmc;
    }

    public String getAjmc() 
    {
        return ajmc;
    }
    public void setAjlb(String ajlb) 
    {
        this.ajlb = ajlb;
    }

    public String getAjlb() 
    {
        return ajlb;
    }
    public void setAjlx(String ajlx) 
    {
        this.ajlx = ajlx;
    }

    public String getAjlx() 
    {
        return ajlx;
    }
    public void setAjly(String ajly) 
    {
        this.ajly = ajly;
    }

    public String getAjly() 
    {
        return ajly;
    }
    public void setAjzt(String ajzt) 
    {
        this.ajzt = ajzt;
    }

    public String getAjzt() 
    {
        return ajzt;
    }
    public void setGaah(String gaah) 
    {
        this.gaah = gaah;
    }

    public String getGaah() 
    {
        return gaah;
    }
    public void setAqms(String aqms) 
    {
        this.aqms = aqms;
    }

    public String getAqms() 
    {
        return aqms;
    }
    public void setAymc(String aymc) 
    {
        this.aymc = aymc;
    }

    public String getAymc() 
    {
        return aymc;
    }
    public void setBarxm(String barxm) 
    {
        this.barxm = barxm;
    }

    public String getBarxm() 
    {
        return barxm;
    }
    public void setBasj(String basj) 
    {
        this.basj = basj;
    }

    public String getBasj() 
    {
        return basj;
    }
    public void setAfcs(String afcs) 
    {
        this.afcs = afcs;
    }

    public String getAfcs() 
    {
        return afcs;
    }
    public void setAfsj(String afsj) 
    {
        this.afsj = afsj;
    }

    public String getAfsj() 
    {
        return afsj;
    }
    public void setAfcsmc(String afcsmc) 
    {
        this.afcsmc = afcsmc;
    }

    public String getAfcsmc() 
    {
        return afcsmc;
    }
    public void setAfdgxjg(String afdgxjg) 
    {
        this.afdgxjg = afdgxjg;
    }

    public String getAfdgxjg() 
    {
        return afdgxjg;
    }
    public void setAfdgxjglxdh(String afdgxjglxdh) 
    {
        this.afdgxjglxdh = afdgxjglxdh;
    }

    public String getAfdgxjglxdh() 
    {
        return afdgxjglxdh;
    }
    public void setAfdq(String afdq) 
    {
        this.afdq = afdq;
    }

    public String getAfdq() 
    {
        return afdq;
    }
    public void setAfdqmc(String afdqmc) 
    {
        this.afdqmc = afdqmc;
    }

    public String getAfdqmc() 
    {
        return afdqmc;
    }
    public void setAjbhZc(String ajbhZc) 
    {
        this.ajbhZc = ajbhZc;
    }

    public String getAjbhZc() 
    {
        return ajbhZc;
    }
    public void setAjbhZr(String ajbhZr) 
    {
        this.ajbhZr = ajbhZr;
    }

    public String getAjbhZr() 
    {
        return ajbhZr;
    }
    public void setBczcsj(String bczcsj) 
    {
        this.bczcsj = bczcsj;
    }

    public String getBczcsj() 
    {
        return bczcsj;
    }
    public void setBylasj(String bylasj) 
    {
        this.bylasj = bylasj;
    }

    public String getBylasj() 
    {
        return bylasj;
    }
    public void setCbdw(String cbdw) 
    {
        this.cbdw = cbdw;
    }

    public String getCbdw() 
    {
        return cbdw;
    }
    public void setCbdwmc(String cbdwmc) 
    {
        this.cbdwmc = cbdwmc;
    }

    public String getCbdwmc() 
    {
        return cbdwmc;
    }
    public void setCbr(String cbr) 
    {
        this.cbr = cbr;
    }

    public String getCbr() 
    {
        return cbr;
    }
    public void setCbrxm(String cbrxm) 
    {
        this.cbrxm = cbrxm;
    }

    public String getCbrxm() 
    {
        return cbrxm;
    }
    public void setCfkyws(String cfkyws) 
    {
        this.cfkyws = cfkyws;
    }

    public String getCfkyws() 
    {
        return cfkyws;
    }
    public void setCxajsj(String cxajsj) 
    {
        this.cxajsj = cxajsj;
    }

    public String getCxajsj() 
    {
        return cxajsj;
    }
    public void setDjsj(String djsj) 
    {
        this.djsj = djsj;
    }

    public String getDjsj() 
    {
        return djsj;
    }
    public void setFadxz(String fadxz) 
    {
        this.fadxz = fadxz;
    }

    public String getFadxz() 
    {
        return fadxz;
    }
    public void setSfsacw(String sfsacw) 
    {
        this.sfsacw = sfsacw;
    }

    public String getSfsacw() 
    {
        return sfsacw;
    }
    public void setSfsw(String sfsw) 
    {
        this.sfsw = sfsw;
    }

    public String getSfsw() 
    {
        return sfsw;
    }
    public void setLadwdm(String ladwdm) 
    {
        this.ladwdm = ladwdm;
    }

    public String getLadwdm() 
    {
        return ladwdm;
    }
    public void setLadwxc(String ladwxc) 
    {
        this.ladwxc = ladwxc;
    }

    public String getLadwxc() 
    {
        return ladwxc;
    }
    public void setLasj(String lasj) 
    {
        this.lasj = lasj;
    }

    public String getLasj() 
    {
        return lasj;
    }
    public void setZhgxsj(String zhgxsj) 
    {
        this.zhgxsj = zhgxsj;
    }

    public String getZhgxsj() 
    {
        return zhgxsj;
    }
    public void setLazcjl(String lazcjl) 
    {
        this.lazcjl = lazcjl;
    }

    public String getLazcjl() 
    {
        return lazcjl;
    }
    public void setLazcy(String lazcy) 
    {
        this.lazcy = lazcy;
    }

    public String getLazcy() 
    {
        return lazcy;
    }
    public void setLazcyxm(String lazcyxm) 
    {
        this.lazcyxm = lazcyxm;
    }

    public String getLazcyxm() 
    {
        return lazcyxm;
    }
    public void setPasj(String pasj) 
    {
        this.pasj = pasj;
    }

    public String getPasj() 
    {
        return pasj;
    }
    public void setJasj(String jasj) 
    {
        this.jasj = jasj;
    }

    public String getJasj() 
    {
        return jasj;
    }
    public void setJd(String jd) 
    {
        this.jd = jd;
    }

    public String getJd() 
    {
        return jd;
    }
    public void setJjdjid(String jjdjid) 
    {
        this.jjdjid = jjdjid;
    }

    public String getJjdjid() 
    {
        return jjdjid;
    }
    public void setJqbh(String jqbh) 
    {
        this.jqbh = jqbh;
    }

    public String getJqbh() 
    {
        return jqbh;
    }
    public void setPzbh(String pzbh) 
    {
        this.pzbh = pzbh;
    }

    public String getPzbh() 
    {
        return pzbh;
    }
    public void setSfcx(String sfcx) 
    {
        this.sfcx = sfcx;
    }

    public String getSfcx() 
    {
        return sfcx;
    }
    public void setSfzyzfjgjbaj(String sfzyzfjgjbaj) 
    {
        this.sfzyzfjgjbaj = sfzyzfjgjbaj;
    }

    public String getSfzyzfjgjbaj() 
    {
        return sfzyzfjgjbaj;
    }
    public void setShrxm(String shrxm) 
    {
        this.shrxm = shrxm;
    }

    public String getShrxm() 
    {
        return shrxm;
    }
    public void setSldw(String sldw) 
    {
        this.sldw = sldw;
    }

    public String getSldw() 
    {
        return sldw;
    }
    public void setSldwmc(String sldwmc) 
    {
        this.sldwmc = sldwmc;
    }

    public String getSldwmc() 
    {
        return sldwmc;
    }
    public void setSlr(String slr) 
    {
        this.slr = slr;
    }

    public String getSlr() 
    {
        return slr;
    }
    public void setSlrxm(String slrxm) 
    {
        this.slrxm = slrxm;
    }

    public String getSlrxm() 
    {
        return slrxm;
    }
    public void setSlsj(String slsj) 
    {
        this.slsj = slsj;
    }

    public String getSlsj() 
    {
        return slsj;
    }
    public void setTfdw(String tfdw) 
    {
        this.tfdw = tfdw;
    }

    public String getTfdw() 
    {
        return tfdw;
    }
    public void setTfdwmc(String tfdwmc) 
    {
        this.tfdwmc = tfdwmc;
    }

    public String getTfdwmc() 
    {
        return tfdwmc;
    }
    public void setTfr(String tfr) 
    {
        this.tfr = tfr;
    }

    public String getTfr() 
    {
        return tfr;
    }
    public void setXbr(String xbr) 
    {
        this.xbr = xbr;
    }

    public String getXbr() 
    {
        return xbr;
    }
    public void setXbdw(String xbdw) 
    {
        this.xbdw = xbdw;
    }

    public String getXbdw() 
    {
        return xbdw;
    }

    public String getCbrmx() {
        return cbrmx;
    }

    public void setCbrmx(String cbrmx) {
        this.cbrmx = cbrmx;
    }

    public String getXbrxm() {
        return xbrxm;
    }

    public void setXbrxm(String xbrxm) {
        this.xbrxm = xbrxm;
    }

    public String getQc() {
        return qc;
    }

    public void setQc(String qc) {
        this.qc = qc;
    }

    public String getCbrjh() {
        return cbrjh;
    }

    public void setCbrjh(String cbrjh) {
        this.cbrjh = cbrjh;
    }

    public String getXbrjh() {
        return xbrjh;
    }

    public void setXbrjh(String xbrjh) {
        this.xbrjh = xbrjh;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("ajbh", getAjbh())
            .append("ajmc", getAjmc())
            .append("ajlb", getAjlb())
            .append("ajlx", getAjlx())
            .append("ajly", getAjly())
            .append("ajzt", getAjzt())
            .append("gaah", getGaah())
            .append("aqms", getAqms())
            .append("aymc", getAymc())
            .append("barxm", getBarxm())
            .append("basj", getBasj())
            .append("afcs", getAfcs())
            .append("afsj", getAfsj())
            .append("afcsmc", getAfcsmc())
            .append("afdgxjg", getAfdgxjg())
            .append("afdgxjglxdh", getAfdgxjglxdh())
            .append("afdq", getAfdq())
            .append("afdqmc", getAfdqmc())
            .append("ajbhZc", getAjbhZc())
            .append("ajbhZr", getAjbhZr())
            .append("bczcsj", getBczcsj())
            .append("bylasj", getBylasj())
            .append("cbdw", getCbdw())
            .append("cbdwmc", getCbdwmc())
            .append("cbr", getCbr())
            .append("cbrxm", getCbrxm())
            .append("cfkyws", getCfkyws())
            .append("cxajsj", getCxajsj())
            .append("djsj", getDjsj())
            .append("fadxz", getFadxz())
            .append("sfsacw", getSfsacw())
            .append("sfsw", getSfsw())
            .append("ladwdm", getLadwdm())
            .append("ladwxc", getLadwxc())
            .append("lasj", getLasj())
            .append("zhgxsj", getZhgxsj())
            .append("lazcjl", getLazcjl())
            .append("lazcy", getLazcy())
            .append("lazcyxm", getLazcyxm())
            .append("pasj", getPasj())
            .append("jasj", getJasj())
            .append("jd", getJd())
            .append("jjdjid", getJjdjid())
            .append("jqbh", getJqbh())
            .append("pzbh", getPzbh())
            .append("sfcx", getSfcx())
            .append("sfzyzfjgjbaj", getSfzyzfjgjbaj())
            .append("shrxm", getShrxm())
            .append("sldw", getSldw())
            .append("sldwmc", getSldwmc())
            .append("slr", getSlr())
            .append("slrxm", getSlrxm())
            .append("slsj", getSlsj())
            .append("tfdw", getTfdw())
            .append("tfdwmc", getTfdwmc())
            .append("tfr", getTfr())
            .append("xbr", getXbr())
            .append("xbdw", getXbdw())
            .toString();
    }
}
