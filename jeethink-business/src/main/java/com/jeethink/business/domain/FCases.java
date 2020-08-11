package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 案卷对象 f_cases
 *
 * @author yhb
 * @date 2020-07-23
 */
public class FCases extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String fId;

    /** 案件编号 */
    @Excel(name = "案件编号")
    private String fCasecode;

    /** 案件名称 */
    @Excel(name = "案件名称")
    private String fCasename;

    /** 简要案情 */
    @Excel(name = "简要案情")
    private String fCasebriefdetail;

    /** 立案日期 */
    @Excel(name = "立案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String fPutdate;

    /** 创建日期 */
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCreatedate;

    /** 民警1编号 */
    @Excel(name = "民警1编号")
    private String fPolice1id;

    /** 民警2编号 */
    @Excel(name = "民警2编号")
    private String fPolice2id;

    /** 民警1名称 */
    @Excel(name = "民警1名称")
    private String fPolice1name;

    /** 民警2名称 */
    @Excel(name = "民警2名称")
    private String fPolice2name;

    /** 办案单位Id */
    @Excel(name = "办案单位Id")
    private String fDepartmentid;

    /** 案件类别 */
    @Excel(name = "案件类别")
    private String fCasetype;

    /** 案由 */
    @Excel(name = "案由")
    private String fCasebrief;

    /** 卷宗柜Id */
    @Excel(name = "卷宗柜Id")
    private String fLockerid;

    /** 卷宗柜名称 */
    @Excel(name = "卷宗柜名称")
    private String fLockername;

    /** 货位Id */
    @Excel(name = "货位Id")
    private String fPositionid;

    /** 货位编号 */
    @Excel(name = "货位编号")
    private String fPositioncode;

    /** 嫌疑人编号 */
    @Excel(name = "嫌疑人编号")
    private String fCriminalcode;

    /** 嫌疑人名称 */
    @Excel(name = "嫌疑人名称")
    private String fCriminalname;

    /** 案发地址 */
    @Excel(name = "案发地址")
    private String fCrimeaddress;
    /** 状态（0登记，1存入，2取出） */
    private Integer fState;
    /** 保存业务字段Begin */
    /**
     * 民警类型（0未录入，1已录入，2平台拉取）
     * */
    private Integer policeType;
    /**
     * 卡号
     * */
    private String cardCode;
    /**
     * 卡Id
     * */
    private String cardId;
    /**
     * 0主办，1辅办
     * */
    private String peopleType;
    /** 保存业务字段End */

    /** 盘点业务查询字段 */
    /** 盘点状态 */
    private String fCheckState;
    /** 盘点备注 */
    private String fRemark;
    /** 盘点时间 */
    private String fCheckDate;
    /** 盘点明细Id */
    private String fCheckDetailId;

    public void setfId(String fId)
    {
        this.fId = fId;
    }

    public String getfId()
    {
        return fId;
    }
    public void setfCasecode(String fCasecode)
    {
        this.fCasecode = fCasecode;
    }

    public String getfCasecode()
    {
        return fCasecode;
    }
    public void setfCasename(String fCasename)
    {
        this.fCasename = fCasename;
    }

    public String getfCasename()
    {
        return fCasename;
    }
    public void setfCasebriefdetail(String fCasebriefdetail)
    {
        this.fCasebriefdetail = fCasebriefdetail;
    }

    public String getfCasebriefdetail()
    {
        return fCasebriefdetail;
    }
    public void setfPutdate(String fPutdate)
    {
        this.fPutdate = fPutdate;
    }

    public String getfPutdate()
    {
        return fPutdate;
    }
    public void setfCreatedate(Date fCreatedate)
    {
        this.fCreatedate = fCreatedate;
    }

    public Date getfCreatedate()
    {
        return fCreatedate;
    }
    public void setfPolice1id(String fPolice1id)
    {
        this.fPolice1id = fPolice1id;
    }

    public String getfPolice1id()
    {
        return fPolice1id;
    }
    public void setfPolice2id(String fPolice2id)
    {
        this.fPolice2id = fPolice2id;
    }

    public String getfPolice2id()
    {
        return fPolice2id;
    }
    public void setfPolice1name(String fPolice1name)
    {
        this.fPolice1name = fPolice1name;
    }

    public String getfPolice1name()
    {
        return fPolice1name;
    }
    public void setfPolice2name(String fPolice2name)
    {
        this.fPolice2name = fPolice2name;
    }

    public String getfPolice2name()
    {
        return fPolice2name;
    }
    public void setfDepartmentid(String fDepartmentid)
    {
        this.fDepartmentid = fDepartmentid;
    }

    public String getfDepartmentid()
    {
        return fDepartmentid;
    }
    public void setfCasetype(String fCasetype)
    {
        this.fCasetype = fCasetype;
    }

    public String getfCasetype()
    {
        return fCasetype;
    }
    public void setfCasebrief(String fCasebrief)
    {
        this.fCasebrief = fCasebrief;
    }

    public String getfCasebrief()
    {
        return fCasebrief;
    }
    public void setfLockerid(String fLockerid)
    {
        this.fLockerid = fLockerid;
    }

    public String getfLockerid()
    {
        return fLockerid;
    }
    public void setfLockername(String fLockername)
    {
        this.fLockername = fLockername;
    }

    public String getfLockername()
    {
        return fLockername;
    }
    public void setfPositionid(String fPositionid)
    {
        this.fPositionid = fPositionid;
    }

    public String getfPositionid()
    {
        return fPositionid;
    }
    public void setfPositioncode(String fPositioncode)
    {
        this.fPositioncode = fPositioncode;
    }

    public String getfPositioncode()
    {
        return fPositioncode;
    }
    public void setfCriminalcode(String fCriminalcode)
    {
        this.fCriminalcode = fCriminalcode;
    }

    public String getfCriminalcode()
    {
        return fCriminalcode;
    }
    public void setfCriminalname(String fCriminalname)
    {
        this.fCriminalname = fCriminalname;
    }

    public String getfCriminalname()
    {
        return fCriminalname;
    }
    public void setfCrimeaddress(String fCrimeaddress)
    {
        this.fCrimeaddress = fCrimeaddress;
    }

    public String getfCrimeaddress()
    {
        return fCrimeaddress;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Integer getfState() {
        return fState;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setPoliceType(Integer policeType) {
        this.policeType = policeType;
    }

    public Integer getPoliceType() {
        return policeType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public String getPeopleType() {
        return peopleType;
    }

    public void setfCheckState(String fCheckState) {
        this.fCheckState = fCheckState;
    }

    public String getfCheckState() {
        return fCheckState;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    public String getfRemark() {
        return fRemark;
    }

    public void setfCheckDate(String fCheckDate) {
        this.fCheckDate = fCheckDate;
    }

    public String getfCheckDate() {
        return fCheckDate;
    }

    public void setfCheckDetailId(String fCheckDetailId) {
        this.fCheckDetailId = fCheckDetailId;
    }

    public String getfCheckDetailId() {
        return fCheckDetailId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("fId", getfId())
                .append("fCasecode", getfCasecode())
                .append("fCasename", getfCasename())
                .append("fCasebriefdetail", getfCasebriefdetail())
                .append("fPutdate", getfPutdate())
                .append("fCreatedate", getfCreatedate())
                .append("fPolice1id", getfPolice1id())
                .append("fPolice2id", getfPolice2id())
                .append("fPolice1name", getfPolice1name())
                .append("fPolice2name", getfPolice2name())
                .append("fDepartmentid", getfDepartmentid())
                .append("fCasetype", getfCasetype())
                .append("fCasebrief", getfCasebrief())
                .append("fLockerid", getfLockerid())
                .append("fLockername", getfLockername())
                .append("fPositionid", getfPositionid())
                .append("fPositioncode", getfPositioncode())
                .append("fCriminalcode", getfCriminalcode())
                .append("fCriminalname", getfCriminalname())
                .append("fCrimeaddress", getfCrimeaddress())
                .toString();
    }
}
