package com.jeethink.requestutil.entity.useface;

public class voucherLink {
    /**
     * 货位编号
     * */
    private String boxCode;
    /**
     * 货位Id
     * */
    private String boxUuid;
    /**
     * 柜子唯一标识
     * */
    private String lockerUuid;

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
    }

    public String getBoxUuid() {
        return boxUuid;
    }

    public void setBoxUuid(String boxUuid) {
        this.boxUuid = boxUuid;
    }

    public String getLockerUuid() {
        return lockerUuid;
    }

    public void setLockerUuid(String lockerUuid) {
        this.lockerUuid = lockerUuid;
    }
}
