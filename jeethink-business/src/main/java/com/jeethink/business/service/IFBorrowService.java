package com.jeethink.business.service;

import java.util.List;

import com.jeethink.basicInfo.domain.FCamera;
import com.jeethink.business.domain.FBorrow;
import com.jeethink.business.domain.FCases;

/**
 * 借阅Service接口
 * 
 * @author yhb
 * @date 2020-07-28
 */
public interface IFBorrowService 
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
     * 根据id查询时间及摄像头信息
     * */
    public FCamera getFcameraById(String borrowId);
    /**
     * 借阅案卷
     * */
    String outCase(List<FCases> list, String cardCode, String cardId, String remark,String peopleType,String policeAccount,String policeName);

    /**
     * 新增借阅
     * 
     * @param fBorrow 借阅
     * @return 结果
     */
    public int insertFBorrow(FBorrow fBorrow);

    /**
     * 再次打开货位
     * */
    String OpenBox(String id,String type);

    /**
     * 修改借阅
     * 
     * @param fBorrow 借阅
     * @return 结果
     */
    public int updateFBorrow(FBorrow fBorrow);

    /**
     * 批量删除借阅
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFBorrowByIds(String ids);

    /**
     * 删除借阅信息
     * 
     * @param fBorrowid 借阅ID
     * @return 结果
     */
    public int deleteFBorrowById(String fBorrowid);
}
