package com.jeethink.basicInfo.service.impl;

import java.util.Date;
import java.util.List;

import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.basicInfo.mapper.FCardMapper;
import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.common.core.text.Convert;

/**
 * 卡Service业务层处理
 * 
 * @author yhb
 * @date 2020-07-23
 */
@Service
public class FCardServiceImpl implements IFCardService 
{
    @Autowired
    private FCardMapper fCardMapper;

    /**
     * 查询卡
     * 
     * @param fCardid 卡ID
     * @return 卡
     */
    @Override
    public FCard selectFCardById(String fCardid)
    {
        return fCardMapper.selectFCardById(fCardid);
    }

    /**
     * 查询卡列表
     * 
     * @param fCard 卡
     * @return 卡
     */
    @Override
    public List<FCard> selectFCardList(FCard fCard)
    {
        return fCardMapper.selectFCardList(fCard);
    }

    /**
     * 验证卡是否可用
     * */
    @Override
    public List<FCard> verificationCard(FCard fCard) {
        return fCardMapper.verificationCard(fCard);
    }

    /**
     * 根据用户查询使用的卡
     * */
    @Override
    public List<FCard> selectCardByUserId(String fUserId) {
        return fCardMapper.selectCardByUserId(fUserId);
    }

    /**
     * 新增卡
     * 
     * @param fCard 卡
     * @return 结果
     */
    @Override
    public int insertFCard(FCard fCard)
    {
        fCard.setfCardid(createId.getID());
        fCard.setfCreatedate(new Date());
        fCard.setfCreateuserid(ShiroUtils.getUserId().toString());
        fCard.setfCreateusername(ShiroUtils.getLoginName());
        return fCardMapper.insertFCard(fCard);
    }

    /**
     * 修改卡
     * 
     * @param fCard 卡
     * @return 结果
     */
    @Override
    public int updateFCard(FCard fCard)
    {
        if("0".equals(fCard.getfState())) {
            fCard.setfUsername("");
            fCard.setfUserid("");
        }
        return fCardMapper.updateFCard(fCard);
    }

    /**
     * 删除卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFCardByIds(String ids)
    {
        return fCardMapper.deleteFCardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除卡信息
     * 
     * @param fCardid 卡ID
     * @return 结果
     */
    @Override
    public int deleteFCardById(String fCardid)
    {
        return fCardMapper.deleteFCardById(fCardid);
    }
}
