package com.jeethink.business.service.impl;

import java.util.Date;
import java.util.List;

import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.business.domain.FDeposit;
import com.jeethink.common.extend.codeType;
import com.jeethink.common.extend.createId;
import com.jeethink.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.FCasesMapper;
import com.jeethink.business.domain.FCases;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.common.core.text.Convert;

/**
 * 案卷Service业务层处理
 *
 * @author yhb
 * @date 2020-07-23
 */
@Service
public class FCasesServiceImpl implements IFCasesService
{
    @Autowired
    private FCasesMapper fCasesMapper;
    @Autowired
    private IFLockerService fLockerService;
    @Autowired
    private IFPositionService fPositionService;

    /**
     * 查询案卷
     *
     * @param fId 案卷ID
     * @return 案卷
     */
    @Override
    public FCases selectFCasesById(String fId)
    {
        return fCasesMapper.selectFCasesById(fId);
    }

    /**
     * 查询案卷列表
     *
     * @param fCases 案卷
     * @return 案卷
     */
    @Override
    public List<FCases> selectFCasesList(FCases fCases)
    {
        return fCasesMapper.selectFCasesList(fCases);
    }

    /**
     * 新增案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    @Override
    public int insertFCases(FCases fCases)
    {
        //获取卷宗柜
        FLocker locker=fLockerService.selectFLockerById(fCases.getfLockerid());
        //获取货位
        FPosition position=fPositionService.selectFPositionById(fCases.getfPolice1id());
        //存入案卷信息
        fCases.setfId(createId.getID());
        fCases.setCreateTime(new Date());
        fCases.setfState(0);
        fCases.setfLockername(locker.getfLockername());
        fCases.setfPolice1name(position.getfPositionname());
        //存入主表信息
        FDeposit fDeposit=new FDeposit();
        fDeposit.setfDepositid(createId.getID());
        fDeposit.setfCode(createId.getCode(codeType.In));
        fDeposit.setfCreatedate(new Date());
        fDeposit.setfUserid(ShiroUtils.getUserId().toString());
        fDeposit.setfUsername(ShiroUtils.getLoginName());
        //记录开门的方式，暂时待定
        fDeposit.setfType(0);
        //设置存入记录的状态，不知道是不是保存就调用，暂时默认为已入库
        fDeposit.setfState(codeType.In.getId());
        //存入明细信息

        return fCasesMapper.insertFCases(fCases);

    }

    /**
     * 修改案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    @Override
    public int updateFCases(FCases fCases)
    {
        return fCasesMapper.updateFCases(fCases);
    }

    /**
     * 删除案卷对象
     *insertFCases
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFCasesByIds(String ids)
    {
        return fCasesMapper.deleteFCasesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除案卷信息
     *
     * @param fId 案卷ID
     * @return 结果
     */
    @Override
    public int deleteFCasesById(String fId)
    {
        return fCasesMapper.deleteFCasesById(fId);
    }
}
