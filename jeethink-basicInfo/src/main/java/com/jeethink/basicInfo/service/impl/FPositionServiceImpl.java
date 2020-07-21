package com.jeethink.basicInfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.basicInfo.mapper.FPositionMapper;
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.common.core.text.Convert;

/**
 * 货位Service业务层处理
 * 
 * @author yhb
 * @date 2020-07-21
 */
@Service
public class FPositionServiceImpl implements IFPositionService 
{
    @Autowired
    private FPositionMapper fPositionMapper;

    /**
     * 查询货位
     * 
     * @param fPositionid 货位ID
     * @return 货位
     */
    @Override
    public FPosition selectFPositionById(String fPositionid)
    {
        return fPositionMapper.selectFPositionById(fPositionid);
    }

    /**
     * 查询货位列表
     * 
     * @param fPosition 货位
     * @return 货位
     */
    @Override
    public List<FPosition> selectFPositionList(FPosition fPosition)
    {
        return fPositionMapper.selectFPositionList(fPosition);
    }

    /**
     * 新增货位
     * 
     * @param fPosition 货位
     * @return 结果
     */
    @Override
    public int insertFPosition(FPosition fPosition)
    {
        return fPositionMapper.insertFPosition(fPosition);
    }

    /**
     * 修改货位
     * 
     * @param fPosition 货位
     * @return 结果
     */
    @Override
    public int updateFPosition(FPosition fPosition)
    {
        return fPositionMapper.updateFPosition(fPosition);
    }

    /**
     * 删除货位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFPositionByIds(String ids)
    {
        return fPositionMapper.deleteFPositionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除货位信息
     * 
     * @param fPositionid 货位ID
     * @return 结果
     */
    @Override
    public int deleteFPositionById(String fPositionid)
    {
        return fPositionMapper.deleteFPositionById(fPositionid);
    }
}
