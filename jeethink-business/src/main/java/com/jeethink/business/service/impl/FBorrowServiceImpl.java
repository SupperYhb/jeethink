package com.jeethink.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.business.domain.FBorrowdetail;
import com.jeethink.business.domain.FCases;
import com.jeethink.business.service.IFBorrowdetailService;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.common.extend.codeType;
import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.system.domain.SysUser;
import com.jeethink.system.domain.SysUserRole;
import com.jeethink.system.mapper.SysUserMapper;
import com.jeethink.system.mapper.SysUserRoleMapper;
import com.jeethink.system.service.ISysUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FBorrowMapper;
import com.jeethink.business.domain.FBorrow;
import com.jeethink.business.service.IFBorrowService;
import com.jeethink.common.core.text.Convert;

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
        fBorrow.setfUserid(ShiroUtils.getUserId().toString());
        return fBorrowMapper.selectFBorrowList(fBorrow);
    }

    /**
     * 案卷借阅
     * */
    @Override
    public int outCase(List<FCases> list, String cardCode, String cardId, String remark) {


        FBorrow borrow=new FBorrow();
        borrow.setfBorrowid(createId.getID());
        borrow.setfCode(createId.getCode(codeType.Out));
        borrow.setfCreatedate(new Date());
        borrow.setfUserid(ShiroUtils.getUserId().toString());
        borrow.setfUsername(ShiroUtils.getLoginName());
        borrow.setfState(1);
        borrow.setfCardcode(cardCode);
        for (FCases entity:list
             ) {
            //修改案卷状态
            FCases cases=new FCases();
            cases.setfId(entity.getfId());
            cases.setfState(2);
            fCasesService.updateFCases(cases);
            //添加人员
            //验证民警信息
            SysUser Sysuser=new SysUser();
            Sysuser.setLoginName(entity.getfPolice1id());
            Sysuser.setUserName(entity.getfPolice1name());
            List<SysUser> userList= SysUserService.selectUserList(Sysuser);
            SysUser user=new SysUser();
            //判断民警是否存在，存在赋值卡信息
            if(userList.size()>0&&!cardId.isEmpty())
            {
                user=userList.get(0);
                user.setCardid(cardId);
                user.setCardcode(cardCode);
                SysUserService.updateUser(user);
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
                SysUserService.insertUser(user);
                //设置用户角色
                List<SysUserRole> userRolesList=new ArrayList<>();
                SysUserRole userRole=new SysUserRole();
                userRole.setRoleId((long)3);
                userRole.setUserId(user.getUserId());
                userRolesList.add(userRole);
                userRoleMapper.batchUserRole(userRolesList);
            }
            //修改卡
            if(!cardId.isEmpty()){
                borrow.setfType(1);
                FCard card=new FCard();
                card.setfCardid(cardId);
                card.setfUserid(entity.getfPolice1id());
                card.setfUsername(entity.getfPolice1name());
                card.setfState("1");
                fCardService.updateFCard(card);
            }else{
                borrow.setfType(0);
            }
            fBorrowMapper.insertFBorrow(borrow);
            FBorrowdetail detail=new FBorrowdetail();
            detail.setfBorrowdetailid(createId.getID());
            detail.setfBorrowid(borrow.getfBorrowid());
            detail.setfCasecode(entity.getfCasecode());
            detail.setfState(1);
            detail.setCreatedate(new Date());
            fBorrowdetailService.insertFBorrowdetail(detail);
        }

        return 0;
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
