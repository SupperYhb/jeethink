package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 案卷存入明细对象 f_depositdetail
 * 
 * @author yhb
 * @date 2020-07-24
 */
public class FDepositdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 存放案卷明细Id */
    private String fDepositdetailid;
    /**
     * 主表Id
     * */
    private String fDepositid;
    /** 案卷编号 */
    @Excel(name = "案卷编号")
    private String fCasecode;

    /** 卷宗柜Id */
    @Excel(name = "卷宗柜Id")
    private String fLockerid;

    /** 箱体Id */
    @Excel(name = "箱体Id")
    private String fBoxid;

    /** 货位Id */
    @Excel(name = "货位Id")
    private String fPositionid;

    /** 状态（0未存入，1已存入） */
    @Excel(name = "状态")
    private String state;
    /** 创建用户 */
    private  String createby;
    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdate;

    public void setfDepositdetailid(String fDepositdetailid) 
    {
        this.fDepositdetailid = fDepositdetailid;
    }

    public String getfDepositdetailid() 
    {
        return fDepositdetailid;
    }

    public void setfDepositid(String fDepositid) {
        this.fDepositid = fDepositid;
    }

    public String getfDepositid() {
        return fDepositid;
    }

    public void setfCasecode(String fCasecode)
    {
        this.fCasecode = fCasecode;
    }

    public String getfCasecode() 
    {
        return fCasecode;
    }
    public void setfLockerid(String fLockerid) 
    {
        this.fLockerid = fLockerid;
    }

    public String getfLockerid() 
    {
        return fLockerid;
    }
    public void setfBoxid(String fBoxid) 
    {
        this.fBoxid = fBoxid;
    }

    public String getfBoxid() 
    {
        return fBoxid;
    }
    public void setfPositionid(String fPositionid) 
    {
        this.fPositionid = fPositionid;
    }

    public String getfPositionid() 
    {
        return fPositionid;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreatedate(Date createdate)
    {
        this.createdate = createdate;
    }

    public Date getCreatedate() 
    {
        return createdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fDepositdetailid", getfDepositdetailid())
            .append("fCasecode", getfCasecode())
            .append("fLockerid", getfLockerid())
            .append("fBoxid", getfBoxid())
            .append("fPositionid", getfPositionid())
            .append("state", getState())
            .append("createdate", getCreatedate())
            .toString();
    }
}
