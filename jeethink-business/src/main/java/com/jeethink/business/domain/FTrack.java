package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 案卷轨迹对象 f_track
 * 
 * @author yhb
 * @date 2020-08-03
 */
public class FTrack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String fTrackid;

    /** 案卷编号 */
    @Excel(name = "案卷编号")
    private String fCasecode;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String fBusinesstype;

    /** 业务主表id */
    @Excel(name = "业务主表id")
    private String fBusinessid;

    /** 业务明细id */
    @Excel(name = "业务明细id")
    private String fBusinessdetailid;

    /** 创建用户id */
    @Excel(name = "创建用户id")
    private String fCreateuserid;

    /** 创建用户名称 */
    @Excel(name = "创建用户名称")
    private String fCreateusername;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCreatedate;

    /** 轨迹查询返回结果 */
    /** 业务类型 */
    private String type;


    public void setfTrackid(String fTrackid) 
    {
        this.fTrackid = fTrackid;
    }

    public String getfTrackid() 
    {
        return fTrackid;
    }
    public void setfCasecode(String fCasecode) 
    {
        this.fCasecode = fCasecode;
    }

    public String getfCasecode() 
    {
        return fCasecode;
    }
    public void setfBusinesstype(String fBusinesstype) 
    {
        this.fBusinesstype = fBusinesstype;
    }

    public String getfBusinesstype() 
    {
        return fBusinesstype;
    }
    public void setfBusinessid(String fBusinessid) 
    {
        this.fBusinessid = fBusinessid;
    }

    public String getfBusinessid() 
    {
        return fBusinessid;
    }
    public void setfBusinessdetailid(String fBusinessdetailid) 
    {
        this.fBusinessdetailid = fBusinessdetailid;
    }

    public String getfBusinessdetailid() 
    {
        return fBusinessdetailid;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fTrackid", getfTrackid())
            .append("fCasecode", getfCasecode())
            .append("fBusinesstype", getfBusinesstype())
            .append("fBusinessid", getfBusinessid())
            .append("fBusinessdetailid", getfBusinessdetailid())
            .append("fCreateuserid", getfCreateuserid())
            .append("fCreateusername", getfCreateusername())
            .append("fCreatedate", getfCreatedate())
            .toString();
    }
}
