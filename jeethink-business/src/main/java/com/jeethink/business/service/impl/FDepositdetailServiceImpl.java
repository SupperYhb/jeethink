package com.jeethink.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FDepositdetailMapper;
import com.jeethink.business.domain.FDepositdetail;
import com.jeethink.business.service.IFDepositdetailService;
import com.jeethink.common.core.text.Convert;

/**
 * 案卷存入明细Service业务层处理
 * 
 * @author yhb
 * @date 2020-07-24
 */
@Service
public class FDepositdetailServiceImpl implements IFDepositdetailService 
{
    @Autowired
    private FDepositdetailMapper fDepositdetailMapper;

    /**
     * 查询案卷存入明细
     * 
     * @param fDepositdetailid 案卷存入明细ID
     * @return 案卷存入明细
     */
    @Override
    public FDepositdetail selectFDepositdetailById(String fDepositdetailid)
    {
        return fDepositdetailMapper.selectFDepositdetailById(fDepositdetailid);
    }

    /**
     * 查询案卷存入明细列表
     * 
     * @param fDepositdetail 案卷存入明细
     * @return 案卷存入明细
     */
    @Override
    public List<FDepositdetail> selectFDepositdetailList(FDepositdetail fDepositdetail)
    {
        return fDepositdetailMapper.selectFDepositdetailList(fDepositdetail);
    }

    /**
     * 新增案卷存入明细
     * 
     * @param fDepositdetail 案卷存入明细
     * @return 结果
     */
    @Override
    public int insertFDepositdetail(FDepositdetail fDepositdetail)
    {
        return fDepositdetailMapper.insertFDepositdetail(fDepositdetail);
    }

    /**
     * 修改案卷存入明细
     * 
     * @param fDepositdetail 案卷存入明细
     * @return 结果
     */
    @Override
    public int updateFDepositdetail(FDepositdetail fDepositdetail)
    {
        return fDepositdetailMapper.updateFDepositdetail(fDepositdetail);
    }

    /**
     * 删除案卷存入明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFDepositdetailByIds(String ids)
    {
        return fDepositdetailMapper.deleteFDepositdetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案卷存入明细信息
     * 
     * @param fDepositdetailid 案卷存入明细ID
     * @return 结果
     */
    @Override
    public int deleteFDepositdetailById(String fDepositdetailid)
    {
        return fDepositdetailMapper.deleteFDepositdetailById(fDepositdetailid);
    }
}
