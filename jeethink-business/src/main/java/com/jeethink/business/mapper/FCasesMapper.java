package com.jeethink.business.mapper;

import java.util.List;

import com.jeethink.business.domain.FCases;

/**
 * 案卷Mapper接口
 *
 * @author yhb
 * @date 2020-07-23
 */
public interface FCasesMapper
{
    /**
     * 查询案卷
     *
     * @param fId 案卷ID
     * @return 案卷
     */
    public FCases selectFCasesById(String fId);

    /**
     * 查询案卷列表
     *
     * @param fCases 案卷
     * @return 案卷集合
     */
    public List<FCases> selectFCasesList(FCases fCases);
    /**
     * 查询入库的案卷
     * */
    public List<FCases> selectBydepositId(String depositId);

    /**
     * 查询借阅明细
     * */
    public List<FCases> selectByborrowId(String borrowId);

    /**
     * 根据卷宗柜Id查询在库案卷
     * */
    public List<FCases> selectByLockerId(String lockerId);

    /**
     * 查询盘点明细
     * */
    public List<FCases> selectByCheckId(String checkId);

    /**
     * 查询可盘点信息
     * */
    public List<FCases> selectByCheckAndState(String checkId);

    /**
     * 查询超期借阅列表
     * */
    public List<FCases> selectOverdueList(String overTimeCount);

    /**
     * 新增案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    public int insertFCases(FCases fCases);

    /**
     * 修改案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    public int updateFCases(FCases fCases);

    /**
     * 删除案卷
     *
     * @param fId 案卷ID
     * @return 结果
     */
    public int deleteFCasesById(String fId);

    /**
     * 批量删除案卷
     *
     * @param fIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFCasesByIds(String[] fIds);
}
