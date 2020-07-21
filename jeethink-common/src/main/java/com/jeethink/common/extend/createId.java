package com.jeethink.common.extend;

import java.util.UUID;

public class createId {
    /**
     * 生成Id(字符串)
     * */
    public static String getID(){
        return UUID.randomUUID().toString();
    }
}
