package com.jeethink.basicInfo.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 卡对象 f_card
 * 
 * @author yhb
 * @date 2020-07-23
 */
public class FCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 卡主键 */
    private String fCardid;

    /** 卡编号 */
    @Excel(name = "卡编号")
    private String fCardcode;

    /** 使用状态（0未使用，1使用中） */
    @Excel(name = "使用状态", readConverterExp = "0=未使用，1使用中")
    private String fState;

    /** 使用人id */
    @Excel(name = "使用人id")
    private String fUserid;

    /** 使用人名称 */
    @Excel(name = "使用人名称")
    private String fUsername;

    /** 创建用户Id */
    @Excel(name = "创建用户Id")
    private String fCreateuserid;

    /** 创建用户名称 */
    @Excel(name = "创建用户名称")
    private String fCreateusername;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCreatedate;
    /** 案卷柜编号 */
    private String fLockercode;
    /** 案卷柜货位编号 */
    private String fPositioncode;

    public void setfCardid(String fCardid) 
    {
        this.fCardid = fCardid;
    }

    public String getfCardid() 
    {
        return fCardid;
    }
    public void setfCardcode(String fCardcode) 
    {
        this.fCardcode = fCardcode;
    }

    public String getfCardcode() 
    {
        return fCardcode;
    }
    public void setfState(String fState)
    {
        this.fState = fState;
    }

    public String getfState()
    {
        return fState;
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

    public void setfLockercode(String fLockercode) {
        this.fLockercode = fLockercode;
    }

    public String getfLockercode() {
        return fLockercode;
    }

    public void setfPositioncode(String fPositioncode) {
        this.fPositioncode = fPositioncode;
    }

    public String getfPositioncode() {
        return fPositioncode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fCardid", getfCardid())
            .append("fCardcode", getfCardcode())
            .append("fState", getfState())
            .append("fUserid", getfUserid())
            .append("fUsername", getfUsername())
            .append("fCreateuserid", getfCreateuserid())
            .append("fCreateusername", getfCreateusername())
            .append("fCreatedate", getfCreatedate())
            .toString();
    }
}
