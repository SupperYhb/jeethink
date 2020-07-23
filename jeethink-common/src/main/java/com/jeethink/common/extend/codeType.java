package com.jeethink.common.extend;

public enum codeType {
    /**
     * 空闲
     * */
    Leisure(0,"空闲"),
    /**
     * 存入
     * */
    In(1,"存入"),
    /**
     * 取出
     * */
    Out(2,"取出");

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

