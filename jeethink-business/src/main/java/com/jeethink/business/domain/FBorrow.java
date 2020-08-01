package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 借阅对象 f_borrow
 * 
 * @author yhb
 * @date 2020-07-28
 */
public class FBorrow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出库主键 */
    private String fBorrowid;

    /** 编号 */
    @Excel(name = "编号")
    private String fCode;

    /** 操作用户Id */
    @Excel(name = "操作用户Id")
    private String fUserid;

    /** 操作用户名称 */
    @Excel(name = "操作用户名称")
    private String fUsername;

    /** 操作时间 */
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCreatedate;

    /** 业务方式（0直接开门，1刷卡） */
    @Excel(name = "业务方式", readConverterExp = "0=直接开门，1刷卡")
    private Integer fType;

    /** 状态（0未取出，1已取出） */
    @Excel(name = "状态", readConverterExp = "0=0未取出，1已取出")
    private Integer fState;

    /** 卡编号 */
    @Excel(name = "卡编号")
    private String fCardcode;

    /** 开门时间 */
    @Excel(name = "开门时间")
    private String fOpendate;

    /** 关门时间 */
    @Excel(name = "关门时间")
    private String fEnddate;
    /**
     * 备注
     * */
    private String fRemark;
    /** 发卡人员类型（0主办民警，1辅办民警） */
    @Excel(name = "发卡人员类型（0主办民警，1辅办民警）")
    private String fPeopletype;

    public void setfBorrowid(String fBorrowid) 
    {
        this.fBorrowid = fBorrowid;
    }

    public String getfBorrowid() 
    {
        return fBorrowid;
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
    public void setfCardcode(String fCardcode) 
    {
        this.fCardcode = fCardcode;
    }

    public String getfCardcode() 
    {
        return fCardcode;
    }
    public void setfOpendate(String fOpendate) 
    {
        this.fOpendate = fOpendate;
    }

    public String getfOpendate() 
    {
        return fOpendate;
    }
    public void setfEnddate(String fEnddate) 
    {
        this.fEnddate = fEnddate;
    }

    public String getfEnddate() 
    {
        return fEnddate;
    }

    public void setfRemark(String fRemark) {
        this.fRemark = fRemark;
    }

    public String getfRemark() {
        return fRemark;
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
            .append("fBorrowid", getfBorrowid())
            .append("fCode", getfCode())
            .append("fUserid", getfUserid())
            .append("fUsername", getfUsername())
            .append("fCreatedate", getfCreatedate())
            .append("fType", getfType())
            .append("fState", getfState())
            .append("fCardcode", getfCardcode())
            .append("fOpendate", getfOpendate())
            .append("fEnddate", getfEnddate())
            .toString();
    }
}
