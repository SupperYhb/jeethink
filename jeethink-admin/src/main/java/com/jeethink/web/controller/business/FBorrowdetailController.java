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
import com.jeethink.business.domain.FBorrowdetail;
import com.jeethink.business.service.IFBorrowdetailService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 取出明细Controller
 * 
 * @author yhb
 * @date 2020-07-28
 */
@Controller
@RequestMapping("/business/borrowdetail")
public class FBorrowdetailController extends BaseController
{
    private String prefix = "business/borrowdetail";

    @Autowired
    private IFBorrowdetailService fBorrowdetailService;

    @RequiresPermissions("business:borrowdetail:view")
    @GetMapping()
    public String borrowdetail()
    {
        return prefix + "/borrowdetail";
    }

    /**
     * 查询取出明细列表
     */
    @RequiresPermissions("business:borrowdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FBorrowdetail fBorrowdetail)
    {
        startPage();
        List<FBorrowdetail> list = fBorrowdetailService.selectFBorrowdetailList(fBorrowdetail);
        return getDataTable(list);
    }

    /**
     * 导出取出明细列表
     */
    @RequiresPermissions("business:borrowdetail:export")
    @Log(title = "取出明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FBorrowdetail fBorrowdetail)
    {
        List<FBorrowdetail> list = fBorrowdetailService.selectFBorrowdetailList(fBorrowdetail);
        ExcelUtil<FBorrowdetail> util = new ExcelUtil<FBorrowdetail>(FBorrowdetail.class);
        return util.exportExcel(list, "borrowdetail");
    }

    /**
     * 新增取出明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存取出明细
     */
    @RequiresPermissions("business:borrowdetail:add")
    @Log(title = "取出明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FBorrowdetail fBorrowdetail)
    {
        return toAjax(fBorrowdetailService.insertFBorrowdetail(fBorrowdetail));
    }

    /**
     * 修改取出明细
     */
    @GetMapping("/edit/{fBorrowdetailid}")
    public String edit(@PathVariable("fBorrowdetailid") String fBorrowdetailid, ModelMap mmap)
    {
        FBorrowdetail fBorrowdetail = fBorrowdetailService.selectFBorrowdetailById(fBorrowdetailid);
        mmap.put("fBorrowdetail", fBorrowdetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存取出明细
     */
    @RequiresPermissions("business:borrowdetail:edit")
    @Log(title = "取出明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FBorrowdetail fBorrowdetail)
    {
        return toAjax(fBorrowdetailService.updateFBorrowdetail(fBorrowdetail));
    }

    /**
     * 删除取出明细
     */
    @RequiresPermissions("business:borrowdetail:remove")
    @Log(title = "取出明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fBorrowdetailService.deleteFBorrowdetailByIds(ids));
    }
}
