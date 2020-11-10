package com.jeethink.basicInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 箱体对象 f_box
 * 
 * @author yhb
 * @date 2020-08-31
 */
public class FBox extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 箱体主键 */
    private String fBoxid;

    /** 箱体编号 */
    @Excel(name = "箱体编号")
    private String fBoxcode;

    /** 卷宗柜Id */
    @Excel(name = "卷宗柜Id")
    private String fLockerid;

    /** 箱体数量 */
    @Excel(name = "箱体数量")
    private Integer fCount;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer fSort;

    public void setfBoxid(String fBoxid) 
    {
        this.fBoxid = fBoxid;
    }

    public String getfBoxid() 
    {
        return fBoxid;
    }
    public void setfBoxcode(String fBoxcode) 
    {
        this.fBoxcode = fBoxcode;
    }

    public String getfBoxcode() 
    {
        return fBoxcode;
    }
    public void setfLockerid(String fLockerid) 
    {
        this.fLockerid = fLockerid;
    }

    public String getfLockerid() 
    {
        return fLockerid;
    }
    public void setfCount(Integer fCount) 
    {
        this.fCount = fCount;
    }

    public Integer getfCount() 
    {
        return fCount;
    }
    public void setfSort(Integer fSort) 
    {
        this.fSort = fSort;
    }

    public Integer getfSort() 
    {
        return fSort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fBoxid", getfBoxid())
            .append("fBoxcode", getfBoxcode())
            .append("fLockerid", getfLockerid())
            .append("fCount", getfCount())
            .append("fSort", getfSort())
            .toString();
    }
}
