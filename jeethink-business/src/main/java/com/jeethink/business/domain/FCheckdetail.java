package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 盘点明细对象 f_checkdetail
 * 
 * @author yhb
 * @date 2020-08-07
 */
public class FCheckdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String fCheckdetailid;

    /** 盘点主表Id */
    @Excel(name = "盘点主表Id")
    private String fCheckid;

    /** 案卷编号 */
    @Excel(name = "案卷编号")
    private String fCasecode;

    /** 状态（0正常，1损坏） */
    @Excel(name = "状态", readConverterExp = "0=正常，1损坏,2丢失")
    private Integer fState;

    /** 备注 */
    @Excel(name = "备注")
    private String fRemark;

    /** 盘点时间 */
    @Excel(name = "盘点时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fCheckdate;

    public void setfCheckdetailid(String fCheckdetailid) 
    {
        this.fCheckdetailid = fCheckdetailid;
    }

    public String getfCheckdetailid() 
    {
        return fCheckdetailid;
    }
    public void setfCheckid(String fCheckid) 
    {
        this.fCheckid = fCheckid;
    }

    public String getfCheckid() 
    {
        return fCheckid;
    }
    public void setfCasecode(String fCasecode) 
    {
        this.fCasecode = fCasecode;
    }

    public String getfCasecode() 
    {
        return fCasecode;
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
    public void setfCheckdate(Date fCheckdate) 
    {
        this.fCheckdate = fCheckdate;
    }

    public Date getfCheckdate() 
    {
        return fCheckdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fCheckdetailid", getfCheckdetailid())
            .append("fCheckid", getfCheckid())
            .append("fCasecode", getfCasecode())
            .append("fState", getfState())
            .append("fRemark", getfRemark())
            .append("fCheckdate", getfCheckdate())
            .toString();
    }
}
