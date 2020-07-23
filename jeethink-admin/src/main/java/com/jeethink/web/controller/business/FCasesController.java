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
    @RequiresPermissions("business:cases:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FCases fCases)
    {
        startPage();
        List<FCases> list = fCasesService.selectFCasesList(fCases);
        return getDataTable(list);
    }

    /**
     * 导出案卷列表
     */
    @RequiresPermissions("business:cases:export")
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

    /**
     * 新增保存案卷
     */
    @RequiresPermissions("business:cases:add")
    @Log(title = "案卷", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FCases fCases)
    {
        return toAjax(fCasesService.insertFCases(fCases));
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
