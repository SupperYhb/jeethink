package com.jeethink.basicInfo.service.impl;

import java.util.List;

import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.domain.saveBoxEntity;
import com.jeethink.basicInfo.mapper.FPositionMapper;
import com.jeethink.common.extend.createId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.basicInfo.mapper.FBoxMapper;
import com.jeethink.basicInfo.domain.FBox;
import com.jeethink.basicInfo.service.IFBoxService;
import com.jeethink.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 箱体Service业务层处理
 * 
 * @author yhb
 * @date 2020-08-31
 */
@Service
public class FBoxServiceImpl implements IFBoxService 
{
    @Autowired
    private FBoxMapper fBoxMapper;
    @Autowired
    private FPositionMapper fPositionMapper;

    /**
     * 查询箱体
     * 
     * @param fBoxid 箱体ID
     * @return 箱体
     */
    @Override
    public FBox selectFBoxById(String fBoxid)
    {
        return fBoxMapper.selectFBoxById(fBoxid);
    }

    /**
     * 查询箱体列表
     * 
     * @param fBox 箱体
     * @return 箱体
     */
    @Override
    public List<FBox> selectFBoxList(FBox fBox)
    {
        return fBoxMapper.selectFBoxList(fBox);
    }

    /**
     * 新增箱体
     * 
     * @param fBox 箱体
     * @return 结果
     */
    @Override
    public int insertFBox(FBox fBox)
    {
        return fBoxMapper.insertFBox(fBox);
    }

    /**
     * 修改箱体
     * 
     * @param fBox 箱体
     * @return 结果
     */
    @Override
    public int updateFBox(FBox fBox)
    {
        return fBoxMapper.updateFBox(fBox);
    }

    /**
     * 删除箱体对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFBoxByIds(String ids)
    {
        return fBoxMapper.deleteFBoxByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除箱体信息
     * 
     * @param fBoxid 箱体ID
     * @return 结果
     */
    @Override
    public int deleteFBoxById(String fBoxid)
    {
        return fBoxMapper.deleteFBoxById(fBoxid);
    }

    /**
     * 设置箱体信息
     * */
    @Override
    @Transactional
    public int setBox(List<saveBoxEntity> list) {
        int count=1;
        //初步实现保存，不做其他删除业务校验
        for ( saveBoxEntity entity: list
             ) {
            String lockerid=entity.getLockerId().substring(1,entity.getLockerId().length());
            lockerid=lockerid.substring(0,lockerid.length()-1);
            //存入箱体
            FBox box=new FBox();
            box.setfBoxid(createId.getID());
            box.setfBoxcode(entity.getSort());
            box.setfLockerid(lockerid);
            box.setfCount(Integer.parseInt(entity.getBoxCount()));
            box.setfSort(Integer.parseInt(entity.getSort()));
            fBoxMapper.insertFBox(box);
            //存入货位信息
            for(int i=1;i<=Integer.parseInt(entity.getBoxCount());i++)
            {
                FPosition position=new FPosition();
                position.setfPositionid(createId.getID());
                position.setfLockerid(lockerid);
                position.setfPositionname(count+"");
                position.setfPositioncode(count+"");
                position.setfBoxid(box.getfBoxid());
                fPositionMapper.insertFPosition(position);
                count++;
            }
        }
        return 0;
    }
}
