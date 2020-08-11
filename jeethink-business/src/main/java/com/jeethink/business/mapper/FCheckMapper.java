package com.jeethink.business.mapper;

import java.util.List;
import java.util.Map;

import com.jeethink.business.domain.FCheck;

/**
 * 盘点主表Mapper接口
 * 
 * @author yhb
 * @date 2020-08-07
 */
public interface FCheckMapper 
{
    /**
     * 查询盘点主表
     * 
     * @param fCheckid 盘点主表ID
     * @return 盘点主表
     */
    public FCheck selectFCheckById(String fCheckid);

    /**
     * 查询盘点主表列表
     * 
     * @param fCheck 盘点主表
     * @return 盘点主表集合
     */
    public List<FCheck> selectFCheckList(FCheck fCheck);

    /**
     * 新增盘点主表
     * 
     * @param fCheck 盘点主表
     * @return 结果
     */
    public int insertFCheck(FCheck fCheck);

    /**
     * 修改盘点主表
     * 
     * @param fCheck 盘点主表
     * @return 结果
     */
    public int updateFCheck(FCheck fCheck);

    /**
     * 更新主表状态
     * */
    public int updateState(Map map);

    /**
     * 删除盘点主表
     * 
     * @param fCheckid 盘点主表ID
     * @return 结果
     */
    public int deleteFCheckById(String fCheckid);

    /**
     * 批量删除盘点主表
     * 
     * @param fCheckids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFCheckByIds(String[] fCheckids);
}
