package com.jeethink.web.controller.basicInfo;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.jeethink.basicInfo.domain.saveBoxEntity;
import com.jeethink.business.domain.FCases;
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
import com.jeethink.basicInfo.domain.FBox;
import com.jeethink.basicInfo.service.IFBoxService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 箱体Controller
 * 
 * @author yhb
 * @date 2020-08-31
 */
@Controller
@RequestMapping("/basicInfo/box")
public class FBoxController extends BaseController
{
    private String prefix = "basicInfo/box";

    @Autowired
    private IFBoxService fBoxService;

    @RequiresPermissions("basicInfo:box:view")
    @GetMapping()
    public String box()
    {
        return prefix + "/box";
    }

    /**
     * 查询箱体列表
     */
    @RequiresPermissions("basicInfo:box:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FBox fBox)
    {
        startPage();
        List<FBox> list = fBoxService.selectFBoxList(fBox);
        return getDataTable(list);
    }

    /**
     * 导出箱体列表
     */
    @RequiresPermissions("basicInfo:box:export")
    @Log(title = "箱体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FBox fBox)
    {
        List<FBox> list = fBoxService.selectFBoxList(fBox);
        ExcelUtil<FBox> util = new ExcelUtil<FBox>(FBox.class);
        return util.exportExcel(list, "box");
    }

    /**
     * 设置箱体
     */
    @GetMapping("/setform")
    public String setform(String lockerId,ModelMap mmap)
    {
        mmap.put("lockerId",lockerId);
        return prefix + "/setform";
    }

    /**
     * 设置副柜
     */
    @GetMapping("/deskbox")
    public String deskbox()
    {
        return prefix + "/deskbox";
    }

    /**
     * 设置主柜
     */
    @GetMapping("/mainbox")
    public String mainbox()
    {
        return prefix + "/mainbox";
    }


    /**
     * 新增保存箱体
     */
    @RequiresPermissions("basicInfo:box:add")
    @Log(title = "箱体", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FBox fBox)
    {
        return toAjax(fBoxService.insertFBox(fBox));
    }

    /**
     * 新增保存箱体
     */
    @Log(title = "设置箱体", businessType = BusinessType.INSERT)
    @PostMapping("/setBox")
    @ResponseBody
    public AjaxResult setBox(String json){
        List<saveBoxEntity> list= JSON.parseArray(json, saveBoxEntity.class);
        fBoxService.setBox(list);
        return toAjax(1);
    }
    /**
     * 修改箱体
     */
    @GetMapping("/edit/{fBoxid}")
    public String edit(@PathVariable("fBoxid") String fBoxid, ModelMap mmap)
    {
        FBox fBox = fBoxService.selectFBoxById(fBoxid);
        mmap.put("fBox", fBox);
        return prefix + "/edit";
    }

    /**
     * 修改保存箱体
     */
    @RequiresPermissions("basicInfo:box:edit")
    @Log(title = "箱体", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FBox fBox)
    {
        return toAjax(fBoxService.updateFBox(fBox));
    }

    /**
     * 删除箱体
     */
    @RequiresPermissions("basicInfo:box:remove")
    @Log(title = "箱体", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fBoxService.deleteFBoxByIds(ids));
    }
}
