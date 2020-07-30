package com.jeethink.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.business.domain.FCases;
import com.jeethink.business.domain.FDepositdetail;
import com.jeethink.business.mapper.FCasesMapper;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.business.service.IFDepositdetailService;
import com.jeethink.common.extend.codeType;
import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.requestutil.entity.kdcaseentity;
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
import com.jeethink.business.domain.FDeposit;
import com.jeethink.business.service.IFDepositService;
import com.jeethink.common.core.text.Convert;

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

        fDeposit.setfUserid(ShiroUtils.getUserId().toString());
        return fDepositMapper.selectFDepositList(fDeposit);
    }

    @Override
    public List<kdcaseentity> getkdCase() {
       List<kdcaseentity> list= httprequest.getCase();
       List<FCases> casesList =fCasesMapper.selectFCasesList(null);
       List<kdcaseentity> resultList=list.stream().filter(kdcaseentity->casesList.stream().noneMatch(cases->cases.getfCasecode().equals(kdcaseentity.getNo()))).collect(Collectors.toList());
        return resultList;
    }

    /**
     * 新增案卷入库（平台拉取）
     * */
    @Override
    public int addCaseIn(List<kdcaseentity> list, String lockerId, String positionId, String cardCode, String cardId, String remark) {

        //获取卷宗柜
        FLocker locker=fLockerService.selectFLockerById(lockerId);
        //获取货位
        FPosition position=fPositionService.selectFPositionById(positionId);

        //添加入库主表
        FDeposit fDeposit=new FDeposit();
        fDeposit.setfDepositid(createId.getID());
        fDeposit.setfCode(createId.getCode(codeType.LQIn));
        fDeposit.setfCreatedate(new Date());
        fDeposit.setfUserid(ShiroUtils.getUserId().toString());
        fDeposit.setfUsername(ShiroUtils.getLoginName());
        fDeposit.setfState(1);
        fDeposit.setfBusinesstype("1");
        for (kdcaseentity entity:list
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
            cases.setfCasecode(entity.getNo());
            cases.setfCasename(entity.getName());
            cases.setfCasebriefdetail(entity.getBriefCase());
            cases.setfPutdate(entity.getAcceptanceTime());
            cases.setfPolice1id(entity.getPoliceCode());
            cases.setfPolice1name(entity.getPoliceName());
            cases.setfCriminalcode(entity.getSuspectCode());
            cases.setfCriminalname(entity.getSuspectName());
            cases.setfCrimeaddress(entity.getCrimeAddress());
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
            //添加人员
            //验证民警信息
            SysUser Sysuser=new SysUser();
            Sysuser.setLoginName(entity.getPoliceCode());
            Sysuser.setUserName(entity.getPoliceName());
            List<SysUser> userList= sysUserService.selectUserList(Sysuser);
            SysUser user=new SysUser();
            //判断民警是否存在，存在赋值卡信息
            if(userList.size()>0&&!cardId.isEmpty())
            {
                user=userList.get(0);
                user.setCardid(cardId);
                user.setCardcode(cardCode);
                sysUserService.updateUser(user);
            }else{
                String source="1";
                if(!cardId.isEmpty()) {
                    user.setCardid(cardId);
                    user.setCardcode(cardCode);
                }
                user.setLoginName(entity.getPoliceCode());
                user.setUserName(entity.getPoliceName());
                user.setSalt(ShiroUtils.randomSalt());
                user.setUserType("1");
                user.setDeptId((long)110);
                String password= new Md5Hash(entity.getPoliceCode()+"123456"+user.getSalt()).toHex();
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
            }
        }
        //修改卡
        if(!cardId.isEmpty())
        {
            fDeposit.setfType(1);
            fDeposit.setfCardcode(cardCode);
            FCard card=new FCard();
            card.setfCardid(cardId);
            card.setfUserid(list.get(0).getPoliceCode());
            card.setfUsername(list.get(0).getPoliceName());
            card.setfState("1");
            fCardService.updateFCard(card);
        }else{
            fDeposit.setfType(0);
        }
        //保存入库主表信息
        fDepositMapper.insertFDeposit(fDeposit);

        return 0;
    }

    @Override
    public int addCaseReturn(List<FCases> list, String lockerId, String positionId, String cardCode, String cardId, String remark) {

        //获取卷宗柜
        FLocker locker=fLockerService.selectFLockerById(lockerId);
        //获取货位
        FPosition position=fPositionService.selectFPositionById(positionId);

        //添加入库主表
        FDeposit fDeposit=new FDeposit();
        fDeposit.setfDepositid(createId.getID());
        fDeposit.setfCode(createId.getCode(codeType.GiveBack));
        fDeposit.setfCreatedate(new Date());
        fDeposit.setfUserid(ShiroUtils.getUserId().toString());
        fDeposit.setfUsername(ShiroUtils.getLoginName());
        fDeposit.setfState(1);
        fDeposit.setfBusinesstype("2");
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
            //添加人员
            //验证民警信息
            SysUser Sysuser=new SysUser();
            Sysuser.setLoginName(entity.getfPolice1id());
            Sysuser.setUserName(entity.getfPolice1name());
            List<SysUser> userList= sysUserService.selectUserList(Sysuser);
            SysUser user=new SysUser();
            //判断民警是否存在，存在赋值卡信息
            if(userList.size()>0&&!cardId.isEmpty())
            {
                user=userList.get(0);
                user.setCardid(cardId);
                user.setCardcode(cardCode);
                sysUserService.updateUser(user);
            }else{
                String source="1";
                if(!cardId.isEmpty()) {
                    user.setCardid(cardId);
                    user.setCardcode(cardCode);
                }
                user.setLoginName(entity.getfPolice1id());
                user.setUserName(entity.getfPolice1name());
                user.setSalt(ShiroUtils.randomSalt());
                user.setUserType("1");
                user.setDeptId((long)110);
                String password= new Md5Hash(entity.getfPolice1id()+"123456"+user.getSalt()).toHex();
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
            }
        }
        //修改卡
        if(!cardId.isEmpty())
        {
            fDeposit.setfType(1);
            fDeposit.setfCardcode(cardCode);
            FCard card=new FCard();
            card.setfCardid(cardId);
            card.setfUserid(list.get(0).getfPolice1id());
            card.setfUsername(list.get(0).getfPolice1name());
            card.setfState("1");
            fCardService.updateFCard(card);
        }else{
            fDeposit.setfType(0);
        }
        //保存入库主表信息
        fDepositMapper.insertFDeposit(fDeposit);

        return 0;
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
