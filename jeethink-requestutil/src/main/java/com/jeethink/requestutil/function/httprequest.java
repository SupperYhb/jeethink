package com.jeethink.requestutil.function;

import com.jeethink.requestutil.entity.kdcaseentity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class httprequest {
    /**
     * 登录平台
     * */
    public static String login(){
        return "";
    }
    /**
     * 获取案卷信息
     * */
    public static List<kdcaseentity> getCase(){
        List<kdcaseentity> list=new ArrayList<kdcaseentity>();
        for (int i=0;i<30;i++)
        {
            kdcaseentity entity=new kdcaseentity();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            entity.setNo(i+"");
            entity.setAcceptanceTime(df.format(new Date()));
            entity.setBriefCase("简要案情"+i);
            entity.setCrimeAddress("地址");
            entity.setName("测试案件"+df.format(new Date()));
            entity.setPoliceCode("08321"+i);
            entity.setPoliceName("A"+i);
            entity.setSuspectCode("涉案人员编号");
            entity.setSuspectName("涉案人员名称:"+i);
            list.add(entity);
        }
        return list;
    }

}
