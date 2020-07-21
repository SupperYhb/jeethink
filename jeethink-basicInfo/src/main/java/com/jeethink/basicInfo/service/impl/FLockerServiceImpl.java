package com.jeethink.basicInfo.service.impl;

import java.util.List;

import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.mapper.FPositionMapper;
import com.jeethink.common.extend.createId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.basicInfo.mapper.FLockerMapper;
import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.common.core.text.Convert;

/**
 * 卷宗柜Service业务层处理
 * 
 * @author yhb
 * @date 2020-07-21
 */
@Service
public class FLockerServiceImpl implements IFLockerService 
{
    @Autowired
    private FLockerMapper fLockerMapper;

    @Autowired
    private FPositionMapper fPositionMapper;

    /**
     * 查询卷宗柜
     * 
     * @param fLockerid 卷宗柜ID
     * @return 卷宗柜
     */
    @Override
    public FLocker selectFLockerById(String fLockerid)
    {
        return fLockerMapper.selectFLockerById(fLockerid);
    }

    /**
     * 查询卷宗柜列表
     * 
     * @param fLocker 卷宗柜
     * @return 卷宗柜
     */
    @Override
    public List<FLocker> selectFLockerList(FLocker fLocker)
    {
        return fLockerMapper.selectFLockerList(fLocker);
    }

    /**
     * 新增卷宗柜
     * 
     * @param fLocker 卷宗柜
     * @return 结果
     */
    @Override
    public int insertFLocker(FLocker fLocker)
    {
       for(int i=1;i<=fLocker.getfCount();i++)
       {
           FPosition position=new FPosition();
           position.setfPositionid(createId.getID());
           position.setfPositioncode(i+"");
           position.setfPositionname(i+"");
           position.setfLockerid(fLocker.getfLockerid());
           fPositionMapper.insertFPosition(position);
       }
        return fLockerMapper.insertFLocker(fLocker);
    }

    /**
     * 修改卷宗柜
     * 
     * @param fLocker 卷宗柜
     * @return 结果
     */
    @Override
    public int updateFLocker(FLocker fLocker)
    {
        return fLockerMapper.updateFLocker(fLocker);
    }

    /**
     * 删除卷宗柜对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFLockerByIds(String ids)
    {
        return fLockerMapper.deleteFLockerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除卷宗柜信息
     * 
     * @param fLockerid 卷宗柜ID
     * @return 结果
     */
    @Override
    public int deleteFLockerById(String fLockerid)
    {
        return fLockerMapper.deleteFLockerById(fLockerid);
    }
}
