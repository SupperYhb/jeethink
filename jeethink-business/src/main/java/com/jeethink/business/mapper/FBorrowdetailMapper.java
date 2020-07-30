package com.jeethink.business.mapper;

import java.util.List;
import com.jeethink.business.domain.FBorrowdetail;

/**
 * 取出明细Mapper接口
 * 
 * @author yhb
 * @date 2020-07-28
 */
public interface FBorrowdetailMapper 
{
    /**
     * 查询取出明细
     * 
     * @param fBorrowdetailid 取出明细ID
     * @return 取出明细
     */
    public FBorrowdetail selectFBorrowdetailById(String fBorrowdetailid);

    /**
     * 查询取出明细列表
     * 
     * @param fBorrowdetail 取出明细
     * @return 取出明细集合
     */
    public List<FBorrowdetail> selectFBorrowdetailList(FBorrowdetail fBorrowdetail);

    /**
     * 新增取出明细
     * 
     * @param fBorrowdetail 取出明细
     * @return 结果
     */
    public int insertFBorrowdetail(FBorrowdetail fBorrowdetail);

    /**
     * 修改取出明细
     * 
     * @param fBorrowdetail 取出明细
     * @return 结果
     */
    public int updateFBorrowdetail(FBorrowdetail fBorrowdetail);

    /**
     * 删除取出明细
     * 
     * @param fBorrowdetailid 取出明细ID
     * @return 结果
     */
    public int deleteFBorrowdetailById(String fBorrowdetailid);

    /**
     * 批量删除取出明细
     * 
     * @param fBorrowdetailids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFBorrowdetailByIds(String[] fBorrowdetailids);
}
