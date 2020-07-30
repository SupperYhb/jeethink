package com.jeethink.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FBorrowdetailMapper;
import com.jeethink.business.domain.FBorrowdetail;
import com.jeethink.business.service.IFBorrowdetailService;
import com.jeethink.common.core.text.Convert;

/**
 * 取出明细Service业务层处理
 * 
 * @author yhb
 * @date 2020-07-28
 */
@Service
public class FBorrowdetailServiceImpl implements IFBorrowdetailService 
{
    @Autowired
    private FBorrowdetailMapper fBorrowdetailMapper;

    /**
     * 查询取出明细
     * 
     * @param fBorrowdetailid 取出明细ID
     * @return 取出明细
     */
    @Override
    public FBorrowdetail selectFBorrowdetailById(String fBorrowdetailid)
    {
        return fBorrowdetailMapper.selectFBorrowdetailById(fBorrowdetailid);
    }

    /**
     * 查询取出明细列表
     * 
     * @param fBorrowdetail 取出明细
     * @return 取出明细
     */
    @Override
    public List<FBorrowdetail> selectFBorrowdetailList(FBorrowdetail fBorrowdetail)
    {
        return fBorrowdetailMapper.selectFBorrowdetailList(fBorrowdetail);
    }

    /**
     * 新增取出明细
     * 
     * @param fBorrowdetail 取出明细
     * @return 结果
     */
    @Override
    public int insertFBorrowdetail(FBorrowdetail fBorrowdetail)
    {
        return fBorrowdetailMapper.insertFBorrowdetail(fBorrowdetail);
    }

    /**
     * 修改取出明细
     * 
     * @param fBorrowdetail 取出明细
     * @return 结果
     */
    @Override
    public int updateFBorrowdetail(FBorrowdetail fBorrowdetail)
    {
        return fBorrowdetailMapper.updateFBorrowdetail(fBorrowdetail);
    }

    /**
     * 删除取出明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFBorrowdetailByIds(String ids)
    {
        return fBorrowdetailMapper.deleteFBorrowdetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除取出明细信息
     * 
     * @param fBorrowdetailid 取出明细ID
     * @return 结果
     */
    @Override
    public int deleteFBorrowdetailById(String fBorrowdetailid)
    {
        return fBorrowdetailMapper.deleteFBorrowdetailById(fBorrowdetailid);
    }
}
