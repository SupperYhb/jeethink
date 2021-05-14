package com.jeethink.requestutil.function;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.MappingIterator;
import com.jeethink.common.config.Global;
import com.jeethink.common.utils.AddressUtils;
import com.jeethink.common.utils.http.HttpUtils;
import com.jeethink.requestutil.entity.casetotalentity;
import com.jeethink.requestutil.entity.kdcaseentity;
import com.jeethink.requestutil.entity.loginresult;
import com.jeethink.requestutil.entity.tdwyCase;
import com.jeethink.requestutil.entity.useface.faceParam;
import com.jeethink.requestutil.entity.useface.verificationFace;
import com.jeethink.requestutil.entity.useface.voucher;
import com.jeethink.requestutil.entity.useface.voucherLink;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private static String cupboardControl="/udms/device/cupboardControl";

    /**
     * 刷卡柜门打开
     * */
    private static String bind="/udms/device/voucher/bind";

    /**
     * 刷卡柜门打开
     * */
    private static String cases="/dams/cases/page";

    /**
     * 删除绑定
     * */
    private static String deleteBind="/udms/device/voucher";

    /**
     * 人脸路径
     * */
    private static String facePath="/udms/device/voucher/general";

    /**
     * 人脸核验
     * */
    private static String versionFace="/udms/device/faceVoucher/check";
    /**
     * 登录平台
     * */
    public static String login()
    {
        String data=HttpUtils.sendPost(Global.getLockerUrl()+"/udms/login","userName=admin&password=admin123","","");
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
        String url=Global.getCaseUrl();
       String obj= HttpUtils.sendGet(Global.getCaseUrl()+"/dams/casePolices/page","currentPage=0&pageSize=10&caseName="+caseName+"&caseNumber="+caseNumber+"&policeCode="+policeCode,"",ApiToken);
       if(!obj.isEmpty()) {
           casetotalentity entity = JSON.parseObject(obj, casetotalentity.class);
           return entity.getList();
       }else{
           return new ArrayList<kdcaseentity>();
       }
//        List<kdcaseentity> list= new ArrayList<kdcaseentity>();
//        kdcaseentity data=new kdcaseentity();
//        data.setNo("1");0
//        data.setName("测试");
//        data.setAssistPoliceCode("092451");
//        data.setAssistPoliceName("测试");
//        list.add(data);
//        return list;
    }



    /**
     * 立即开门
     * */
    public static String openBox(String uuid,String boxNum,String ApiToken)
    {
       String result= HttpUtils.sendPost(Global.getLockerUrl()+cupboardControl,"uuid="+uuid+"&boxNum="+boxNum,"", ApiToken);
        return result;
    }
    /**
     * 刷卡打开
     * */
    public static String openBoxByCard(String cardCode,String boxCode,String uuid,String userName,String ApiToken)
    {
        String result= HttpUtils.sendPost(Global.getLockerUrl()+bind,"cardCode="+cardCode+"&boxCode="+boxCode+"&lockerUuid="+uuid+"&useName="+userName,"", ApiToken);
        return result;
    }

    /**
     * 删除绑定
     * */
    public static String deleteBind(String cardCode,String lockerCode,String positionCode,String ApiToken){
    String Result=HttpUtils.sendDeletes(Global.getLockerUrl()+deleteBind+"/"+cardCode+"/"+lockerCode+"/"+positionCode,ApiToken);
    return Result;
    }

    /**
     * 人脸验证
     * */
    public static String verificationFace(String uuId,String faceImg,String ApiToken)
    {
        faceImg=faceImg.split(",")[1];
        verificationFace  verificationFace=new verificationFace();
        verificationFace.setFaceImageBase64(faceImg);
        verificationFace.setLokerUuid(uuId);
        String json = JSONObject.toJSONString(verificationFace);
        String result=HttpUtils.sendPosts(Global.getLockerUrl()+versionFace,json,ApiToken,2);
        return result;
    }
    /**
     * 刷脸开门
     * */
    public static String openBoxByFace(String account,String useName,String faceImag,String boxCode,String lockerCode,String ApiToken)
    {
        faceImag=faceImag.split(",")[1];
        voucherLink voucherLink=new voucherLink();
        voucherLink.setBoxCode(boxCode);
        voucherLink.setBoxUuid("");
        voucherLink.setLockerUuid("");
        voucher voucher=new voucher();
        voucher.setCode(account);
        voucher.setVoucherType("1");
        voucher.setUseName(useName);
        voucher.setFaceImage(faceImag);
        voucher.setType("1");
        List<voucherLink> list=new ArrayList<>();
        list.add(voucherLink);
        voucher.setVoucherLinkList(list);
        faceParam faceParam=new faceParam();
        faceParam.setUuid(lockerCode);
        faceParam.setVoucherDTO(voucher);
        String json = JSONObject.toJSONString(faceParam);
        String result=HttpUtils.sendPosts(Global.getLockerUrl()+facePath,json,ApiToken,2);
        return result;
    }

    public static tdwyCase sendCase(Integer ajzt,String ajbh,String bgr,String lrr,Integer zgzt)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String badd=Global.getBgdd();
        String badw=Global.getBgdw();
        String bgdwbh=Global.getBgdwbh();
        tdwyCase tdwyCase=new tdwyCase();
        tdwyCase.setAjbazt(ajzt);
        tdwyCase.setAjbh(ajbh);
        tdwyCase.setBgdd(badd);
        tdwyCase.setBgdw(badw);
        tdwyCase.setBgr(bgr);
        tdwyCase.setLrr(lrr);
        tdwyCase.setZgzt(zgzt);
        tdwyCase.setLybid(ajbh);
        tdwyCase.setBgdwbh(bgdwbh);
        tdwyCase.setLrsj(df.format(new Date()));
        return tdwyCase;
    }
    /**
     * 发送案卷信息
     * */
    public static String sendtdwy(List<tdwyCase> list){
        String json=JSONObject.toJSONString(list);
        String result= null;
        try {
            result = HttpUtils.jsonPost(Global.getTdwyUrl()+"/pangu/checkToken/zfjdArchiveFile/saveBatch",json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 更新案卷状态
     * */
    public static String upDatetdwy(tdwyCase tdwyCase){
        String json=JSONObject.toJSONString(tdwyCase);
        String result= null;
        try {
            result = HttpUtils.jsonPost(Global.getTdwyUrl()+"/pangu/checkToken/zfjdArchiveFile/update",json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}

