package com.jeethink.web.controller.business;

import java.util.List;

import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.mapper.FLockerMapper;
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
import com.jeethink.business.domain.FCheck;
import com.jeethink.business.service.IFCheckService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 盘点主表Controller
 * 
 * @author yhb
 * @date 2020-08-07
 */
@Controller
@RequestMapping("/business/check")
public class FCheckController extends BaseController
{
    private String prefix = "business/check";

    @Autowired
    private IFCheckService fCheckService;
    @Autowired
    private FLockerMapper fLockerMapper;

    @RequiresPermissions("business:check:view")
    @GetMapping()
    public String check()
    {
        return prefix + "/check";
    }

    /**
     * 查询盘点主表列表
     */
    @RequiresPermissions("business:check:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FCheck fCheck)
    {
        startPage();
        List<FCheck> list = fCheckService.selectFCheckList(fCheck);
        return getDataTable(list);
    }

    /**
     * 导出盘点主表列表
     */
    @RequiresPermissions("business:check:export")
    @Log(title = "盘点主表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FCheck fCheck)
    {
        List<FCheck> list = fCheckService.selectFCheckList(fCheck);
        ExcelUtil<FCheck> util = new ExcelUtil<FCheck>(FCheck.class);
        return util.exportExcel(list, "check");
    }

    /** 保存盘点信息 */
    @PostMapping("/addCheck")
    @ResponseBody
    public AjaxResult addCheck(String lockerId,String name)
    {
        String Result=fCheckService.addCheck(lockerId,name);
        return Result.isEmpty()?success("保存成功"):error("保存失败");
    }

    /**
     * 新增盘点主表
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {

        List<FLocker> fLockerList=fLockerMapper.selectFLockerList(null);
        mmap.put("fLockerList",fLockerList);
        return prefix + "/add";
    }

    /**
     * 盘点form
     */
    @GetMapping("/checkform")
    public String checkform(String checkId,ModelMap mmap){
        mmap.put("checkId",checkId);
        return prefix+"/checkform";
    }
    /**
     * 新增保存盘点主表
     */
    @RequiresPermissions("business:check:add")
    @Log(title = "盘点主表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FCheck fCheck)
    {
        return toAjax(fCheckService.insertFCheck(fCheck));
    }

    /**
     * 修改盘点主表
     */
    @GetMapping("/edit/{fCheckid}")
    public String edit(@PathVariable("fCheckid") String fCheckid, ModelMap mmap)
    {
        FCheck fCheck = fCheckService.selectFCheckById(fCheckid);
        mmap.put("fCheck", fCheck);
        return prefix + "/edit";
    }

    /**
     * 修改保存盘点主表
     */
    @RequiresPermissions("business:check:edit")
    @Log(title = "盘点主表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FCheck fCheck)
    {
        return toAjax(fCheckService.updateFCheck(fCheck));
    }

    /**
     * 删除盘点主表
     */
    @RequiresPermissions("business:check:remove")
    @Log(title = "盘点主表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fCheckService.deleteFCheckByIds(ids));
    }
}
