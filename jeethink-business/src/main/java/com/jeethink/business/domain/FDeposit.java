package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 存放案卷对象 f_deposit
 * 
 * @author yhb
 * @date 2020-07-22
 */
public class FDeposit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 存入主键 */
    private String fDepositid;

    /** 编号 */
    @Excel(name = "编号")
    private String fCode;

    /** 存入用户Id */
    @Excel(name = "存入用户Id")
    private String fUserid;

    /** 存入用户名称 */
    @Excel(name = "存入用户名称")
    private String fUsername;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCreatedate;

    /** 业务方式（0直接开门，1刷卡） */
    @Excel(name = "业务方式", readConverterExp = "0=直接开门，1刷卡")
    private Integer fType;

    /** 状态（0未存放，1以存放） */
    @Excel(name = "状态", readConverterExp = "0=未存放，1以存放")
    private Integer fState;

    /** 备注 */
    @Excel(name = "备注")
    private String fRemark;

    /** 卡编号 */
    @Excel(name = "卡编号")
    private String fCardcode;
    /** 开门时间 */
    @Excel(name = "开门时间")
    private String fOpendate;
    /** 关门时间 */
    @Excel(name = "关门时间")
    private String fEnddate;
    /** 业务类型（1首次入库，2归还入库） */
    @Excel(name = "业务类型（1首次入库，2归还入库）")
    private String fBusinesstype;
    /** 发卡人员类型（0主办民警，1辅办民警） */
    @Excel(name = "发卡人员类型（0主办民警，1辅办民警）")
    private String fPeopletype;

    public void setfDepositid(String fDepositid) 
    {
        this.fDepositid = fDepositid;
    }

    public String getfDepositid() 
    {
        return fDepositid;
    }
    public void setfCode(String fCode) 
    {
        this.fCode = fCode;
    }

    public String getfCode() 
    {
        return fCode;
    }
    public void setfUserid(String fUserid) 
    {
        this.fUserid = fUserid;
    }

    public String getfUserid() 
    {
        return fUserid;
    }
    public void setfUsername(String fUsername) 
    {
        this.fUsername = fUsername;
    }

    public String getfUsername() 
    {
        return fUsername;
    }
    public void setfCreatedate(Date fCreatedate) 
    {
        this.fCreatedate = fCreatedate;
    }

    public Date getfCreatedate() 
    {
        return fCreatedate;
    }
    public void setfType(Integer fType) 
    {
        this.fType = fType;
    }

    public Integer getfType() 
    {
        return fType;
    }
    public void setfState(Integer fState) 
    {
        this.fState = fState;
    }

    public Integer getfState() 
    {
        return fState;
    }
    public void setfRemark(String fRemark) 
    {
        this.fRemark = fRemark;
    }

    public String getfRemark() 
    {
        return fRemark;
    }
    public void setfCardcode(String fCardcode) 
    {
        this.fCardcode = fCardcode;
    }

    public String getfCardcode() 
    {
        return fCardcode;
    }

    public void setfOpendate(String fOpendate) {
        this.fOpendate = fOpendate;
    }

    public String getfOpendate() {
        return fOpendate;
    }

    public void setfEnddate(String fEnddate) {
        this.fEnddate = fEnddate;
    }

    public String getfEnddate() {
        return fEnddate;
    }

    public void setfBusinesstype(String fBusinesstype) {
        this.fBusinesstype = fBusinesstype;
    }

    public String getfBusinesstype() {
        return fBusinesstype;
    }

    public void setfPeopletype(String fPeopletype) {
        this.fPeopletype = fPeopletype;
    }

    public String getfPeopletype() {
        return fPeopletype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fDepositid", getfDepositid())
            .append("fCode", getfCode())
            .append("fUserid", getfUserid())
            .append("fUsername", getfUsername())
            .append("fCreatedate", getfCreatedate())
            .append("fType", getfType())
            .append("fState", getfState())
            .append("fRemark", getfRemark())
            .append("fCardcode", getfCardcode())
            .toString();
    }
}
