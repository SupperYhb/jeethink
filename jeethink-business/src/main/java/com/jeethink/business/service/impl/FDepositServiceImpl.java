package com.jeethink.business.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jeethink.basicInfo.domain.FCamera;
import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.business.domain.*;
import com.jeethink.business.mapper.FCasesMapper;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.business.service.IFDepositdetailService;
import com.jeethink.common.annotation.Log;
import com.jeethink.common.config.Global;
import com.jeethink.common.extend.codeType;
import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.requestutil.entity.kdcaseentity;
import com.jeethink.requestutil.entity.tdwyCase;
import com.jeethink.requestutil.function.httprequest;
import com.jeethink.system.domain.SysUser;
import com.jeethink.system.domain.SysUserRole;
import com.jeethink.system.mapper.SysUserMapper;
import com.jeethink.system.mapper.SysUserRoleMapper;
import com.jeethink.system.service.ISysUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FDepositMapper;
import com.jeethink.business.service.IFDepositService;
import com.jeethink.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 存放案卷Service业务层处理
 * 
 * @author yhb
 * @date 2020-07-22
 */
@Service
public class FDepositServiceImpl implements IFDepositService 
{
    @Autowired
    private FDepositMapper fDepositMapper;
    @Autowired
    private FCasesMapper fCasesMapper;
    @Autowired
    private IFLockerService fLockerService;
    @Autowired
    private IFPositionService fPositionService;
    @Autowired
    private IFCardService fCardService;
    @Autowired
    private SysUserMapper sysUserService;
    @Autowired
    private IFDepositdetailService ifDepositdetailService;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private FTrackServiceImpl fTrackService;

    /**
     * 查询存放案卷
     * 
     * @param fDepositid 存放案卷ID
     * @return 存放案卷
     */
    @Override
    public FDeposit selectFDepositById(String fDepositid)
    {
        return fDepositMapper.selectFDepositById(fDepositid);
    }

    /**
     * 查询存放案卷列表
     * 
     * @param fDeposit 存放案卷
     * @return 存放案卷
     */
    @Override
    public List<FDeposit> selectFDepositList(FDeposit fDeposit)
    {

        fDeposit.setfUserid(ShiroUtils.getLoginName());
        return fDepositMapper.selectFDepositList(fDeposit);
    }

    @Override
    public List<kdcaseentity> getkdCase(String caseName,String caseNumber,String policeCode) {
        if("1".equals(ShiroUtils.getType())){
            policeCode=ShiroUtils.getLoginName();
        }
//        else{
//            if(policeCode.isEmpty()){
//                policeCode="1";
//            }
//        }
//       String apiTolen= httprequest.login();
       List<kdcaseentity> list= httprequest.getCase("",caseName,caseNumber,policeCode);
       //String openboxResult=httprequest.openBox("1000001478595110","1",apiTolen);
       List<FCases> casesList =fCasesMapper.selectFCasesList(null);
       List<kdcaseentity> resultList=list.stream().filter(kdcaseentity->casesList.stream().noneMatch(cases->cases.getfCasecode().equals(kdcaseentity.getNo()))).collect(Collectors.toList());
        return resultList;
    }

    /**
     * 新增案卷入库（平台拉取）
     * */
    @Override
    @Transactional
    public String addCaseIn(List<TdwyCase> list, String lockerId, String positionId, String cardCode, String cardId,
                            String remark,String peopleType,String policeAccount,String policeName,
                            String openDoorType,String PolicePic) {

        //获取卷宗柜
        FLocker locker=fLockerService.selectFLockerById(lockerId);
        //获取货位
        FPosition position=fPositionService.selectFPositionById(positionId);
        //天地伟业集合
        List<tdwyCase> tdwylist=new ArrayList<>();
        //添加入库主表
        FDeposit fDeposit=new FDeposit();
        fDeposit.setfDepositid(createId.getID());
        fDeposit.setfCode(createId.getCode(codeType.LQIn));
        fDeposit.setfCreatedate(new Date());
        fDeposit.setfUserid(ShiroUtils.getLoginName());
        fDeposit.setfUsername(ShiroUtils.getSysUser().getUserName());
        fDeposit.setfState(1);
        fDeposit.setfBusinesstype("1");
        fDeposit.setfPeopletype(peopleType);
        fDeposit.setfCarduserid(policeAccount);
        fDeposit.setfCardusername(policeName);
        for (TdwyCase entity:list
             ) {
            //添加案卷
            FCases cases=new FCases();
            cases.setfId(createId.getID());
            cases.setfCreatedate(new Date());
            cases.setfState(1);
            cases.setfLockerid(lockerId);
            cases.setfPositionid(positionId);
            cases.setfLockername(locker.getfLockername());
            cases.setfPositioncode(position.getfPositioncode());
            cases.setfCasecode(entity.getAjbh());
            cases.setfCasename(entity.getAjmc());
            cases.setfCasebriefdetail(entity.getAqms());
            cases.setfPutdate("");
            cases.setfPolice1id(entity.getCbrjh());
            cases.setfPolice1name(entity.getCbrmx());
            cases.setfPolice2id(entity.getXbrjh());
            cases.setfPolice2name(entity.getXbrxm());
            cases.setfCriminalcode("");
            cases.setfCriminalname("");
            cases.setfCrimeaddress("");
            cases.setfState(1);
            fCasesMapper.insertFCases(cases);
            //添加入库明细
            FDepositdetail detail=new FDepositdetail();
            detail.setfDepositid(fDeposit.getfDepositid());
            detail.setfDepositdetailid(createId.getID());
            detail.setCreateby(ShiroUtils.getUserId().toString());
            detail.setfLockerid(lockerId);
            detail.setfPositionid(positionId);
            detail.setfCasecode(cases.getfCasecode());
            detail.setState("1");
            ifDepositdetailService.insertFDepositdetail(detail);
            //添加主办人员
            //验证民警信息
            SysUser Sysuser=new SysUser();
            Sysuser.setLoginName(policeAccount);
            Sysuser.setUserName(policeName);
            List<SysUser> userList= sysUserService.selectUserList(Sysuser);
            SysUser user=new SysUser();
            //判断民警是否存在，存在赋值卡信息
            if(userList.size()>0&&!cardId.isEmpty()&&"0".equals(peopleType)&&userList.size()==1)
            {
                user=userList.get(0);
                user.setCardid(cardId);
                user.setCardcode(cardCode);
                if(!PolicePic.isEmpty()){
                    PolicePic = new String(Base64.getDecoder().decode(PolicePic));
                    try {
                        PolicePic = URLDecoder.decode(PolicePic, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    user.setPic(PolicePic);
                }
                sysUserService.updateUser(user);
            }else if(userList.size()==0){
                String source="1";
                if(!cardId.isEmpty()&&"0".equals(peopleType)) {
                    user.setCardid(cardId);
                    user.setCardcode(cardCode);
                }
                user.setLoginName(policeAccount);
                user.setUserName(policeName);
                user.setSalt(ShiroUtils.randomSalt());
                user.setUserType("1");
                user.setDeptId((long)110);
                String password= new Md5Hash(policeAccount+"123456"+user.getSalt()).toHex();
                user.setPassword(password);
                user.setCreateBy(ShiroUtils.getLoginName());
                user.setSource(source);
                user.setCreateBy(source=="0"?"填写":"平台拉取");
                if(!PolicePic.isEmpty()){
                    PolicePic = new String(Base64.getDecoder().decode(PolicePic));
                    try {
                        PolicePic = URLDecoder.decode(PolicePic, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    user.setPic(PolicePic);
                }
                sysUserService.insertUser(user);
                //设置用户角色
                List<SysUserRole> userRolesList=new ArrayList<>();
                SysUserRole userRole=new SysUserRole();
                userRole.setRoleId((long)3);
                userRole.setUserId(user.getUserId());
                userRolesList.add(userRole);
                userRoleMapper.batchUserRole(userRolesList);
            }else if(!PolicePic.isEmpty()&&userList.size()>0)
            {
                user=userList.get(0);
                PolicePic = new String(Base64.getDecoder().decode(PolicePic));
                try {
                    PolicePic = URLDecoder.decode(PolicePic, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                user.setPic(PolicePic);
                sysUserService.updateUser(user);
            }
            //判断辅办民警是不是空
//            if(entity.getAssistPoliceCode()!=null){
//                //添加主办人员
//                //验证民警信息
//                SysUser Sysuser1=new SysUser();
//                Sysuser1.setLoginName(entity.getAssistPoliceCode());
//                Sysuser1.setUserName(entity.getAssistPoliceName());
//                List<SysUser> userList1= sysUserService.selectUserList(Sysuser1);
//                SysUser user1=new SysUser();
//                //判断民警是否存在，存在赋值卡信息
//                if(userList1.size()>0&&!cardId.isEmpty()&&"0".equals(peopleType))
//                {
//                    user1=userList1.get(0);
//                    user1.setCardid(cardId);
//                    user1.setCardcode(cardCode);
//                    sysUserService.updateUser(user1);
//                }else if(userList1.size()==1){
//                    String source="1";
//                    if(!cardId.isEmpty()&&"1".equals(peopleType)) {
//                        user1.setCardid(cardId);
//                        user1.setCardcode(cardCode);
//                    }
//                    user1.setLoginName(entity.getAssistPoliceCode());
//                    user1.setUserName(entity.getAssistPoliceName());
//                    user1.setSalt(ShiroUtils.randomSalt());
//                    user1.setUserType("1");
//                    user1.setDeptId((long)110);
//                    String password= new Md5Hash(entity.getAssistPoliceCode()+"123456"+user1.getSalt()).toHex();
//                    user1.setPassword(password);
//                    user1.setCreateBy(ShiroUtils.getLoginName());
//                    user1.setSource(source);
//                    user1.setCreateBy(source=="0"?"填写":"平台拉取");
//                    sysUserService.insertUser(user1);
//                    //设置用户角色
//                    List<SysUserRole> userRolesList1=new ArrayList<>();
//                    SysUserRole userRole1=new SysUserRole();
//                    userRole1.setRoleId((long)3);
//                    userRole1.setUserId(user1.getUserId());
//                    userRolesList1.add(userRole1);
//                    userRoleMapper.batchUserRole(userRolesList1);
//                }
//            }
            //添加轨迹
            FTrack fTrack=new FTrack();
            fTrack.setfTrackid(createId.getID());
            fTrack.setfCasecode(detail.getfCasecode());
            fTrack.setfBusinesstype(codeType.LQIn.getName());
            fTrack.setfBusinessid(fDeposit.getfDepositid());
            fTrack.setfBusinessdetailid(detail.getfDepositdetailid());
            fTrack.setfCreateuserid(ShiroUtils.getLoginName());
            fTrack.setfCreateusername(ShiroUtils.getSysUser().getUserName());
            fTrack.setfCreatedate(new Date());
            fTrackService.insertFTrack(fTrack);

            tdwyCase tdwyCase=httprequest.sendCase(1,entity.getAjbh(),ShiroUtils.getLoginName(),ShiroUtils.getLoginName(),1);
            tdwylist.add(tdwyCase);
        }
        String userName=policeName;
        //修改卡
        if(openDoorType.equals("1"))
        {
            fDeposit.setfType(1);
            fDeposit.setfCardcode(cardCode);
            FCard card=new FCard();
            card.setfCardid(cardId);
            card.setfUserid(policeAccount);
            card.setfUsername(policeName);
            card.setfState("1");
            card.setfLockercode(locker.getfLockercode());
            card.setfPositioncode(position.getfPositioncode());
            fCardService.updateFCard(card,"");
        }
        fDeposit.setfType(Integer.parseInt(openDoorType));
        //发送命令
        String apiToken= httprequest.login();
        String result="";
        //直接打开
        if(openDoorType.indexOf("0")!=-1) {
            result= httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
        }
        //刷卡打开
        else if(openDoorType.indexOf("1")!=-1){
            result=httprequest.openBoxByCard(cardCode,position.getfPositioncode(),locker.getfLockercode(),userName,apiToken);
        }
        //刷脸打开
        else if(openDoorType.indexOf("2")!=-1){
            result=httprequest.openBoxByFace(policeAccount,policeName,PolicePic,position.getfPositioncode(),locker.getfLockercode(),apiToken);
        }

        if(result.indexOf("成功")==-1) {
            fDeposit.setfState(0);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fDeposit.setfOpendate( sdf.format(new Date()));
            Date now = new Date();
            Date afterDate = new Date(now.getTime() + 300000);
            fDeposit.setfEnddate(sdf.format(afterDate));
        }
        //保存入库主表信息
        fDepositMapper.insertFDeposit(fDeposit);
        String results=httprequest.sendtdwy(tdwylist);
        System.out.println(results);
        if(result.indexOf("成功")!=-1)
        {
            return "";
        }else{
            return "发送命令失败";
        }
    }

//    public String addCaseIn(List<kdcaseentity> list, String lockerId, String positionId, String cardCode, String cardId,
//                            String remark,String peopleType,String policeAccount,String policeName,
//                            String openDoorType,String PolicePic) {
//
//        //获取卷宗柜
//        FLocker locker=fLockerService.selectFLockerById(lockerId);
//        //获取货位
//        FPosition position=fPositionService.selectFPositionById(positionId);
//        //天地伟业集合
//        List<tdwyCase> tdwylist=new ArrayList<>();
//        //添加入库主表
//        FDeposit fDeposit=new FDeposit();
//        fDeposit.setfDepositid(createId.getID());
//        fDeposit.setfCode(createId.getCode(codeType.LQIn));
//        fDeposit.setfCreatedate(new Date());
//        fDeposit.setfUserid(ShiroUtils.getLoginName());
//        fDeposit.setfUsername(ShiroUtils.getSysUser().getUserName());
//        fDeposit.setfState(1);
//        fDeposit.setfBusinesstype("1");
//        fDeposit.setfPeopletype(peopleType);
//        fDeposit.setfCarduserid(policeAccount);
//        fDeposit.setfCardusername(policeName);
//        for (kdcaseentity entity:list
//        ) {
//            //添加案卷
//            FCases cases=new FCases();
//            cases.setfId(createId.getID());
//            cases.setfCreatedate(new Date());
//            cases.setfState(1);
//            cases.setfLockerid(lockerId);
//            cases.setfPositionid(positionId);
//            cases.setfLockername(locker.getfLockername());
//            cases.setfPositioncode(position.getfPositioncode());
//            cases.setfCasecode(entity.getNo());
//            cases.setfCasename(entity.getName());
//            cases.setfCasebriefdetail(entity.getBriefCase());
//            cases.setfPutdate(entity.getAcceptanceTime());
//            cases.setfPolice1id(entity.getMainPoliceCode());
//            cases.setfPolice1name(entity.getMainPoliceName());
//            cases.setfPolice2id(entity.getAssistPoliceCode());
//            cases.setfPolice2name(entity.getAssistPoliceName());
//            cases.setfCriminalcode(entity.getSuspectCode());
//            cases.setfCriminalname(entity.getSuspectName());
//            cases.setfCrimeaddress(entity.getCrimeAddress());
//            cases.setfState(1);
//            fCasesMapper.insertFCases(cases);
//            //添加入库明细
//            FDepositdetail detail=new FDepositdetail();
//            detail.setfDepositid(fDeposit.getfDepositid());
//            detail.setfDepositdetailid(createId.getID());
//            detail.setCreateby(ShiroUtils.getUserId().toString());
//            detail.setfLockerid(lockerId);
//            detail.setfPositionid(positionId);
//            detail.setfCasecode(cases.getfCasecode());
//            detail.setState("1");
//            ifDepositdetailService.insertFDepositdetail(detail);
//            //添加主办人员
//            //验证民警信息
//            SysUser Sysuser=new SysUser();
//            Sysuser.setLoginName(policeAccount);
//            Sysuser.setUserName(policeName);
//            List<SysUser> userList= sysUserService.selectUserList(Sysuser);
//            SysUser user=new SysUser();
//            //判断民警是否存在，存在赋值卡信息
//            if(userList.size()>0&&!cardId.isEmpty()&&"0".equals(peopleType)&&userList.size()==1)
//            {
//                user=userList.get(0);
//                user.setCardid(cardId);
//                user.setCardcode(cardCode);
//                if(!PolicePic.isEmpty()){
//                    PolicePic = new String(Base64.getDecoder().decode(PolicePic));
//                    try {
//                        PolicePic = URLDecoder.decode(PolicePic, "UTF-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    user.setPic(PolicePic);
//                }
//                sysUserService.updateUser(user);
//            }else if(userList.size()==0){
//                String source="1";
//                if(!cardId.isEmpty()&&"0".equals(peopleType)) {
//                    user.setCardid(cardId);
//                    user.setCardcode(cardCode);
//                }
//                user.setLoginName(policeAccount);
//                user.setUserName(policeName);
//                user.setSalt(ShiroUtils.randomSalt());
//                user.setUserType("1");
//                user.setDeptId((long)110);
//                String password= new Md5Hash(policeAccount+"123456"+user.getSalt()).toHex();
//                user.setPassword(password);
//                user.setCreateBy(ShiroUtils.getLoginName());
//                user.setSource(source);
//                user.setCreateBy(source=="0"?"填写":"平台拉取");
//                if(!PolicePic.isEmpty()){
//                    PolicePic = new String(Base64.getDecoder().decode(PolicePic));
//                    try {
//                        PolicePic = URLDecoder.decode(PolicePic, "UTF-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    user.setPic(PolicePic);
//                }
//                sysUserService.insertUser(user);
//                //设置用户角色
//                List<SysUserRole> userRolesList=new ArrayList<>();
//                SysUserRole userRole=new SysUserRole();
//                userRole.setRoleId((long)3);
//                userRole.setUserId(user.getUserId());
//                userRolesList.add(userRole);
//                userRoleMapper.batchUserRole(userRolesList);
//            }else if(!PolicePic.isEmpty()&&userList.size()>0)
//            {
//                user=userList.get(0);
//                PolicePic = new String(Base64.getDecoder().decode(PolicePic));
//                try {
//                    PolicePic = URLDecoder.decode(PolicePic, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                user.setPic(PolicePic);
//                sysUserService.updateUser(user);
//            }
//            //判断辅办民警是不是空
////            if(entity.getAssistPoliceCode()!=null){
////                //添加主办人员
////                //验证民警信息
////                SysUser Sysuser1=new SysUser();
////                Sysuser1.setLoginName(entity.getAssistPoliceCode());
////                Sysuser1.setUserName(entity.getAssistPoliceName());
////                List<SysUser> userList1= sysUserService.selectUserList(Sysuser1);
////                SysUser user1=new SysUser();
////                //判断民警是否存在，存在赋值卡信息
////                if(userList1.size()>0&&!cardId.isEmpty()&&"0".equals(peopleType))
////                {
////                    user1=userList1.get(0);
////                    user1.setCardid(cardId);
////                    user1.setCardcode(cardCode);
////                    sysUserService.updateUser(user1);
////                }else if(userList1.size()==1){
////                    String source="1";
////                    if(!cardId.isEmpty()&&"1".equals(peopleType)) {
////                        user1.setCardid(cardId);
////                        user1.setCardcode(cardCode);
////                    }
////                    user1.setLoginName(entity.getAssistPoliceCode());
////                    user1.setUserName(entity.getAssistPoliceName());
////                    user1.setSalt(ShiroUtils.randomSalt());
////                    user1.setUserType("1");
////                    user1.setDeptId((long)110);
////                    String password= new Md5Hash(entity.getAssistPoliceCode()+"123456"+user1.getSalt()).toHex();
////                    user1.setPassword(password);
////                    user1.setCreateBy(ShiroUtils.getLoginName());
////                    user1.setSource(source);
////                    user1.setCreateBy(source=="0"?"填写":"平台拉取");
////                    sysUserService.insertUser(user1);
////                    //设置用户角色
////                    List<SysUserRole> userRolesList1=new ArrayList<>();
////                    SysUserRole userRole1=new SysUserRole();
////                    userRole1.setRoleId((long)3);
////                    userRole1.setUserId(user1.getUserId());
////                    userRolesList1.add(userRole1);
////                    userRoleMapper.batchUserRole(userRolesList1);
////                }
////            }
//            //添加轨迹
//            FTrack fTrack=new FTrack();
//            fTrack.setfTrackid(createId.getID());
//            fTrack.setfCasecode(detail.getfCasecode());
//            fTrack.setfBusinesstype(codeType.LQIn.getName());
//            fTrack.setfBusinessid(fDeposit.getfDepositid());
//            fTrack.setfBusinessdetailid(detail.getfDepositdetailid());
//            fTrack.setfCreateuserid(ShiroUtils.getLoginName());
//            fTrack.setfCreateusername(ShiroUtils.getSysUser().getUserName());
//            fTrack.setfCreatedate(new Date());
//            fTrackService.insertFTrack(fTrack);
//
//            tdwyCase tdwyCase=httprequest.sendCase(1,entity.getNo(),ShiroUtils.getLoginName(),ShiroUtils.getLoginName(),1);
//            tdwylist.add(tdwyCase);
//        }
//        String userName=policeName;
//        //修改卡
//        if(openDoorType.equals("1"))
//        {
//            fDeposit.setfType(1);
//            fDeposit.setfCardcode(cardCode);
//            FCard card=new FCard();
//            card.setfCardid(cardId);
//            card.setfUserid(policeAccount);
//            card.setfUsername(policeName);
//            card.setfState("1");
//            card.setfLockercode(locker.getfLockercode());
//            card.setfPositioncode(position.getfPositioncode());
//            fCardService.updateFCard(card,"");
//        }
//        fDeposit.setfType(Integer.parseInt(openDoorType));
//        //发送命令
//        String apiToken= httprequest.login();
//        String result="";
//        //直接打开
//        if(openDoorType.indexOf("0")!=-1) {
//            result= httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
//        }
//        //刷卡打开
//        else if(openDoorType.indexOf("1")!=-1){
//            result=httprequest.openBoxByCard(cardCode,position.getfPositioncode(),locker.getfLockercode(),userName,apiToken);
//        }
//        //刷脸打开
//        else if(openDoorType.indexOf("2")!=-1){
//            result=httprequest.openBoxByFace(policeAccount,policeName,PolicePic,position.getfPositioncode(),locker.getfLockercode(),apiToken);
//        }
//
//        if(result.indexOf("成功")==-1) {
//            fDeposit.setfState(0);
//        }else{
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            fDeposit.setfOpendate( sdf.format(new Date()));
//            Date now = new Date();
//            Date afterDate = new Date(now.getTime() + 300000);
//            fDeposit.setfEnddate(sdf.format(afterDate));
//        }
//        //保存入库主表信息
//        fDepositMapper.insertFDeposit(fDeposit);
//        String results=httprequest.sendtdwy(tdwylist);
//        System.out.println(results);
//        if(result.indexOf("成功")!=-1)
//        {
//            return "";
//        }else{
//            return "发送命令失败";
//        }
//    }

    @Override
    @Transactional
    public String addCaseReturn(List<FCases> list, String lockerId, String positionId, String cardCode, String cardId, String remark
            ,String peopleType,String policeAccount,String policeName,
                                String openDoorType,String PolicePic) {

        //获取卷宗柜
        FLocker locker=fLockerService.selectFLockerById(lockerId);
        //获取货位
        FPosition position=fPositionService.selectFPositionById(positionId);
        //添加入库主表
        FDeposit fDeposit=new FDeposit();
        fDeposit.setfDepositid(createId.getID());
        fDeposit.setfCode(createId.getCode(codeType.GiveBack));
        fDeposit.setfCreatedate(new Date());
        fDeposit.setfUserid(ShiroUtils.getLoginName());
        fDeposit.setfUsername(ShiroUtils.getSysUser().getUserName());
        fDeposit.setfState(1);
        fDeposit.setfBusinesstype("2");
        fDeposit.setfPeopletype(peopleType);
        for (FCases entity:list
        ) {
            //添加案卷
            FCases cases=entity;
            cases.setfCreatedate(new Date());
            cases.setfState(1);
            cases.setfLockerid(lockerId);
            cases.setfPositionid(positionId);
            cases.setfLockername(locker.getfLockername());
            cases.setfPositioncode(position.getfPositioncode());
            fCasesMapper.updateFCases(cases);
            //添加入库明细
            FDepositdetail detail=new FDepositdetail();
            detail.setfDepositid(fDeposit.getfDepositid());
            detail.setfDepositdetailid(createId.getID());
            detail.setCreateby(ShiroUtils.getUserId().toString());
            detail.setfLockerid(lockerId);
            detail.setfPositionid(positionId);
            detail.setfCasecode(cases.getfCasecode());
            detail.setState("1");
            ifDepositdetailService.insertFDepositdetail(detail);
            //添加主办人员
            //验证民警信息
            SysUser Sysuser=new SysUser();
            Sysuser.setLoginName(policeAccount);
            Sysuser.setUserName(policeName);
            List<SysUser> userList= sysUserService.selectUserList(Sysuser);
            SysUser user=new SysUser();
            //判断民警是否存在，存在赋值卡信息
            if(userList.size()>0&&!cardId.isEmpty()&&"0".equals(peopleType)&&userList.size()==1)
            {
                user=userList.get(0);
                user.setCardid(cardId);
                user.setCardcode(cardCode);
                sysUserService.updateUser(user);
            }else if(userList.size()==0){
                String source="1";
                if(!cardId.isEmpty()) {
                    user.setCardid(cardId);
                    user.setCardcode(cardCode);
                }
                user.setLoginName(policeAccount);
                user.setUserName(policeName);
                user.setSalt(ShiroUtils.randomSalt());
                user.setUserType("1");
                user.setDeptId((long)110);
                String password= new Md5Hash(policeAccount+"123456"+user.getSalt()).toHex();
                user.setPassword(password);
                user.setCreateBy(ShiroUtils.getLoginName());
                user.setSource(source);
                user.setCreateBy(source=="0"?"填写":"平台拉取");
                sysUserService.insertUser(user);
                //设置用户角色
                List<SysUserRole> userRolesList=new ArrayList<>();
                SysUserRole userRole=new SysUserRole();
                userRole.setRoleId((long)3);
                userRole.setUserId(user.getUserId());
                userRolesList.add(userRole);
                userRoleMapper.batchUserRole(userRolesList);
            }else if(!PolicePic.isEmpty()&&userList.size()>0){
                user=userList.get(0);
                PolicePic = new String(Base64.getDecoder().decode(PolicePic));
                try {
                    PolicePic = URLDecoder.decode(PolicePic, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                user.setPic(PolicePic);
                sysUserService.updateUser(user);
            }
            //添加辅主办人员
            //验证民警信息
//            SysUser Sysuser1=new SysUser();
//            Sysuser1.setLoginName(entity.getfPolice2id());
//            Sysuser1.setUserName(entity.getfPolice2name());
//            List<SysUser> userList1= sysUserService.selectUserList(Sysuser1);
//            SysUser user1=new SysUser();
//            //判断民警是否存在，存在赋值卡信息
//            if(userList.size()>0&&!cardId.isEmpty()&&"1".equals(peopleType)&&userList.size()==1)
//            {
//                user1=userList1.get(0);
//                user1.setCardid(cardId);
//                user1.setCardcode(cardCode);
//                sysUserService.updateUser(user1);
//            }else if(userList1.size()==0){
//                String source="1";
//                if(!cardId.isEmpty()) {
//                    user1.setCardid(cardId);
//                    user1.setCardcode(cardCode);
//                }
//                user1.setLoginName(entity.getfPolice2id());
//                user1.setUserName(entity.getfPolice2name());
//                user1.setSalt(ShiroUtils.randomSalt());
//                user1.setUserType("1");
//                user1.setDeptId((long)110);
//                String password= new Md5Hash(entity.getfPolice2id()+"123456"+user1.getSalt()).toHex();
//                user1.setPassword(password);
//                user1.setCreateBy(ShiroUtils.getLoginName());
//                user1.setSource(source);
//                user1.setCreateBy(source=="0"?"填写":"平台拉取");
//                sysUserService.insertUser(user1);
//                //设置用户角色
//                List<SysUserRole> userRolesList1=new ArrayList<>();
//                SysUserRole userRole1=new SysUserRole();
//                userRole1.setRoleId((long)3);
//                userRole1.setUserId(user1.getUserId());
//                userRolesList1.add(userRole1);
//                userRoleMapper.batchUserRole(userRolesList1);
//            }
            //添加轨迹
            FTrack fTrack=new FTrack();
            fTrack.setfTrackid(createId.getID());
            fTrack.setfCasecode(detail.getfCasecode());
            fTrack.setfBusinesstype(codeType.GiveBack.getName());
            fTrack.setfBusinessid(fDeposit.getfDepositid());
            fTrack.setfBusinessdetailid(detail.getfDepositdetailid());
            fTrack.setfCreateuserid(ShiroUtils.getLoginName());
            fTrack.setfCreateusername(ShiroUtils.getSysUser().getUserName());
            fTrack.setfCreatedate(new Date());
            fTrackService.insertFTrack(fTrack);

            tdwyCase tdwyCase=httprequest.sendCase(1,entity.getfCasecode(),ShiroUtils.getLoginName(),ShiroUtils.getLoginName(),1);

            String results=httprequest.upDatetdwy(tdwyCase);
            System.out.println(results);
        }
        //修改卡
        if(openDoorType.equals("1"))
        {
            fDeposit.setfType(1);
            fDeposit.setfCardcode(cardCode);
            FCard card=new FCard();
            card.setfCardid(cardId);
            card.setfUserid(policeAccount);
            card.setfUsername(policeName);
            card.setfState("1");
            fCardService.updateFCard(card,"");
        }
        fDeposit.setfType(Integer.parseInt(openDoorType));
        //发送命令
        String apiToken= httprequest.login();
        String result="";
        if(openDoorType.equals("0")) {
            result= httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
        }else if(openDoorType.equals("1")){
            result=httprequest.openBoxByCard(cardCode,position.getfPositioncode(),locker.getfLockercode(),policeName,apiToken);
        }else if(openDoorType.equals("2"))
        {
            result=httprequest.openBoxByFace(policeAccount,policeName,PolicePic,position.getfPositioncode(),locker.getfLockercode(),apiToken);
        }
        if(result.indexOf("成功")==-1) {
            fDeposit.setfState(0);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fDeposit.setfOpendate( sdf.format(new Date()));
            Date now = new Date();
            Date afterDate = new Date(now.getTime() + 300000);
            fDeposit.setfEnddate(sdf.format(afterDate));
        }
        //保存入库主表信息
        fDepositMapper.insertFDeposit(fDeposit);

        if(result.indexOf("成功")!=-1)
        {
            return "";
        }else{
            return "发送命令失败";
        }
    }
    @Override
    public String OpenBox(String id,String type){
        //查找要开门的记录
        FDeposit entity=fDepositMapper.selectFDepositById(id);
        //查找明细记录
        FDepositdetail findparam=new FDepositdetail();
        findparam.setfDepositid(id);
        FDepositdetail detail=ifDepositdetailService.selectFDepositdetailList(findparam).get(0);
        //查找智能柜编号
        FLocker locker=fLockerService.selectFLockerById(detail.getfLockerid());
        //查找货位
        FPosition position=fPositionService.selectFPositionById(detail.getfPositionid());
        SysUser Sysuser1=new SysUser();
        Sysuser1.setLoginName(entity.getfCarduserid());
        Sysuser1.setUserName(entity.getfCardusername());
        List<SysUser> userList1= sysUserService.selectUserList(Sysuser1);
        //发送命令
        String apiToken= httprequest.login();
        String result="";
        if("0".equals(type))
        {
            result= httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
        }else if("1".equals(type))
        {
            result=httprequest.openBoxByCard(entity.getfCardcode(),position.getfPositioncode(),locker.getfLockercode(),"再次打开",apiToken);
        }else if("2".equals(type))
        {
            result=httprequest.openBoxByFace(entity.getfCarduserid(),entity.getfCardusername(),userList1.get(0).getPic(),position.getfPositioncode(),locker.getfLockercode(),apiToken);
        }
        if(result.indexOf("成功")!=-1)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            entity.setfOpendate( sdf.format(new Date()));
            Date now = new Date();
            Date afterDate = new Date(now.getTime() + 300000);
            entity.setfEnddate(sdf.format(afterDate));
            entity.setfState(1);
            fDepositMapper.updateFDeposit(entity);
            return "";
        }else{
            return "发送命令失败";
        }
    }
    /**
     * 根据入库主表Id查询平台及时间信息
     * */
    @Override
    public FCamera getFcameraById(String depositId) {
        FDeposit fDeposit=fDepositMapper.selectFDepositById(depositId);
        if(fDeposit!=null) {
            FCamera fCamera = new FCamera();
            fCamera.setBeginDate(fDeposit.getfOpendate());
            fCamera.setEndDate(fDeposit.getfEnddate());
            fCamera.setfIp(Global.getCameraIp());
            fCamera.setfUsername(Global.getCameraUsername());
            fCamera.setfPassword(Global.getCameraPassword());
            fCamera.setfPuid(Global.getPuid());
            fCamera.setfPort(Global.getCameraPort());
            return fCamera;
        }else{
            return null;
        }
    }

    /**
     * 新增存放案卷
     * 
     * @param fDeposit 存放案卷
     * @return 结果
     */
    @Override
    public int insertFDeposit(FDeposit fDeposit)
    {
        return fDepositMapper.insertFDeposit(fDeposit);
    }

    /**
     * 修改存放案卷
     * 
     * @param fDeposit 存放案卷
     * @return 结果
     */
    @Override
    public int updateFDeposit(FDeposit fDeposit)
    {
        return fDepositMapper.updateFDeposit(fDeposit);
    }

    /**
     * 删除存放案卷对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFDepositByIds(String ids)
    {
        return fDepositMapper.deleteFDepositByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除存放案卷信息
     * 
     * @param fDepositid 存放案卷ID
     * @return 结果
     */
    @Override
    public int deleteFDepositById(String fDepositid)
    {
        return fDepositMapper.deleteFDepositById(fDepositid);
    }
}
