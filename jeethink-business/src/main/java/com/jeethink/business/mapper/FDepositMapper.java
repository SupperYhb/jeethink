package com.jeethink.business.mapper;

import java.util.List;
import com.jeethink.business.domain.FDeposit;

/**
 * 存放案卷Mapper接口
 * 
 * @author yhb
 * @date 2020-07-22
 */
public interface FDepositMapper 
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
     * 删除存放案卷
     * 
     * @param fDepositid 存放案卷ID
     * @return 结果
     */
    public int deleteFDepositById(String fDepositid);

    /**
     * 批量删除存放案卷
     * 
     * @param fDepositids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFDepositByIds(String[] fDepositids);
}
