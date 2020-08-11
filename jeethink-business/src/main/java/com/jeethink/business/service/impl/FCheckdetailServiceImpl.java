package com.jeethink.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FCheckdetailMapper;
import com.jeethink.business.domain.FCheckdetail;
import com.jeethink.business.service.IFCheckdetailService;
import com.jeethink.common.core.text.Convert;

/**
 * 盘点明细Service业务层处理
 * 
 * @author yhb
 * @date 2020-08-07
 */
@Service
public class FCheckdetailServiceImpl implements IFCheckdetailService 
{
    @Autowired
    private FCheckdetailMapper fCheckdetailMapper;

    /**
     * 查询盘点明细
     * 
     * @param fCheckdetailid 盘点明细ID
     * @return 盘点明细
     */
    @Override
    public FCheckdetail selectFCheckdetailById(String fCheckdetailid)
    {
        return fCheckdetailMapper.selectFCheckdetailById(fCheckdetailid);
    }

    /**
     * 查询盘点明细列表
     * 
     * @param fCheckdetail 盘点明细
     * @return 盘点明细
     */
    @Override
    public List<FCheckdetail> selectFCheckdetailList(FCheckdetail fCheckdetail)
    {
        return fCheckdetailMapper.selectFCheckdetailList(fCheckdetail);
    }

    /**
     * 新增盘点明细
     * 
     * @param fCheckdetail 盘点明细
     * @return 结果
     */
    @Override
    public int insertFCheckdetail(FCheckdetail fCheckdetail)
    {
        return fCheckdetailMapper.insertFCheckdetail(fCheckdetail);
    }

    /**
     * 修改盘点明细
     * 
     * @param fCheckdetail 盘点明细
     * @return 结果
     */
    @Override
    public int updateFCheckdetail(FCheckdetail fCheckdetail)
    {
        return fCheckdetailMapper.updateFCheckdetail(fCheckdetail);
    }

    /**
     * 删除盘点明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFCheckdetailByIds(String ids)
    {
        return fCheckdetailMapper.deleteFCheckdetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除盘点明细信息
     * 
     * @param fCheckdetailid 盘点明细ID
     * @return 结果
     */
    @Override
    public int deleteFCheckdetailById(String fCheckdetailid)
    {
        return fCheckdetailMapper.deleteFCheckdetailById(fCheckdetailid);
    }
}
