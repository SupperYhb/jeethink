package com.jeethink.basicInfo.mapper;

import java.util.List;
import com.jeethink.basicInfo.domain.FPosition;

/**
 * 货位Mapper接口
 * 
 * @author yhb
 * @date 2020-07-21
 */
public interface FPositionMapper 
{
    /**
     * 查询货位
     * 
     * @param fPositionid 货位ID
     * @return 货位
     */
    public FPosition selectFPositionById(String fPositionid);

    /**
     * 查询货位列表
     * 
     * @param fPosition 货位
     * @return 货位集合
     */
    public List<FPosition> selectFPositionList(FPosition fPosition);

    /**
     * 新增货位
     * 
     * @param fPosition 货位
     * @return 结果
     */
    public int insertFPosition(FPosition fPosition);

    /**
     * 修改货位
     * 
     * @param fPosition 货位
     * @return 结果
     */
    public int updateFPosition(FPosition fPosition);

    /**
     * 删除货位
     * 
     * @param fPositionid 货位ID
     * @return 结果
     */
    public int deleteFPositionById(String fPositionid);

    /**
     * 批量删除货位
     * 
     * @param fPositionids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFPositionByIds(String[] fPositionids);
}
