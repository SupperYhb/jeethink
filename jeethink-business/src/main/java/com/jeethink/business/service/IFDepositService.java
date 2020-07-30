package com.jeethink.business.service;

import java.util.List;

import com.jeethink.business.domain.FCases;
import com.jeethink.business.domain.FDeposit;
import com.jeethink.requestutil.entity.kdcaseentity;

/**
 * 存放案卷Service接口
 * 
 * @author yhb
 * @date 2020-07-22
 */
public interface IFDepositService 
{
    /**
     * 查询存放案卷
     * 
     * @param fDepositid 存放案卷ID
     * @return 存放案卷
     */
    public FDeposit selectFDepositById(String fDepositid);

    /**
     * 查询存放案卷列表
     * 
     * @param fDeposit 存放案卷
     * @return 存放案卷集合
     */
    public List<FDeposit> selectFDepositList(FDeposit fDeposit);
    /**
     * 获取科达接口案卷数据
     * */
    public List<kdcaseentity> getkdCase();
    /**
     * 添加入库信息（平台拉取）
     * */
    public int addCaseIn(List<kdcaseentity> list,String lockerId,String positionId,String cardCode,String cardId,String remark);
    /**
     * 添加归还信息
     * */
    public int addCaseReturn(List<FCases> list, String lockerId, String positionId, String cardCode, String cardId, String remark);
    /**
     * 新增存放案卷
     * 
     * @param fDeposit 存放案卷
     * @return 结果
     */
    public int insertFDeposit(FDeposit fDeposit);

    /**
     * 修改存放案卷
     * 
     * @param fDeposit 存放案卷
     * @return 结果
     */
    public int updateFDeposit(FDeposit fDeposit);

    /**
     * 批量删除存放案卷
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFDepositByIds(String ids);

    /**
     * 删除存放案卷信息
     * 
     * @param fDepositid 存放案卷ID
     * @return 结果
     */
    public int deleteFDepositById(String fDepositid);
}
