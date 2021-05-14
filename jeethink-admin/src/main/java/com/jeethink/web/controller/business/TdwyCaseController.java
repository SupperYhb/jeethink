package com.jeethink.web.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jeethink.business.domain.TdwyCase;
import com.jeethink.business.service.ITdwyCaseService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 天地伟业案卷Controller
 * 
 * @author yhb
 * @date 2021-02-05
 */
@Controller
@RequestMapping("/business/tdwycase")
public class TdwyCaseController extends BaseController
{
    private String prefix = "business/tdwycase";

    @Autowired
    private ITdwyCaseService tdwyCaseService;

    @RequiresPermissions("business:tdwycase:view")
    @GetMapping()
    public String tdwycase()
    {
        return prefix + "/tdwycase";
    }

    /**
     * 查询天地伟业案卷列表
     */
    @RequiresPermissions("business:tdwycase:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TdwyCase tdwyCase)
    {
        startPage();
        List<TdwyCase> list = tdwyCaseService.selectTdwyCaseList(tdwyCase);
        return getDataTable(list);
    }
    @PostMapping("/selectTdwyCaseList")
    @ResponseBody
    public TableDataInfo selectTdwyCaseList(String ajmc,String mc,String ajbh){
        Map findMap=new HashMap();
        findMap.put("ajmc",ajmc);
        findMap.put("mc",mc);
        findMap.put("ajbh",ajbh);
        startPage();
        List<TdwyCase> list = tdwyCaseService.selectTdwyCaseList(findMap);
        return getDataTable(list);
    }
    /**
     * 导出天地伟业案卷列表
     */
    @RequiresPermissions("business:tdwycase:export")
    @Log(title = "天地伟业案卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TdwyCase tdwyCase)
    {
        List<TdwyCase> list = tdwyCaseService.selectTdwyCaseList(tdwyCase);
        ExcelUtil<TdwyCase> util = new ExcelUtil<TdwyCase>(TdwyCase.class);
        return util.exportExcel(list, "tdwycase");
    }

    /**
     * 新增天地伟业案卷
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存天地伟业案卷
     */
    @RequiresPermissions("business:tdwycase:add")
    @Log(title = "天地伟业案卷", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TdwyCase tdwyCase)
    {
        return toAjax(tdwyCaseService.insertTdwyCase(tdwyCase));
    }

    /**
     * 修改天地伟业案卷
     */
    @GetMapping("/edit/{sId}")
    public String edit(@PathVariable("sId") String sId, ModelMap mmap)
    {
        TdwyCase tdwyCase = tdwyCaseService.selectTdwyCaseById(sId);
        mmap.put("tdwyCase", tdwyCase);
        return prefix + "/edit";
    }

    /**
     * 修改保存天地伟业案卷
     */
    @RequiresPermissions("business:tdwycase:edit")
    @Log(title = "天地伟业案卷", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TdwyCase tdwyCase)
    {
        return toAjax(tdwyCaseService.updateTdwyCase(tdwyCase));
    }

    /**
     * 删除天地伟业案卷
     */
    @RequiresPermissions("business:tdwycase:remove")
    @Log(title = "天地伟业案卷", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tdwyCaseService.deleteTdwyCaseByIds(ids));
    }

}
