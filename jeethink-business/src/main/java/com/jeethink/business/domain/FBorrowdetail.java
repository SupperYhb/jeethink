package com.jeethink.business.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 取出明细对象 f_borrowdetail
 * 
 * @author yhb
 * @date 2020-07-28
 */
public class FBorrowdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 取出明细Id */
    private String fBorrowdetailid;

    /** 取出主表Id */
    @Excel(name = "取出主表Id")
    private String fBorrowid;

    /** 案卷编号 */
    @Excel(name = "案卷编号")
    private String fCasecode;

    /** 状态（0未取出，1已取出） */
    @Excel(name = "状态", readConverterExp = "0=未取出，1已取出")
    private Integer fState;
    /** 创建人 */
    private String createby;
    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdate;

    public void setfBorrowdetailid(String fBorrowdetailid) 
    {
        this.fBorrowdetailid = fBorrowdetailid;
    }

    public String getfBorrowdetailid() 
    {
        return fBorrowdetailid;
    }
    public void setfBorrowid(String fBorrowid) 
    {
        this.fBorrowid = fBorrowid;
    }

    public String getfBorrowid() 
    {
        return fBorrowid;
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
            .append("fBorrowdetailid", getfBorrowdetailid())
            .append("fBorrowid", getfBorrowid())
            .append("fCasecode", getfCasecode())
            .append("fState", getfState())
            .append("createdate", getCreatedate())
            .toString();
    }
}
