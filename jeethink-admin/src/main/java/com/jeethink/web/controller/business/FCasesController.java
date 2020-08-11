package com.jeethink.web.controller.business;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeethink.common.annotation.Log;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.business.domain.FCases;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 案卷Controller
 *
 * @author yhb
 * @date 2020-07-23
 */
@Controller
@RequestMapping("/business/cases")
public class FCasesController extends BaseController
{
    private String prefix = "business/cases";

    @Autowired
    private IFCasesService fCasesService;

    @RequiresPermissions("business:cases:view")
    @GetMapping()
    public String cases()
    {
        return prefix + "/cases";
    }

    /**
     * 查询案卷列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FCases fCases)
    {
        startPage();
        fCases.setfState(1);
        List<FCases> list = fCasesService.selectFCasesList(fCases);
        return getDataTable(list);
    }
    /**
     * 查询入库申请的案卷
     */
    @PostMapping("/selectBydepositId")
    @ResponseBody
    public TableDataInfo selectBydepositId(String depositId){
        List<FCases> list=fCasesService.selectBydepositId(depositId);
        return getDataTable(list);
    }

    /**
     * 查询借阅的案卷
     */
    @PostMapping("/selectByborrowId")
    @ResponseBody
    public TableDataInfo selectByborrowId(String borrowId){
        List<FCases> list=fCasesService.selectByborrowId(borrowId);
        return getDataTable(list);
    }
    /**
     * 查询待归还的案卷
     */
    @PostMapping("/selectByOut")
    @ResponseBody
    public TableDataInfo selectByOut(String caseCode, String policeNo, String caseName){
        List<FCases> list=fCasesService.selectByOut(caseCode,policeNo,caseName);
        return getDataTable(list);
    }
    /**
     * 查询盘点明细
     */
    @PostMapping("/selectByCheckId")
    @ResponseBody
    public TableDataInfo selectByCheckId(String checkId){
        List<FCases> list=fCasesService.selectByCheckId(checkId);
        return getDataTable(list);
    }

    /**
     * 根据案卷柜Id查询在库案卷
     */
    @PostMapping("/selectByLockerId")
    @ResponseBody
    public TableDataInfo selectByLockerId(String lockerId){
        List<FCases> list=fCasesService.selectByLockerId(lockerId);
        return getDataTable(list);
    }
    /**
     * 查询可盘点信息
     */
    @PostMapping("/selectByCheckAndState")
    @ResponseBody
    public TableDataInfo selectByCheckAndState(String checkId){
        List<FCases> list=fCasesService.selectByCheckAndState(checkId);
        return getDataTable(list);
    }
    /**
     * 导出案卷列表
     */
    @Log(title = "案卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FCases fCases)
    {
        List<FCases> list = fCasesService.selectFCasesList(fCases);
        ExcelUtil<FCases> util = new ExcelUtil<FCases>(FCases.class);
        return util.exportExcel(list, "cases");
    }

    /**
     * 新增案卷
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /** 查询录入过的在区人员 */
    @GetMapping("/localform")
    public String localform()
    {
        return prefix + "/localform";
    }

    /**
     * 新增保存案卷
     */
    @Log(title = "案卷", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FCases fCases,String peopleType)
    {
        peopleType=peopleType.length()>2?peopleType.substring(0,1):peopleType;
        String msg=fCasesService.insertFCases(fCases,peopleType);
        return success("");
    }

    /**
     * 修改案卷
     */
    @GetMapping("/edit/{fId}")
    public String edit(@PathVariable("fId") String fId, ModelMap mmap)
    {
        FCases fCases = fCasesService.selectFCasesById(fId);
        mmap.put("fCases", fCases);
        return prefix + "/edit";
    }

    /**
     * 修改保存案卷
     */
    @RequiresPermissions("business:cases:edit")
    @Log(title = "案卷", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FCases fCases)
    {
        return toAjax(fCasesService.updateFCases(fCases));
    }

    /**
     * 删除案卷
     */
    @RequiresPermissions("business:cases:remove")
    @Log(title = "案卷", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fCasesService.deleteFCasesByIds(ids));
    }
}
