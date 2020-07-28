package com.jeethink.common.extend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class createId {
    /**
     * 生成Id(字符串)
     * */
    public static String getID(){
        return UUID.randomUUID().toString();
    }
    /**
     * 生成编号
     * */
    public static String getCode(codeType code){

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return code.getName()+df.format(new Date());
    }
}
