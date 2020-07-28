package com.jeethink.basicInfo.service;

import java.util.List;
import com.jeethink.basicInfo.domain.FCard;

/**
 * 卡Service接口
 * 
 * @author yhb
 * @date 2020-07-23
 */
public interface IFCardService 
{
    /**
     * 查询卡
     * 
     * @param fCardid 卡ID
     * @return 卡
     */
    public FCard selectFCardById(String fCardid);

    /**
     * 查询卡列表
     * 
     * @param fCard 卡
     * @return 卡集合
     */
    public List<FCard> selectFCardList(FCard fCard);
    /**
     * 验证卡是否可用
     * */
    public List<FCard> verificationCard(FCard fCard);

    /**
     * 新增卡
     * 
     * @param fCard 卡
     * @return 结果
     */
    public int insertFCard(FCard fCard);

    /**
     * 修改卡
     * 
     * @param fCard 卡
     * @return 结果
     */
    public int updateFCard(FCard fCard);

    /**
     * 批量删除卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFCardByIds(String ids);

    /**
     * 删除卡信息
     * 
     * @param fCardid 卡ID
     * @return 结果
     */
    public int deleteFCardById(String fCardid);
}
