package com.jeethink.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 * 
 * @author jeethink
 */
@Component
@ConfigurationProperties(prefix = "jeethink")
public class Global
{
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;

    /** 实例演示开关 */
    private static boolean demoEnabled;

    /** 上传路径 */
    private static String profile;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    /** 案卷获取接口 */
    private static String caseUrl;

    /** 案卷柜接口 */
    private static String lockerUrl;

    /** 平台Ip */
    private static String cameraIp;
    /** 平台端口 */
    private static String cameraPort;
    /** 平台用户名 */
    private static String cameraUsername;
    /** 平台密码 */
    private static String cameraPassword;
    /** 摄像头puid */
    private static String puid;
    /** 是否有人脸 0否，1是 */
    private static String isFace;
    /** 天地伟业地址 */
    private static String tdwyUrl;
    /** 保管单位 */
    private static String bgdw;
    /** 保管地点 */
    private static String bgdd;
    /** 保管单位编号 */
    private static String bgdwbh;
    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        Global.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        Global.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        Global.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        Global.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        Global.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        Global.addressEnabled = addressEnabled;
    }

    public void setCaseUrl(String caseUrl) {
        Global.caseUrl = caseUrl;
    }

    public static String getCaseUrl() {
        return caseUrl;
    }

    public void setLockerUrl(String lockerUrl) {
        Global.lockerUrl = lockerUrl;
    }

    public static String getLockerUrl() {
        return lockerUrl;
    }

    public void setCameraIp(String cameraIp) {
        Global.cameraIp = cameraIp;
    }

    public static String getCameraIp() {
        return cameraIp;
    }

    public void setCameraPort(String cameraPort) {
        Global.cameraPort = cameraPort;
    }

    public static String getCameraPort() {
        return cameraPort;
    }

    public void setCameraUsername(String cameraUsername) {
        Global.cameraUsername = cameraUsername;
    }

    public static String getCameraUsername() {
        return cameraUsername;
    }

    public void setCameraPassword(String cameraPassword) {
        Global.cameraPassword = cameraPassword;
    }

    public static String getCameraPassword() {
        return cameraPassword;
    }

    public void setPuid(String puid) {
        Global.puid = puid;
    }

    public static String getPuid() {
        return puid;
    }

    public static String getIsFace() {
        return isFace;
    }

    public void setIsFace(String isFace) {
        Global.isFace = isFace;
    }

    public static String getTdwyUrl() {
        return tdwyUrl;
    }

    public void setTdwyUrl(String tdwyUrl) {
        Global.tdwyUrl = tdwyUrl;
    }

    public static String getBgdw() {
        return bgdw;
    }

    public void setBgdw(String bgdw) {
        Global.bgdw = bgdw;
    }

    public static String getBgdd() {
        return bgdd;
    }

    public void setBgdd(String bgdd) {
        Global.bgdd = bgdd;
    }

    public static String getBgdwbh() {
        return bgdwbh;
    }

    public void setBgdwbh(String bgdwbh) {
        Global.bgdwbh = bgdwbh;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
