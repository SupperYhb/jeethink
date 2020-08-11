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
import com.jeethink.business.domain.FCheckdetail;
import com.jeethink.business.service.IFCheckdetailService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 盘点明细Controller
 * 
 * @author yhb
 * @date 2020-08-07
 */
@Controller
@RequestMapping("/business/checkdetail")
public class FCheckdetailController extends BaseController
{
    private String prefix = "business/checkdetail";

    @Autowired
    private IFCheckdetailService fCheckdetailService;

    @RequiresPermissions("business:checkdetail:view")
    @GetMapping()
    public String checkdetail()
    {
        return prefix + "/checkdetail";
    }

    /**
     * 查询盘点明细列表
     */
    @RequiresPermissions("business:checkdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FCheckdetail fCheckdetail)
    {
        startPage();
        List<FCheckdetail> list = fCheckdetailService.selectFCheckdetailList(fCheckdetail);
        return getDataTable(list);
    }

    /**
     * 导出盘点明细列表
     */
    @RequiresPermissions("business:checkdetail:export")
    @Log(title = "盘点明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FCheckdetail fCheckdetail)
    {
        List<FCheckdetail> list = fCheckdetailService.selectFCheckdetailList(fCheckdetail);
        ExcelUtil<FCheckdetail> util = new ExcelUtil<FCheckdetail>(FCheckdetail.class);
        return util.exportExcel(list, "checkdetail");
    }

    /**
     * 新增盘点明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存盘点明细
     */
    @RequiresPermissions("business:checkdetail:add")
    @Log(title = "盘点明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FCheckdetail fCheckdetail)
    {
        return toAjax(fCheckdetailService.insertFCheckdetail(fCheckdetail));
    }

    /**
     * 修改盘点明细
     */
    @GetMapping("/edit/{fCheckdetailid}")
    public String edit(@PathVariable("fCheckdetailid") String fCheckdetailid, ModelMap mmap)
    {
        FCheckdetail fCheckdetail = fCheckdetailService.selectFCheckdetailById(fCheckdetailid);
        mmap.put("fCheckdetail", fCheckdetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存盘点明细
     */
    @RequiresPermissions("business:checkdetail:edit")
    @Log(title = "盘点明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FCheckdetail fCheckdetail)
    {
        return toAjax(fCheckdetailService.updateFCheckdetail(fCheckdetail));
    }

    /**
     * 删除盘点明细
     */
    @RequiresPermissions("business:checkdetail:remove")
    @Log(title = "盘点明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fCheckdetailService.deleteFCheckdetailByIds(ids));
    }
}
