package com.jeethink.common.extend;

public enum codeType {
    /**
     * 空闲
     * */
    Leisure(0,"KX"),
    /**
     * 填写存入
     * */
    In(1,"CR"),
    /**
     * 拉取存入
     * */
    LQIn(1,"LQ"),
    /**
     * 取出
     * */
    Out(2,"QC"),
    /**
     * 归还
     * */
    GiveBack(3,"GH"),

    /**
     * 盘点
     * */
    Check(10,"PD");

    private final int id;
    private final String name;

    private codeType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

