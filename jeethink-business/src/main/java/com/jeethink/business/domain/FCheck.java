package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 盘点主表对象 f_check
 * 
 * @author yhb
 * @date 2020-08-07
 */
public class FCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String fCheckid;

    /** 卷宗柜Id */
    private String fLockerid;

    /** 盘点名称 */
    @Excel(name = "盘点名称")
    private String fName;

    /** 盘点编号 */
    @Excel(name = "盘点编号")
    private String fCode;

    /** 状态（0未盘点，1盘点中，2已盘点） */
    private Integer fState;

    /** 创建用户Id */
    @Excel(name = "创建用户Id")
    private String fCreateuserid;

    /** 创建用户名 */
    @Excel(name = "创建用户名")
    private String fCreateusername;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCreatedate;

    public void setfCheckid(String fCheckid) 
    {
        this.fCheckid = fCheckid;
    }

    public String getfCheckid() 
    {
        return fCheckid;
    }

    public void setfLockerid(String fLockerid) {
        this.fLockerid = fLockerid;
    }

    public String getfLockerid() {
        return fLockerid;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public String getfName() 
    {
        return fName;
    }
    public void setfCode(String fCode) 
    {
        this.fCode = fCode;
    }

    public String getfCode() 
    {
        return fCode;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfCreateuserid(String fCreateuserid)
    {
        this.fCreateuserid = fCreateuserid;
    }

    public String getfCreateuserid() 
    {
        return fCreateuserid;
    }
    public void setfCreateusername(String fCreateusername) 
    {
        this.fCreateusername = fCreateusername;
    }

    public String getfCreateusername() 
    {
        return fCreateusername;
    }
    public void setfCreatedate(Date fCreatedate) 
    {
        this.fCreatedate = fCreatedate;
    }

    public Date getfCreatedate() 
    {
        return fCreatedate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fCheckid", getfCheckid())
            .append("fName", getfName())
            .append("fCode", getfCode())
            .append("fCreateuserid", getfCreateuserid())
            .append("fCreateusername", getfCreateusername())
            .append("fCreatedate", getfCreatedate())
            .toString();
    }
}
