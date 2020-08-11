package com.jeethink.business.service.impl;

import java.util.Date;
import java.util.List;

import com.jeethink.business.domain.FCases;
import com.jeethink.business.domain.FCheckdetail;
import com.jeethink.business.mapper.FCheckdetailMapper;
import com.jeethink.common.extend.codeType;
import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FCheckMapper;
import com.jeethink.business.domain.FCheck;
import com.jeethink.business.service.IFCheckService;
import com.jeethink.common.core.text.Convert;

/**
 * 盘点主表Service业务层处理
 * 
 * @author yhb
 * @date 2020-08-07
 */
@Service
public class FCheckServiceImpl implements IFCheckService 
{
    @Autowired
    private FCheckMapper fCheckMapper;
    @Autowired
    private FCasesServiceImpl fCasesService;
    @Autowired
    private FCheckdetailMapper fCheckdetailMapper;

    /**
     * 查询盘点主表
     * 
     * @param fCheckid 盘点主表ID
     * @return 盘点主表
     */
    @Override
    public FCheck selectFCheckById(String fCheckid)
    {
        return fCheckMapper.selectFCheckById(fCheckid);
    }

    /**
     * 查询盘点主表列表
     * 
     * @param fCheck 盘点主表
     * @return 盘点主表
     */
    @Override
    public List<FCheck> selectFCheckList(FCheck fCheck)
    {
        return fCheckMapper.selectFCheckList(fCheck);
    }

    /** 添加盘点 */
    public String addCheck(String lockerId,String name){

        String Result="";
        
        FCheck fCheck=new FCheck();
        fCheck.setfCheckid(createId.getID());
        fCheck.setfLockerid(lockerId);
        fCheck.setfName(name);
        fCheck.setfCode(createId.getCode(codeType.Check));
        fCheck.setfCreateuserid(ShiroUtils.getLoginName());
        fCheck.setfCreatedate(new Date());
        fCheck.setfCreateusername(ShiroUtils.getSysUser().getUserName());
        fCheckMapper.insertFCheck(fCheck);
        List<FCases> fCasesList=fCasesService.selectByLockerId(lockerId);
        for (FCases cases: fCasesList
             ) {
            FCheckdetail fCheckdetail=new FCheckdetail();
            fCheckdetail.setfCheckdetailid(createId.getID());
            fCheckdetail.setfCheckid(fCheck.getfCheckid());
            fCheckdetail.setfCasecode(cases.getfCasecode());
            fCheckdetailMapper.insertFCheckdetail(fCheckdetail);
        }
        return Result;
    }
    /**
     * 新增盘点主表
     * 
     * @param fCheck 盘点主表
     * @return 结果
     */
    @Override
    public int insertFCheck(FCheck fCheck)
    {
        return fCheckMapper.insertFCheck(fCheck);
    }

    /**
     * 修改盘点主表
     * 
     * @param fCheck 盘点主表
     * @return 结果
     */
    @Override
    public int updateFCheck(FCheck fCheck)
    {
        return fCheckMapper.updateFCheck(fCheck);
    }

    /**
     * 删除盘点主表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFCheckByIds(String ids)
    {
        return fCheckMapper.deleteFCheckByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除盘点主表信息
     * 
     * @param fCheckid 盘点主表ID
     * @return 结果
     */
    @Override
    public int deleteFCheckById(String fCheckid)
    {
        return fCheckMapper.deleteFCheckById(fCheckid);
    }
}
