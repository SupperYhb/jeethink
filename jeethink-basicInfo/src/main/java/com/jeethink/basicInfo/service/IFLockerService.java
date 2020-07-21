package com.jeethink.basicInfo.service;

import java.util.List;
import com.jeethink.basicInfo.domain.FLocker;

/**
 * 卷宗柜Service接口
 * 
 * @author yhb
 * @date 2020-07-21
 */
public interface IFLockerService 
{
    /**
     * 查询卷宗柜
     * 
     * @param fLockerid 卷宗柜ID
     * @return 卷宗柜
     */
    public FLocker selectFLockerById(String fLockerid);

    /**
     * 查询卷宗柜列表
     * 
     * @param fLocker 卷宗柜
     * @return 卷宗柜集合
     */
    public List<FLocker> selectFLockerList(FLocker fLocker);

    /**
     * 新增卷宗柜
     * insertFLocker
     * @param fLocker 卷宗柜
     * @return 结果
     */
    public int insertFLocker(FLocker fLocker);

    /**
     * 修改卷宗柜
     * 
     * @param fLocker 卷宗柜
     * @return 结果
     */
    public int updateFLocker(FLocker fLocker);

    /**
     * 批量删除卷宗柜
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFLockerByIds(String ids);

    /**
     * 删除卷宗柜信息
     * 
     * @param fLockerid 卷宗柜ID
     * @return 结果
     */
    public int deleteFLockerById(String fLockerid);
}
