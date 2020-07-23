package com.jeethink.web.controller.business;

import java.util.List;

import com.jeethink.requestutil.entity.kdcaseentity;
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
import com.jeethink.business.domain.FDeposit;
import com.jeethink.business.service.IFDepositService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 存放案卷Controller
 * 
 * @author yhb
 * @date 2020-07-22
 */
@Controller
@RequestMapping("/business/deposit")
public class FDepositController extends BaseController
{
    private String prefix = "business/deposit";

    @Autowired
    private IFDepositService fDepositService;

    @RequiresPermissions("business:deposit:view")
    @GetMapping()
    public String deposit()
    {
        return prefix + "/deposit";
    }

    /**
     * 查询存放案卷列表
     */
    @RequiresPermissions("business:deposit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FDeposit fDeposit)
    {
        startPage();
        List<FDeposit> list = fDepositService.selectFDepositList(fDeposit);
        return getDataTable(list);
    }
    /**
     * 查询科达案卷数据列表
     */
    @RequiresPermissions("basicInfo:deposit:getkdCaselist")
    @PostMapping("/getkdCaselist")
    @ResponseBody
    public TableDataInfo getkdCaselist()
    {
        startPage();
        List<kdcaseentity> list = fDepositService.getkdCase();
        return getDataTable(list);
    }
    /**
     * 导出存放案卷列表
     */
    @RequiresPermissions("business:deposit:export")
    @Log(title = "存放案卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FDeposit fDeposit)
    {
        List<FDeposit> list = fDepositService.selectFDepositList(fDeposit);
        ExcelUtil<FDeposit> util = new ExcelUtil<FDeposit>(FDeposit.class);
        return util.exportExcel(list, "deposit");
    }

    /**
     * 新增存放案卷
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存存放案卷
     */
    @RequiresPermissions("business:deposit:add")
    @Log(title = "存放案卷", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FDeposit fDeposit)
    {
        return toAjax(fDepositService.insertFDeposit(fDeposit));
    }

    /**
     * 修改存放案卷
     */
    @GetMapping("/edit/{fDepositid}")
    public String edit(@PathVariable("fDepositid") String fDepositid, ModelMap mmap)
    {
        FDeposit fDeposit = fDepositService.selectFDepositById(fDepositid);
        mmap.put("fDeposit", fDeposit);
        return prefix + "/edit";
    }

    /**
     * 修改保存存放案卷
     */
    @RequiresPermissions("business:deposit:edit")
    @Log(title = "存放案卷", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FDeposit fDeposit)
    {
        return toAjax(fDepositService.updateFDeposit(fDeposit));
    }

    /**
     * 删除存放案卷
     */
    @RequiresPermissions("business:deposit:remove")
    @Log(title = "存放案卷", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fDepositService.deleteFDepositByIds(ids));
    }
}
