package com.jeethink.basicInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 货位对象 f_position
 * 
 * @author yhb
 * @date 2020-07-21
 */
public class FPosition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 货位Id */
    private String fPositionid;

    /** 货位名称 */
    @Excel(name = "货位名称")
    private String fPositionname;

    /** 货位编号 */
    @Excel(name = "货位编号")
    private String fPositioncode;

    /** 使用用户Id */
    @Excel(name = "使用用户Id")
    private String fUseuserid;

    /** 使用用户名称 */
    @Excel(name = "使用用户名称")
    private String fUseusername;

    /** 箱体外键 */
    @Excel(name = "箱体外键")
    private String fBoxid;

    /** 卷宗柜外键 */
    @Excel(name = "卷宗柜外键")
    private String fLockerid;

    public void setfPositionid(String fPositionid) 
    {
        this.fPositionid = fPositionid;
    }

    public String getfPositionid() 
    {
        return fPositionid;
    }
    public void setfPositionname(String fPositionname) 
    {
        this.fPositionname = fPositionname;
    }

    public String getfPositionname() 
    {
        return fPositionname;
    }
    public void setfPositioncode(String fPositioncode) 
    {
        this.fPositioncode = fPositioncode;
    }

    public String getfPositioncode() 
    {
        return fPositioncode;
    }
    public void setfUseuserid(String fUseuserid) 
    {
        this.fUseuserid = fUseuserid;
    }

    public String getfUseuserid() 
    {
        return fUseuserid;
    }
    public void setfUseusername(String fUseusername) 
    {
        this.fUseusername = fUseusername;
    }

    public String getfUseusername() 
    {
        return fUseusername;
    }
    public void setfBoxid(String fBoxid) 
    {
        this.fBoxid = fBoxid;
    }

    public String getfBoxid() 
    {
        return fBoxid;
    }
    public void setfLockerid(String fLockerid) 
    {
        this.fLockerid = fLockerid;
    }

    public String getfLockerid() 
    {
        return fLockerid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fPositionid", getfPositionid())
            .append("fPositionname", getfPositionname())
            .append("fPositioncode", getfPositioncode())
            .append("fUseuserid", getfUseuserid())
            .append("fUseusername", getfUseusername())
            .append("fBoxid", getfBoxid())
            .append("fLockerid", getfLockerid())
            .toString();
    }
}
