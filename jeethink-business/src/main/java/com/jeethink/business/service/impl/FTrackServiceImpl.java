package com.jeethink.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FTrackMapper;
import com.jeethink.business.domain.FTrack;
import com.jeethink.business.service.IFTrackService;
import com.jeethink.common.core.text.Convert;

/**
 * 案卷轨迹Service业务层处理
 * 
 * @author yhb
 * @date 2020-08-03
 */
@Service
public class FTrackServiceImpl implements IFTrackService 
{
    @Autowired
    private FTrackMapper fTrackMapper;

    /**
     * 查询案卷轨迹
     * 
     * @param fTrackid 案卷轨迹ID
     * @return 案卷轨迹
     */
    @Override
    public FTrack selectFTrackById(String fTrackid)
    {
        return fTrackMapper.selectFTrackById(fTrackid);
    }

    /**
     * 查询案卷轨迹列表
     * 
     * @param fTrack 案卷轨迹
     * @return 案卷轨迹
     */
    @Override
    public List<FTrack> selectFTrackList(FTrack fTrack)
    {
        return fTrackMapper.selectFTrackList(fTrack);
    }

    /**
     * 根据案卷编号查询轨迹列表
     * */
    @Override
    public List<FTrack> selectFtrackByCaseCode(String caseCode) {
        return fTrackMapper.selectFtrackByCaseCode(caseCode);
    }

    /**
     * 新增案卷轨迹
     * 
     * @param fTrack 案卷轨迹
     * @return 结果
     */
    @Override
    public int insertFTrack(FTrack fTrack)
    {
        return fTrackMapper.insertFTrack(fTrack);
    }

    /**
     * 修改案卷轨迹
     * 
     * @param fTrack 案卷轨迹
     * @return 结果
     */
    @Override
    public int updateFTrack(FTrack fTrack)
    {
        return fTrackMapper.updateFTrack(fTrack);
    }

    /**
     * 删除案卷轨迹对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFTrackByIds(String ids)
    {
        return fTrackMapper.deleteFTrackByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案卷轨迹信息
     * 
     * @param fTrackid 案卷轨迹ID
     * @return 结果
     */
    @Override
    public int deleteFTrackById(String fTrackid)
    {
        return fTrackMapper.deleteFTrackById(fTrackid);
    }
}
