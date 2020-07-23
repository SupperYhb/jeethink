package com.jeethink.business.service.impl;

import java.util.List;

import com.jeethink.requestutil.entity.kdcaseentity;
import com.jeethink.requestutil.function.httprequest;
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
        return fDepositMapper.selectFDepositList(fDeposit);
    }

    @Override
    public List<kdcaseentity> getkdCase() {
       List<kdcaseentity> list= httprequest.getCase();
        return list;
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
