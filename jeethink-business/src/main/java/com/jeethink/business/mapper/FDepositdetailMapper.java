package com.jeethink.business.mapper;

import java.util.List;
import com.jeethink.business.domain.FDepositdetail;

/**
 * 案卷存入明细Mapper接口
 * 
 * @author yhb
 * @date 2020-07-24
 */
public interface FDepositdetailMapper 
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
     * 删除案卷存入明细
     * 
     * @param fDepositdetailid 案卷存入明细ID
     * @return 结果
     */
    public int deleteFDepositdetailById(String fDepositdetailid);

    /**
     * 批量删除案卷存入明细
     * 
     * @param fDepositdetailids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFDepositdetailByIds(String[] fDepositdetailids);
}
