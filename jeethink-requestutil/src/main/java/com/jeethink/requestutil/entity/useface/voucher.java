package com.jeethink.requestutil.entity.useface;

import java.util.List;

public class voucher {
    /**
     * 人员编号
     * */
    private String code;

    /**
     * 凭证类型(0-卡，1-人脸，2-指纹，3-二维码/条形码)
     * */
    private String voucherType;
    /**
     * 用户名称
     * */
    private String useName;
    /**
     * 人脸base64
     * */
    private String faceImage;

    private List<voucherLink> voucherLinkList;
    /**
     * 凭证类型(0-管理员，1-普通)
     * */
    private String type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public List<voucherLink> getVoucherLinkList() {
        return voucherLinkList;
    }

    public void setVoucherLinkList(List<voucherLink> voucherLinkList) {
        this.voucherLinkList = voucherLinkList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
