package com.jeethink.basicInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 卷宗柜对象 f_locker
 * 
 * @author yhb
 * @date 2020-07-21
 */
public class FLocker extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 卷宗柜Id */
    private String fLockerid;

    /** 卷宗柜名称 */
    @Excel(name = "卷宗柜名称")
    private String fLockername;

    /** 卷宗柜编号 */
    @Excel(name = "卷宗柜编号")
    private String fLockercode;

    /** 卷宗柜IP */
    @Excel(name = "卷宗柜IP")
    private String fLockerip;

    /** 备注 */
    @Excel(name = "备注")
    private String fRemark;

    /** 货位数量 */
    @Excel(name = "货位数量")
    private Integer fCount;
    private List<FPosition> fPositionList;

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
    public void setfLockercode(String fLockercode) 
    {
        this.fLockercode = fLockercode;
    }

    public String getfLockercode() 
    {
        return fLockercode;
    }
    public void setfLockerip(String fLockerip) 
    {
        this.fLockerip = fLockerip;
    }

    public String getfLockerip() 
    {
        return fLockerip;
    }
    public void setfRemark(String fRemark) 
    {
        this.fRemark = fRemark;
    }

    public String getfRemark() 
    {
        return fRemark;
    }
    public void setfCount(Integer fCount) 
    {
        this.fCount = fCount;
    }

    public Integer getfCount() 
    {
        return fCount;
    }

    public void setfPositionList(List<FPosition> fPositionList) {
        this.fPositionList = fPositionList;
    }

    public List<FPosition> getfPositionList() {
        return fPositionList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fLockerid", getfLockerid())
            .append("fLockername", getfLockername())
            .append("fLockercode", getfLockercode())
            .append("fLockerip", getfLockerip())
            .append("fRemark", getfRemark())
            .append("fCount", getfCount())
            .toString();
    }
}
