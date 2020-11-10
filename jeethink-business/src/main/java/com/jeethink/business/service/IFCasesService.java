package com.jeethink.business.service;

import java.util.List;

import com.jeethink.business.domain.FCases;

/**
 * 案卷Service接口
 *
 * @author yhb
 * @date 2020-07-23
 */
public interface IFCasesService
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
     * 查询可归还的数据
     * */
    public List<FCases> selectByOut(String caseCode,String policeNo,String caseName);

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
    public List<FCases> selectOverdueList();
    /**
     * 新增案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    public String insertFCases(FCases fCases,String peopleType);

    /**
     * 修改案卷
     *
     * @param fCases 案卷
     * @return 结果
     */
    public int updateFCases(FCases fCases);

    /**
     * 批量删除案卷
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFCasesByIds(String ids);

    /**
     * 删除案卷信息
     *
     * @param fId 案卷ID
     * @return 结果
     */
    public int deleteFCasesById(String fId);
}
