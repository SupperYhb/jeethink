package com.jeethink.requestutil.function;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.MappingIterator;
import com.jeethink.common.utils.AddressUtils;
import com.jeethink.common.utils.http.HttpUtils;
import com.jeethink.requestutil.entity.casetotalentity;
import com.jeethink.requestutil.entity.kdcaseentity;
import com.jeethink.requestutil.entity.loginresult;


import java.util.ArrayList;
import java.util.List;
public class httprequest {
    /**
     * 柜子http路径
     * */
    private static String url="http://180.169.73.197:19210";
    /**
     * 案卷http路径
     * */
    private static String caseurl="http://180.169.73.197:19224";
    /**
     * 柜门打开
     * */
    private static String cupboardControl="/udms/device/cupboardControl1";

    /**
     * 刷卡柜门打开
     * */
    private static String bind="/udms/device/voucher/bind";

    /**
     * 刷卡柜门打开
     * */
    private static String cases="/dams/cases/page";
    /**
     * 登录平台
     * */
    public static String login()
    {
        String data=HttpUtils.sendPost(url+"/udms/login","userName=admin&password=admin123","","");
        if(!data.isEmpty()) {
            loginresult loginEntity = JSON.parseObject(data, loginresult.class);
            return loginEntity.getApiToken();
        }else{
            return "";
        }
    }
    /**
     * 获取案卷信息
     * */
    public static List<kdcaseentity> getCase(String ApiToken,String caseName,String caseNumber,String policeCode){
       String obj= HttpUtils.sendGet(caseurl+"/dams/casePolices/page","currentPage=0&pageSize=10&caseName="+caseName+"&caseNumber="+caseNumber+"&policeCode="+policeCode,"",ApiToken);
       if(!obj.isEmpty()) {
           casetotalentity entity = JSON.parseObject(obj, casetotalentity.class);
           int i = 0;
           for (kdcaseentity data : entity.getList()
           ) {
               data.setMainPoliceCode("084141");
               data.setMainPoliceName("尚洪涛");
               data.setAssistPoliceCode("09278" + i);
               data.setAssistPoliceName("白书辉");
               i++;
           }
           return entity.getList();
       }else{
           return new ArrayList<kdcaseentity>();
       }
    }



    /**
     * 立即开门
     * */
    public static String openBox(String uuid,String boxNum,String ApiToken)
    {
       String result= HttpUtils.sendPost(url+cupboardControl,"uuid="+uuid+"&boxNum="+boxNum,"", ApiToken);
        return result;
    }
    /**
     * 刷卡打开
     * */
    public static String openBoxByCard(String cardCode,String boxCode,String uuid,String userName,String ApiToken)
    {
        String result= HttpUtils.sendPost(url+bind,"cardCode="+cardCode+"&boxCode="+boxCode+"&lockerUuid="+uuid+"&useName="+userName,"", ApiToken);
        return result;
    }


}

