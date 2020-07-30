package com.jeethink.business.mapper;

import java.util.List;
import com.jeethink.business.domain.FBorrow;

/**
 * 借阅Mapper接口
 * 
 * @author yhb
 * @date 2020-07-28
 */
public interface FBorrowMapper 
{
    /**
     * 查询借阅
     * 
     * @param fBorrowid 借阅ID
     * @return 借阅
     */
    public FBorrow selectFBorrowById(String fBorrowid);

    /**
     * 查询借阅列表
     * 
     * @param fBorrow 借阅
     * @return 借阅集合
     */
    public List<FBorrow> selectFBorrowList(FBorrow fBorrow);

    /**
     * 新增借阅
     * 
     * @param fBorrow 借阅
     * @return 结果
     */
    public int insertFBorrow(FBorrow fBorrow);

    /**
     * 修改借阅
     * 
     * @param fBorrow 借阅
     * @return 结果
     */
    public int updateFBorrow(FBorrow fBorrow);

    /**
     * 删除借阅
     * 
     * @param fBorrowid 借阅ID
     * @return 结果
     */
    public int deleteFBorrowById(String fBorrowid);

    /**
     * 批量删除借阅
     * 
     * @param fBorrowids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFBorrowByIds(String[] fBorrowids);
}
