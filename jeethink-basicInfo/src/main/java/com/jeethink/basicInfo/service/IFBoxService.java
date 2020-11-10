package com.jeethink.basicInfo.service;

import java.util.List;
import com.jeethink.basicInfo.domain.FBox;
import com.jeethink.basicInfo.domain.saveBoxEntity;

/**
 * 箱体Service接口
 * 
 * @author yhb
 * @date 2020-08-31
 */
public interface IFBoxService 
{
    /**
     * 查询箱体
     * 
     * @param fBoxid 箱体ID
     * @return 箱体
     */
    public FBox selectFBoxById(String fBoxid);

    /**
     * 查询箱体列表
     * 
     * @param fBox 箱体
     * @return 箱体集合
     */
    public List<FBox> selectFBoxList(FBox fBox);

    /**
     * 新增箱体
     * 
     * @param fBox 箱体
     * @return 结果
     */
    public int insertFBox(FBox fBox);

    /**
     * 修改箱体
     * 
     * @param fBox 箱体
     * @return 结果
     */
    public int updateFBox(FBox fBox);

    /**
     * 批量删除箱体
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFBoxByIds(String ids);

    /**
     * 删除箱体信息
     * 
     * @param fBoxid 箱体ID
     * @return 结果
     */
    public int deleteFBoxById(String fBoxid);

    /**
     * 设置箱体信息
     * */
    public int setBox(List<saveBoxEntity> list);
}
