package com.jeethink.business.mapper;

import java.util.List;
import com.jeethink.business.domain.FTrack;

/**
 * 案卷轨迹Mapper接口
 * 
 * @author yhb
 * @date 2020-08-03
 */
public interface FTrackMapper 
{
    /**
     * 查询案卷轨迹
     * 
     * @param fTrackid 案卷轨迹ID
     * @return 案卷轨迹
     */
    public FTrack selectFTrackById(String fTrackid);

    /**
     * 查询案卷轨迹列表
     * 
     * @param fTrack 案卷轨迹
     * @return 案卷轨迹集合
     */
    public List<FTrack> selectFTrackList(FTrack fTrack);

    /**
     * 根据案卷编号查询轨迹列表
     * */
    public List<FTrack> selectFtrackByCaseCode(String caseCode);

    /**
     * 新增案卷轨迹
     * 
     * @param fTrack 案卷轨迹
     * @return 结果
     */
    public int insertFTrack(FTrack fTrack);

    /**
     * 修改案卷轨迹
     * 
     * @param fTrack 案卷轨迹
     * @return 结果
     */
    public int updateFTrack(FTrack fTrack);

    /**
     * 删除案卷轨迹
     * 
     * @param fTrackid 案卷轨迹ID
     * @return 结果
     */
    public int deleteFTrackById(String fTrackid);

    /**
     * 批量删除案卷轨迹
     * 
     * @param fTrackids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFTrackByIds(String[] fTrackids);
}
