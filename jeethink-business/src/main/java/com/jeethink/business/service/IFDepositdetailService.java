package com.jeethink.business.service;

import java.util.List;
import com.jeethink.business.domain.FDepositdetail;

/**
 * 案卷存入明细Service接口
 * 
 * @author yhb
 * @date 2020-07-24
 */
public interface IFDepositdetailService 
{
    /**
     * 查询案卷存入明细
     * 
     * @param fDepositdetailid 案卷存入明细ID
     * @return 案卷存入明细
     */
    public FDepositdetail selectFDepositdetailById(String fDepositdetailid);

    /**
     * 查询案卷存入明细列表
     * 
     * @param fDepositdetail 案卷存入明细
     * @return 案卷存入明细集合
     */
    public List<FDepositdetail> selectFDepositdetailList(FDepositdetail fDepositdetail);

    /**
     * 新增案卷存入明细
     * 
     * @param fDepositdetail 案卷存入明细
     * @return 结果
     */
    public int insertFDepositdetail(FDepositdetail fDepositdetail);

    /**
     * 修改案卷存入明细
     * 
     * @param fDepositdetail 案卷存入明细
     * @return 结果
     */
    public int updateFDepositdetail(FDepositdetail fDepositdetail);

    /**
     * 批量删除案卷存入明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFDepositdetailByIds(String ids);

    /**
     * 删除案卷存入明细信息
     * 
     * @param fDepositdetailid 案卷存入明细ID
     * @return 结果
     */
    public int deleteFDepositdetailById(String fDepositdetailid);
}
