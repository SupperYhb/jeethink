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
