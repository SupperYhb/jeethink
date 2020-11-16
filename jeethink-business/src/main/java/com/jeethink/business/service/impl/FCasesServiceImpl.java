package com.jeethink.business.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.business.domain.*;
import com.jeethink.business.service.IFDepositService;
import com.jeethink.business.service.IFDepositdetailService;
import com.jeethink.common.extend.codeType;
import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.framework.web.service.ConfigService;
import com.jeethink.requestutil.function.httprequest;
import com.jeethink.system.domain.SysUser;
import com.jeethink.system.domain.SysUserRole;
import com.jeethink.system.mapper.SysUserMapper;
import com.jeethink.system.mapper.SysUserRoleMapper;
import com.jeethink.system.service.ISysUserService;
import com.jeethink.system.service.impl.SysConfigServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FCasesMapper;
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
        cases.setfIsBack(0);
        if("1".equals(ShiroUtils.getType()))
        {
            cases.setfPolice1id(ShiroUtils.getLoginName());
            cases.setfPolice2id(ShiroUtils.getLoginName());
        }
        return fCasesMapper.selectFCasesList(cases);
    }

    /**
     * 根据案卷柜Id查询在库案卷
     * */
    @Override
    public List<FCases> selectByLockerId(String lockerId) {
        return fCasesMapper.selectByLockerId(lockerId);
    }

    /**
     * 查询盘点明细
     * */
    @Override
    public List<FCases> selectByCheckId(String checkId,String unChecked) {
        Map map=new HashMap();
        map.put("checkId",checkId);
        map.put("Unchecked",unChecked);
        return fCasesMapper.selectByCheckId(map);
    }
    /**
     * 查询可盘点信息
     * */
    @Override
    public List<FCases> selectByCheckAndState(String checkId) {
        return fCasesMapper.selectByCheckAndState(checkId);
    }

    /**
     * 查询超期借阅列表
     * */
    @Override
    public List<FCases> selectOverdueList(String caseName,String caseCode) {
       String overTimeCount= new SysConfigServiceImpl().selectConfigByKey("fabinet.overtime");
        Map map=new HashMap();
        map.put("overTimeCount",overTimeCount);
        map.put("caseName",caseName);
        map.put("caseCode",caseCode);
       return fCasesMapper.selectOverdueList(map);
    }


    /**
     * 新增案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    @Override
    @Transactional
    public String insertFCases(FCases fCases,String peopleType,String mainPolicePic,String auxiliaryPolicePic,String openDoorType)
    {
        FCases findCases=new FCases();
        findCases.setfCasecode(fCases.getfCasecode());
        List<FCases> findList=fCasesMapper.selectFCasesList(findCases);
        if(findList.size()>0)
        {
            return "案卷编号已存在！";
        }
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
        fDeposit.setfCarduserid((peopleType.equals("0")?fCases.getfPolice1id():fCases.getfPolice1name()));
        fDeposit.setfCardusername((peopleType.equals("1")?fCases.getfPolice2id():fCases.getfPolice2name()));
        //记录开门的方式，暂时待定

            fDeposit.setfCardcode(fCases.getCardCode());
            fDeposit.setfType(Integer.parseInt(openDoorType.split(",")[0]));
            if(openDoorType.indexOf('1')!=-1) {
                FCard card = new FCard();
                card.setfCardid(fCases.getCardId());
                card.setfUserid("0".equals(peopleType) ? fCases.getfPolice1id() : fCases.getfPolice2id());
                card.setfUsername("0".equals(peopleType) ? fCases.getfPolice1name() : fCases.getfPolice2name());
                card.setfState("1");
                card.setfLockercode(locker.getfLockercode());
                card.setfPositioncode(position.getfPositioncode());
                fCardService.updateFCard(card, "");
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
            if(!mainPolicePic.isEmpty()){
                mainPolicePic = new String(Base64.getDecoder().decode(mainPolicePic));
                try {
                    mainPolicePic = URLDecoder.decode(mainPolicePic, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                user.setPic(mainPolicePic);
            }
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
            if(!mainPolicePic.isEmpty()){
                mainPolicePic = new String(Base64.getDecoder().decode(mainPolicePic));
                try {
                    mainPolicePic = URLDecoder.decode(mainPolicePic, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                user.setPic(mainPolicePic);
            }
            sysUserService.insertUser(user);

            //设置用户角色
            List<SysUserRole> userRolesList=new ArrayList<>();
            SysUserRole userRole=new SysUserRole();
            userRole.setRoleId((long)3);
            userRole.setUserId(user.getUserId());
            userRolesList.add(userRole);
            userRoleMapper.batchUserRole(userRolesList);
           }else if(!mainPolicePic.isEmpty()&&userList.size()>0)
           {
               user=userList.get(0);
               mainPolicePic = new String(Base64.getDecoder().decode(mainPolicePic));
               try {
                   mainPolicePic = URLDecoder.decode(mainPolicePic, "UTF-8");
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
               }
               user.setPic(mainPolicePic);
               sysUserService.updateUser(user);
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
            if(!auxiliaryPolicePic.isEmpty()){
                auxiliaryPolicePic = new String(Base64.getDecoder().decode(auxiliaryPolicePic));
                try {
                    auxiliaryPolicePic = URLDecoder.decode(auxiliaryPolicePic, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                user1.setPic(auxiliaryPolicePic);
            }
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
            if(!auxiliaryPolicePic.isEmpty()){
                auxiliaryPolicePic = new String(Base64.getDecoder().decode(auxiliaryPolicePic));
                try {
                    auxiliaryPolicePic = URLDecoder.decode(auxiliaryPolicePic, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                user1.setPic(auxiliaryPolicePic);
            }
            sysUserService.insertUser(user1);

            //设置用户角色
            List<SysUserRole> userRolesList1=new ArrayList<>();
            SysUserRole userRole1=new SysUserRole();
            userRole1.setRoleId((long)3);
            userRole1.setUserId(user1.getUserId());
            userRolesList1.add(userRole1);
            userRoleMapper.batchUserRole(userRolesList1);
        }else if(!auxiliaryPolicePic.isEmpty()&&userList1.size()>0)
        {
            auxiliaryPolicePic = new String(Base64.getDecoder().decode(auxiliaryPolicePic));
            try {
                auxiliaryPolicePic = URLDecoder.decode(auxiliaryPolicePic, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            user1=userList1.get(0);
            user1.setPic(auxiliaryPolicePic);
            sysUserService.updateUser(user1);
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
        String account=peopleType.equals("0")?fCases.getfPolice1id():fCases.getfPolice2id();
        String userName=peopleType.equals("0")?fCases.getfPolice1name():fCases.getfPolice2name();
        //存入民警信息
        fCasesMapper.insertFCases(fCases);

        //发送命令
        String apiToken= httprequest.login();
        String result="";
        if(!apiToken.isEmpty()) {
            //直接打开
            if (openDoorType.indexOf("0")!=-1) {
                result = httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
            }
            //刷卡打开
            else if(openDoorType.indexOf("1")!=-1) {
                result = httprequest.openBoxByCard(fCases.getCardCode(), position.getfPositioncode(), locker.getfLockercode(), userName, apiToken);
            }
            //刷脸打开
            else if(openDoorType.indexOf("2")!=-1)
            {
                result=httprequest.openBoxByFace(account,userName,(peopleType.equals("0")?mainPolicePic:auxiliaryPolicePic),position.getfPositioncode(),locker.getfLockercode(),apiToken);
            }
        }else{
            result="登录失败";
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
        fDepositService.insertFDeposit(fDeposit);
        if(result.indexOf("成功")!=-1)
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
