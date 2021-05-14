package com.jeethink.business.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jeethink.business.domain.FCases;
import com.jeethink.business.mapper.FCasesMapper;
import com.jeethink.requestutil.entity.kdcaseentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.business.mapper.TdwyCaseMapper;
import com.jeethink.business.domain.TdwyCase;
import com.jeethink.business.service.ITdwyCaseService;
import com.jeethink.common.core.text.Convert;

/**
 * 天地伟业案卷Service业务层处理
 * 
 * @author yhb
 * @date 2021-02-05
 */
@Service
public class TdwyCaseServiceImpl implements ITdwyCaseService 
{
    @Autowired
    private TdwyCaseMapper tdwyCaseMapper;
    @Autowired
    private FCasesMapper fCasesMapper;

    /**
     * 查询天地伟业案卷
     * 
     * @param sId 天地伟业案卷ID
     * @return 天地伟业案卷
     */
    @Override
    public TdwyCase selectTdwyCaseById(String sId)
    {
        return tdwyCaseMapper.selectTdwyCaseById(sId);
    }

    /**
     * 查询天地伟业案卷列表
     * 
     * @param tdwyCase 天地伟业案卷
     * @return 天地伟业案卷
     */
    @Override
    public List<TdwyCase> selectTdwyCaseList(TdwyCase tdwyCase)
    {
        return tdwyCaseMapper.selectTdwyCaseList(tdwyCase);
    }

    /**
     * 新增天地伟业案卷
     * 
     * @param tdwyCase 天地伟业案卷
     * @return 结果
     */
    @Override
    public int insertTdwyCase(TdwyCase tdwyCase)
    {
        return tdwyCaseMapper.insertTdwyCase(tdwyCase);
    }

    /**
     * 修改天地伟业案卷
     * 
     * @param tdwyCase 天地伟业案卷
     * @return 结果
     */
    @Override
    public int updateTdwyCase(TdwyCase tdwyCase)
    {
        return tdwyCaseMapper.updateTdwyCase(tdwyCase);
    }

    /**
     * 删除天地伟业案卷对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTdwyCaseByIds(String ids)
    {
        return tdwyCaseMapper.deleteTdwyCaseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除天地伟业案卷信息
     * 
     * @param sId 天地伟业案卷ID
     * @return 结果
     */
    @Override
    public int deleteTdwyCaseById(String sId)
    {
        return tdwyCaseMapper.deleteTdwyCaseById(sId);
    }

    /**
     * 查询天地伟业案卷
     * */
    @Override
    public List<TdwyCase> selectTdwyCaseList(Map map) {
        List<FCases> casesList =fCasesMapper.selectFCasesList(null);
        List<TdwyCase> list=tdwyCaseMapper.selectTdwyCaseLists(map);
        List<TdwyCase> resultList=list.stream().filter(TdwyCase->casesList.stream().noneMatch(cases->cases.getfCasecode().equals(TdwyCase.getAjbh()))).collect(Collectors.toList());
        return resultList;
    }
}
