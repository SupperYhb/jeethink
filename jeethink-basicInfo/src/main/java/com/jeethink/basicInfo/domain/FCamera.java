package com.jeethink.basicInfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 摄像头对象 f_camera
 * 
 * @author yhb
 * @date 2020-08-11
 */
public class FCamera extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 摄像头Id */
    private String fCameraid;

    /** 摄像头用户名 */
    @Excel(name = "摄像头用户名")
    private String fName;

    /** 摄像头Ip */
    @Excel(name = "摄像头Ip")
    private String fIp;

    /** 用户名 */
    @Excel(name = "用户名")
    private String fUsername;

    /** 密码 */
    @Excel(name = "密码")
    private String fPassword;

    /** 科达平台puid */
    @Excel(name = "科达平台puid")
    private String fPuid;
    /** 端口号 */
    private String fPort;

    /** 开始时间 */
    private String beginDate;

    /** 结束时间 */
    private String endDate;

    public void setfCameraid(String fCameraid) 
    {
        this.fCameraid = fCameraid;
    }

    public String getfCameraid() 
    {
        return fCameraid;
    }
    public void setfName(String fName) 
    {
        this.fName = fName;
    }

    public String getfName() 
    {
        return fName;
    }
    public void setfIp(String fIp) 
    {
        this.fIp = fIp;
    }

    public String getfIp() 
    {
        return fIp;
    }
    public void setfUsername(String fUsername) 
    {
        this.fUsername = fUsername;
    }

    public String getfUsername() 
    {
        return fUsername;
    }
    public void setfPassword(String fPassword) 
    {
        this.fPassword = fPassword;
    }

    public String getfPassword() 
    {
        return fPassword;
    }
    public void setfPuid(String fPuid) 
    {
        this.fPuid = fPuid;
    }

    public String getfPuid() 
    {
        return fPuid;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setfPort(String fPort) {
        this.fPort = fPort;
    }

    public String getfPort() {
        return fPort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fCameraid", getfCameraid())
            .append("fName", getfName())
            .append("fIp", getfIp())
            .append("fUsername", getfUsername())
            .append("fPassword", getfPassword())
            .append("fPuid", getfPuid())
            .toString();
    }
}
