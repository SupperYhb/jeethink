package com.jeethink.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jeethink.basicInfo.domain.FCamera;
import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.business.domain.*;
import com.jeethink.business.service.IFBorrowdetailService;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.common.config.Global;
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
import com.jeethink.business.mapper.FBorrowMapper;
import com.jeethink.business.service.IFBorrowService;
import com.jeethink.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 借阅Service业务层处理
 * 
 * @author yhb
 * @date 2020-07-28
 */
@Service
public class FBorrowServiceImpl implements IFBorrowService 
{
    @Autowired
    private FBorrowMapper fBorrowMapper;
    @Autowired
    private IFBorrowdetailService fBorrowdetailService;
    @Autowired
    private IFCasesService fCasesService;
    @Autowired
    private IFCardService fCardService;
    @Autowired
    private SysUserMapper SysUserService;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private IFLockerService fLockerService;
    @Autowired
    private IFPositionService fPositionService;
    @Autowired
    private FTrackServiceImpl fTrackService;
    /**
     * 查询借阅
     * 
     * @param fBorrowid 借阅ID
     * @return 借阅
     */
    @Override
    public FBorrow selectFBorrowById(String fBorrowid)
    {
        return fBorrowMapper.selectFBorrowById(fBorrowid);
    }

    /**
     * 查询借阅列表
     * 
     * @param fBorrow 借阅
     * @return 借阅
     */
    @Override
    public List<FBorrow> selectFBorrowList(FBorrow fBorrow)
    {
        fBorrow.setfUserid(ShiroUtils.getLoginName());
        return fBorrowMapper.selectFBorrowList(fBorrow);
    }

    /**
     * 根据id查询时间及摄像头信息
     * */
    @Override
    public FCamera getFcameraById(String borrowId) {
        FBorrow fBorrow=fBorrowMapper.selectFBorrowById(borrowId);
        if(fBorrow!=null) {
            FCamera fCamera = new FCamera();
            fCamera.setBeginDate(fBorrow.getfOpendate());
            fCamera.setEndDate(fBorrow.getfEnddate());
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
     * 案卷借阅
     * */
    @Override
    @Transactional
    public String outCase(List<FCases> list, String cardCode, String cardId, String remark,String peopleType,String policeAccount,String policeName) {


        FBorrow borrow=new FBorrow();
        borrow.setfBorrowid(createId.getID());
        borrow.setfCode(createId.getCode(codeType.Out));
        borrow.setfCreatedate(new Date());
        borrow.setfUserid(ShiroUtils.getLoginName());
        borrow.setfUsername(ShiroUtils.getSysUser().getUserName());
        borrow.setfState(1);
        borrow.setfCardcode(cardCode);
        borrow.setfPeopletype(peopleType);
        borrow.setfCarduserid(policeAccount);
        borrow.setfCardusername(policeName);
        for (FCases entity:list
             ) {
            //修改案卷状态
            FCases cases=new FCases();
            cases.setfId(entity.getfId());
            cases.setfState(2);
            cases.setfLockerid("");
            cases.setfLockername("");
            cases.setfPositionid("");
            cases.setfPositioncode("");
            fCasesService.updateFCases(cases);
            //添加主办人员
            //验证民警信息
            SysUser Sysuser=new SysUser();
            Sysuser.setLoginName(policeAccount);
            Sysuser.setUserName(policeName);
            List<SysUser> userList= SysUserService.selectUserList(Sysuser);
            SysUser user=new SysUser();
            //判断民警是否存在，存在赋值卡信息
            if(userList.size()>0&&!cardId.isEmpty()&&"0".equals(peopleType)&&userList.size()==1)
            {
                user=userList.get(0);
                user.setCardid(cardId);
                user.setCardcode(cardCode);
                SysUserService.updateUser(user);
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
                SysUserService.insertUser(user);
                //设置用户角色
                List<SysUserRole> userRolesList=new ArrayList<>();
                SysUserRole userRole=new SysUserRole();
                userRole.setRoleId((long)3);
                userRole.setUserId(user.getUserId());
                userRolesList.add(userRole);
                userRoleMapper.batchUserRole(userRolesList);
            }
            //添加辅办人员
            //验证民警信息
//            SysUser Sysuser1=new SysUser();
//            Sysuser1.setLoginName(entity.getfPolice2id());
//            Sysuser1.setUserName(entity.getfPolice2name());
//            List<SysUser> userList1= SysUserService.selectUserList(Sysuser1);
//            SysUser user1=new SysUser();
//            //判断民警是否存在，存在赋值卡信息
//            if(userList1.size()>0&&!cardId.isEmpty()&&"1".equals(peopleType)&&userList.size()==1)
//            {
//                user1=userList1.get(0);
//                user1.setCardid(cardId);
//                user1.setCardcode(cardCode);
//                SysUserService.updateUser(user1);
//            }else if(userList1.size()==0){
//                String source="1";
//                if(!cardId.isEmpty()&&"1".equals(peopleType)) {
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
//                SysUserService.insertUser(user);
//                //设置用户角色
//                List<SysUserRole> userRolesList1=new ArrayList<>();
//                SysUserRole userRole1=new SysUserRole();
//                userRole1.setRoleId((long)3);
//                userRole1.setUserId(user1.getUserId());
//                userRolesList1.add(userRole1);
//                userRoleMapper.batchUserRole(userRolesList1);
//            }


            FBorrowdetail detail=new FBorrowdetail();
            detail.setfBorrowdetailid(createId.getID());
            detail.setfBorrowid(borrow.getfBorrowid());
            detail.setfCasecode(entity.getfCasecode());
            detail.setfState(1);
            detail.setCreatedate(new Date());
            fBorrowdetailService.insertFBorrowdetail(detail);
            //添加轨迹
            FTrack fTrack=new FTrack();
            fTrack.setfTrackid(createId.getID());
            fTrack.setfCasecode(detail.getfCasecode());
            fTrack.setfBusinesstype(codeType.Out.getName());
            fTrack.setfBusinessid(borrow.getfBorrowid());
            fTrack.setfBusinessdetailid(detail.getfBorrowdetailid());
            fTrack.setfCreateuserid(ShiroUtils.getLoginName());
            fTrack.setfCreateusername(ShiroUtils.getSysUser().getUserName());
            fTrack.setfCreatedate(new Date());
            fTrackService.insertFTrack(fTrack);
        }
        //获取卷宗柜
        FLocker locker=fLockerService.selectFLockerById(list.get(0).getfLockerid());
        //获取货位
        FPosition position=fPositionService.selectFPositionById(list.get(0).getfPositionid());
        //修改卡
        if(!cardId.isEmpty()){
            borrow.setfType(1);
            FCard card=new FCard();
            card.setfCardid(cardId);
            card.setfUserid(policeAccount);
            card.setfUsername(policeName);
            card.setfState("1");
            card.setfLockercode(locker.getfLockercode());
            card.setfPositioncode(position.getfPositioncode());
            fCardService.updateFCard(card,"");
        }else{
            borrow.setfType(0);
        }
        //发送命令

        String apiToken= httprequest.login();
        String result="";
        if(cardId.isEmpty()&&!apiToken.isEmpty()) {
            result= httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
        }else if(!apiToken.isEmpty()){
            result=httprequest.openBoxByCard(cardCode,position.getfPositioncode(),locker.getfLockercode(),policeName,apiToken);
        }
        if(result.indexOf("成功")==-1) {
            borrow.setfState(0);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            borrow.setfOpendate( sdf.format(new Date()));
            Date now = new Date();
            Date afterDate = new Date(now.getTime() + 300000);
            borrow.setfEnddate(sdf.format(afterDate));
        }
        fBorrowMapper.insertFBorrow(borrow);

        if(result.indexOf("成功")!=-1)
        {
            return "";
        }else{
            return "发送命令失败";
        }
    }
    @Override
    public String OpenBox(String id,String type){

        //获取主表信息
        FBorrow entity=fBorrowMapper.selectFBorrowById(id);
        FBorrowdetail findparam=new FBorrowdetail();
        findparam.setfBorrowid(id);
        //获取明细数据
        FBorrowdetail detail=fBorrowdetailService.selectFBorrowdetailList(findparam).get(0);
        //查找案卷信息
        FCases findparamcases=new FCases();
        findparamcases.setfCasecode(detail.getfCasecode());
        FCases cases=fCasesService.selectFCasesList(findparamcases).get(0);
        //查找智能柜编号
        FLocker locker=fLockerService.selectFLockerById(cases.getfLockerid());
        //查找货位
        FPosition position=fPositionService.selectFPositionById(cases.getfPositionid());
        //发送命令
        String apiToken= httprequest.login();
        String result="";
        if("0".equals(type))
        {
            result= httprequest.openBox(locker.getfLockercode(), position.getfPositioncode(), apiToken);
        }else if("1".equals(type))
        {
            result=httprequest.openBoxByCard(entity.getfCardcode(),position.getfPositioncode(),locker.getfLockercode(),"再次打开",apiToken);
        }
        if(result.indexOf("成功")!=-1)
        {  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            entity.setfOpendate( sdf.format(new Date()));
            Date now = new Date();
            Date afterDate = new Date(now.getTime() + 300000);
            entity.setfEnddate(sdf.format(afterDate));
            entity.setfState(1);
            fBorrowMapper.updateFBorrow(entity);
            return "";
        }else{
            return "发送命令失败";
        }
    }

    /**
     * 新增借阅
     * 
     * @param fBorrow 借阅
     * @return 结果
     */
    @Override
    public int insertFBorrow(FBorrow fBorrow)
    {
        return fBorrowMapper.insertFBorrow(fBorrow);
    }

    /**
     * 修改借阅
     * 
     * @param fBorrow 借阅
     * @return 结果
     */
    @Override
    public int updateFBorrow(FBorrow fBorrow)
    {
        return fBorrowMapper.updateFBorrow(fBorrow);
    }

    /**
     * 删除借阅对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFBorrowByIds(String ids)
    {
        return fBorrowMapper.deleteFBorrowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除借阅信息
     * 
     * @param fBorrowid 借阅ID
     * @return 结果
     */
    @Override
    public int deleteFBorrowById(String fBorrowid)
    {
        return fBorrowMapper.deleteFBorrowById(fBorrowid);
    }
}
