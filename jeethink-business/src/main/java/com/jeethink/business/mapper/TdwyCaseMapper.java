package com.jeethink.business.mapper;

import java.util.List;
import java.util.Map;

import com.jeethink.business.domain.TdwyCase;

/**
 * 天地伟业案卷Mapper接口
 * 
 * @author yhb
 * @date 2021-02-05
 */
public interface TdwyCaseMapper 
{
    /**
     * 查询天地伟业案卷
     * 
     * @param sId 天地伟业案卷ID
     * @return 天地伟业案卷
     */
    public TdwyCase selectTdwyCaseById(String sId);

    /**
     * 查询天地伟业案卷列表
     * 
     * @param tdwyCase 天地伟业案卷
     * @return 天地伟业案卷集合
     */
    public List<TdwyCase> selectTdwyCaseList(TdwyCase tdwyCase);

    /**
     * 新增天地伟业案卷
     * 
     * @param tdwyCase 天地伟业案卷
     * @return 结果
     */
    public int insertTdwyCase(TdwyCase tdwyCase);

    /**
     * 修改天地伟业案卷
     * 
     * @param tdwyCase 天地伟业案卷
     * @return 结果
     */
    public int updateTdwyCase(TdwyCase tdwyCase);

    /**
     * 删除天地伟业案卷
     * 
     * @param sId 天地伟业案卷ID
     * @return 结果
     */
    public int deleteTdwyCaseById(String sId);

    /**
     * 批量删除天地伟业案卷
     * 
     * @param sIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTdwyCaseByIds(String[] sIds);

    /**
     * 查询天地伟业案卷
     * */
    public List<TdwyCase> selectTdwyCaseLists(Map map);
}
