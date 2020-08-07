package com.jeethink.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.business.domain.FDeposit;
import com.jeethink.business.domain.FDepositdetail;
import com.jeethink.business.domain.FTrack;
import com.jeethink.business.service.IFDepositService;
import com.jeethink.business.service.IFDepositdetailService;
import com.jeethink.common.extend.codeType;
import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.requestutil.function.httprequest;
import com.jeethink.system.domain.SysUser;
import com.jeethink.system.domain.SysUserRole;
import com.jeethink.system.mapper.SysUserMapper;
import com.jeethink.system.mapper.SysUserRoleMapper;
import com.jeethink.system.service.ISysUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FCasesMapper;
import com.jeethink.business.domain.FCases;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 案卷Service业务层处理
 *
 * @author yhb
 * @date 2020-07-23
 */
@Service
public class FCasesServiceImpl implements IFCasesService
{
    @Autowired
    private FCasesMapper fCasesMapper;
    @Autowired
    private IFLockerService fLockerService;
    @Autowired
    private IFPositionService fPositionService;
    @Autowired
    private IFCardService fCardService;
    @Autowired
    private IFDepositService fDepositService;
    @Autowired
    private SysUserMapper sysUserService;
    @Autowired
    private IFDepositdetailService ifDepositdetailService;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private FTrackServiceImpl fTrackService;

    /**
     * 查询案卷
     *
     * @param fId 案卷ID
     * @return 案卷
     */
    @Override
    public FCases selectFCasesById(String fId)
    {
        return fCasesMapper.selectFCasesById(fId);
    }

    /**
     * 查询案卷列表
     *
     * @param fCases 案卷
     * @return 案卷
     */
    @Override
    public List<FCases> selectFCasesList(FCases fCases)
    {
        String type=ShiroUtils.getType();
        if("1".equals(type)) {
            fCases.setfPolice1id(ShiroUtils.getLoginName());
            fCases.setfPolice2id(ShiroUtils.getLoginName());
        }
        return fCasesMapper.selectFCasesList(fCases);
    }

    /**
     * 查询入库的案卷
     * */
    @Override
    public List<FCases> selectBydepositId(String depositId) {
        return fCasesMapper.selectBydepositId(depositId);
    }

    /**
     * 查询借阅明细
     * */
    @Override
    public List<FCases> selectByborrowId(String borrowId) {
        return fCasesMapper.selectByborrowId(borrowId);
    }

    /**
     * 查询待归还案卷
     * */
    @Override
    public List<FCases> selectByOut(String caseCode, String policeNo, String caseName) {
        FCases cases=new FCases();
        cases.setfCasecode(caseCode);
        cases.setfPolice1id(policeNo);
        cases.setfCasename(caseName);
        cases.setfState(2);
        if("1".equals(ShiroUtils.getType()))
        {
            cases.setfPolice1id(ShiroUtils.getLoginName());
            cases.setfPolice2id(ShiroUtils.getLoginName());
        }
        return fCasesMapper.selectFCasesList(cases);
    }

    /**
     * 新增案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    @Override
    @Transactional
    public String insertFCases(FCases fCases,String peopleType)
    {
        //获取卷宗柜
        FLocker locker=fLockerService.selectFLockerById(fCases.getfLockerid());
        //获取货位
        FPosition position=fPositionService.selectFPositionById(fCases.getfPositionid());
        //存入案卷信息
        fCases.setfId(createId.getID());
        fCases.setfCreatedate(new Date());
        fCases.setfState(1);
        fCases.setfLockername(locker.getfLockername());
        fCases.setfPositioncode(position.getfPositioncode());
        //存入主表信息
        FDeposit fDeposit=new FDeposit();
        fDeposit.setfDepositid(createId.getID());
        fDeposit.setfCode(createId.getCode(codeType.In));
        fDeposit.setfCreatedate(new Date());
        fDeposit.setfUserid(ShiroUtils.getLoginName());
        fDeposit.setfUsername(ShiroUtils.getSysUser().getUserName());
        fDeposit.setfBusinesstype("1");
        fDeposit.setfPeopletype(peopleType);
        //记录开门的方式，暂时待定
        if(fCases.getCardCode().isEmpty()) {
            fDeposit.setfType(0);
        }else{
            fDeposit.setfCardcode(fCases.getCardCode());
            fDeposit.setfType(1);
            FCard card=new FCard();
            card.setfCardid(fCases.getCardId());
            card.setfUserid("0".equals(peopleType)? fCases.getfPolice1id():fCases.getfPolice2id());
            card.setfUsername("0".equals(peopleType)? fCases.getfPolice1name():fCases.getfPolice2name());
            card.setfState("1");
            fCardService.updateFCard(card,"");
        }
        //设置存入记录的状态，不知道是不是保存就调用，暂时默认为已入库
        fDeposit.setfState(1);

        //存入明细信息
        FDepositdetail detail=new FDepositdetail();
        detail.setfDepositdetailid(createId.getID());
        detail.setCreateby(ShiroUtils.getUserId().toString());
        detail.setfLockerid(fCases.getfLockerid());
        detail.setfPositionid(fCases.getfPositionid());
        detail.setfCasecode(fCases.getfCasecode());
        detail.setCreatedate(new Date());
        detail.setfDepositid(fDeposit.getfDepositid());
        //暂时定义为1
        detail.setState("1");
        ifDepositdetailService.insertFDepositdetail(detail);
        //验证主办民警信息
        SysUser Sysuser=new SysUser();
        Sysuser.setLoginName(fCases.getfPolice1id());
        Sysuser.setUserName(fCases.getfPolice1name());
        List<SysUser> userList= sysUserService.selectUserList(Sysuser);
        SysUser user=new SysUser();
        //判断民警是否存在，存在赋值卡信息
        if(userList.size()>0&&!fCases.getCardId().isEmpty()&&"0".equals(peopleType))
        {
            user=userList.get(0);
            user.setCardid(fCases.getCardId());
            user.setCardcode(fCases.getCardCode());
            sysUserService.updateUser(user);
        }else if(userList.size()==0&&!fCases.getfPolice1id().isEmpty()){
            String source="0";
            if("0".equals(peopleType)&&!fCases.getCardId().isEmpty()) {
                user.setCardid(fCases.getCardId());
                user.setCardcode(fCases.getCardCode());
            }
            user.setLoginName(fCases.getfPolice1id());
            user.setUserName(fCases.getfPolice1name());
            user.setSalt(ShiroUtils.randomSalt());
            user.setUserType("1");
            user.setDeptId((long)110);
            String password= new Md5Hash(fCases.getfPolice1id()+"123456"+user.getSalt()).toHex();
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
        //验证辅办民警信息
        SysUser Sysuser1=new SysUser();
        Sysuser1.setLoginName(fCases.getfPolice2id());
        Sysuser1.setUserName(fCases.getfPolice2name());
        List<SysUser> userList1= sysUserService.selectUserList(Sysuser1);
        SysUser user1=new SysUser();
        //判断民警是否存在，存在赋值卡信息
        if(userList1.size()>0&&!fCases.getCardId().isEmpty()&&"1".equals(peopleType))
        {
            user1=userList1.get(0);
            user1.setCardid(fCases.getCardId());
            user1.setCardcode(fCases.getCardCode());
            sysUserService.updateUser(user1);
        }else if(userList1.size()==0&&!fCases.getfPolice2id().isEmpty()){
            if("1".equals(peopleType)&&!fCases.getCardId().isEmpty()) {
                user1.setCardid(fCases.getCardId());
                user1.setCardcode(fCases.getCardCode());
            }
            user1.setLoginName(fCases.getfPolice2id());
            user1.setUserName(fCases.getfPolice2name());
            user1.setSalt(ShiroUtils.randomSalt());
            user1.setUserType("1");
            user1.setDeptId((long)110);
            String password1= new Md5Hash(fCases.getfPolice2id()+"123456"+user1.getSalt()).toHex();
            user1.setPassword(password1);
            user1.setCreateBy(ShiroUtils.getLoginName());
            user1.setSource("0");
            user1.setCreateBy("填写");
            sysUserService.insertUser(user1);

            //设置用户角色
            List<SysUserRole> userRolesList1=new ArrayList<>();
            SysUserRole userRole1=new SysUserRole();
            userRole1.setRoleId((long)3);
            userRole1.setUserId(user1.getUserId());
            userRolesList1.add(userRole1);
            userRoleMapper.batchUserRole(userRolesList1);
        }
        //添加案卷轨迹
        FTrack fTrack=new FTrack();
        fTrack.setfTrackid(createId.getID());
        fTrack.setfCasecode(detail.getfCasecode());
        fTrack.setfBusinesstype(codeType.In.getName());
        fTrack.setfBusinessid(fDeposit.getfDepositid());
        fTrack.setfBusinessdetailid(detail.getfDepositdetailid());
        fTrack.setfCreateuserid(ShiroUtils.getLoginName());
        fTrack.setfCreateusername(ShiroUtils.getSysUser().getUserName());
        fTrack.setfCreatedate(new Date());
        fTrackService.insertFTrack(fTrack);
        String userName=peopleType=="0"?fCases.getfPolice1id():fCases.getfPolice2id();
        //存入民警信息
        fCasesMapper.insertFCases(fCases);

        //发送命令
        String apiToken= httprequest.login();
        String result="";
        if(!apiToken.isEmpty()) {
            if (fCases.getCardId().isEmpty()) {
                result = httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
            } else {
                result = httprequest.openBoxByCard(fCases.getCardCode(), position.getfPositioncode(), locker.getfLockercode(), userName, apiToken);
            }
        }else{
            result="登录失败";
        }
        if(result.indexOf("控制成功")==-1) {
            fDeposit.setfState(0);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fDeposit.setfOpendate( sdf.format(new Date()));
            Date now = new Date();
            Date afterDate = new Date(now.getTime() + 300000);
            fDeposit.setfEnddate(sdf.format(afterDate));
        }
        //保存入库主表信息
        fDepositService.insertFDeposit(fDeposit);
        if(result.indexOf("控制成功")!=-1)
        {
            return "";
        }else{
            return "发送命令失败";
        }
    }

    /**
     * 修改案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    @Override
    public int updateFCases(FCases fCases)
    {
        return fCasesMapper.updateFCases(fCases);
    }

    /**
     * 删除案卷对象
     *insertFCases
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFCasesByIds(String ids)
    {
        return fCasesMapper.deleteFCasesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案卷信息
     *
     * @param fId 案卷ID
     * @return 结果
     */
    @Override
    public int deleteFCasesById(String fId)
    {
        return fCasesMapper.deleteFCasesById(fId);
    }
}
