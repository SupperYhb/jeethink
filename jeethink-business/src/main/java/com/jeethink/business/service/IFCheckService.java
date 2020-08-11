package com.jeethink.business.service;

import java.util.List;
import java.util.Map;

import com.jeethink.business.domain.FCheck;

/**
 * 盘点主表Service接口
 * 
 * @author yhb
 * @date 2020-08-07
 */
public interface IFCheckService 
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
    /** 添加盘点 */
    public String addCheck(String lockerId,String name);
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
    public int updateStates(String fCheckId);

    /**
     * 盘点明细
     * */
    public String checkCase(String state,String ids,String remark);

    /**
     * 批量删除盘点主表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFCheckByIds(String ids);

    /**
     * 删除盘点主表信息
     * 
     * @param fCheckid 盘点主表ID
     * @return 结果
     */
    public int deleteFCheckById(String fCheckid);
}
