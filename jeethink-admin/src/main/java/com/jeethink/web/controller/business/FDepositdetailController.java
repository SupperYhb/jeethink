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
import com.jeethink.business.domain.FDepositdetail;
import com.jeethink.business.service.IFDepositdetailService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 案卷存入明细Controller
 * 
 * @author yhb
 * @date 2020-07-24
 */
@Controller
@RequestMapping("/business/depositdetail")
public class FDepositdetailController extends BaseController
{
    private String prefix = "business/depositdetail";

    @Autowired
    private IFDepositdetailService fDepositdetailService;

    @RequiresPermissions("business:depositdetail:view")
    @GetMapping()
    public String depositdetail()
    {
        return prefix + "/depositdetail";
    }

    /**
     * 查询案卷存入明细列表
     */
    @RequiresPermissions("business:depositdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FDepositdetail fDepositdetail)
    {
        startPage();
        List<FDepositdetail> list = fDepositdetailService.selectFDepositdetailList(fDepositdetail);
        return getDataTable(list);
    }

    /**
     * 导出案卷存入明细列表
     */
    @RequiresPermissions("business:depositdetail:export")
    @Log(title = "案卷存入明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FDepositdetail fDepositdetail)
    {
        List<FDepositdetail> list = fDepositdetailService.selectFDepositdetailList(fDepositdetail);
        ExcelUtil<FDepositdetail> util = new ExcelUtil<FDepositdetail>(FDepositdetail.class);
        return util.exportExcel(list, "depositdetail");
    }

    /**
     * 新增案卷存入明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存案卷存入明细
     */
    @RequiresPermissions("business:depositdetail:add")
    @Log(title = "案卷存入明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FDepositdetail fDepositdetail)
    {
        return toAjax(fDepositdetailService.insertFDepositdetail(fDepositdetail));
    }

    /**
     * 修改案卷存入明细
     */
    @GetMapping("/edit/{fDepositdetailid}")
    public String edit(@PathVariable("fDepositdetailid") String fDepositdetailid, ModelMap mmap)
    {
        FDepositdetail fDepositdetail = fDepositdetailService.selectFDepositdetailById(fDepositdetailid);
        mmap.put("fDepositdetail", fDepositdetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存案卷存入明细
     */
    @RequiresPermissions("business:depositdetail:edit")
    @Log(title = "案卷存入明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FDepositdetail fDepositdetail)
    {
        return toAjax(fDepositdetailService.updateFDepositdetail(fDepositdetail));
    }

    /**
     * 删除案卷存入明细
     */
    @RequiresPermissions("business:depositdetail:remove")
    @Log(title = "案卷存入明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fDepositdetailService.deleteFDepositdetailByIds(ids));
    }
}
