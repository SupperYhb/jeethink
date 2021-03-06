package com.jeethink.business.service;

import java.util.List;
import com.jeethink.business.domain.FCheckdetail;

/**
 * 盘点明细Service接口
 * 
 * @author yhb
 * @date 2020-08-07
 */
public interface IFCheckdetailService 
{
    /**
     * 查询盘点明细
     * 
     * @param fCheckdetailid 盘点明细ID
     * @return 盘点明细
     */
    public FCheckdetail selectFCheckdetailById(String fCheckdetailid);

    /**
     * 查询盘点明细列表
     * 
     * @param fCheckdetail 盘点明细
     * @return 盘点明细集合
     */
    public List<FCheckdetail> selectFCheckdetailList(FCheckdetail fCheckdetail);

    /**
     * 新增盘点明细
     * 
     * @param fCheckdetail 盘点明细
     * @return 结果
     */
    public int insertFCheckdetail(FCheckdetail fCheckdetail);

    /**
     * 修改盘点明细
     * 
     * @param fCheckdetail 盘点明细
     * @return 结果
     */
    public int updateFCheckdetail(FCheckdetail fCheckdetail);

    /**
     * 批量删除盘点明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFCheckdetailByIds(String ids);

    /**
     * 删除盘点明细信息
     * 
     * @param fCheckdetailid 盘点明细ID
     * @return 结果
     */
    public int deleteFCheckdetailById(String fCheckdetailid);
}
