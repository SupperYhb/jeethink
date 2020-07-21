package com.jeethink.web.controller.basicInfo;

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
import com.jeethink.basicInfo.domain.FPosition;
import com.jeethink.basicInfo.service.IFPositionService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 货位Controller
 * 
 * @author yhb
 * @date 2020-07-21
 */
@Controller
@RequestMapping("/basicInfo/position")
public class FPositionController extends BaseController
{
    private String prefix = "basicInfo/position";

    @Autowired
    private IFPositionService fPositionService;

    @RequiresPermissions("basicInfo:position:view")
    @GetMapping()
    public String position()
    {
        return prefix + "/position";
    }

    /**
     * 查询货位列表
     */
    @RequiresPermissions("basicInfo:position:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FPosition fPosition)
    {
//        startPage();
        List<FPosition> list = fPositionService.selectFPositionList(fPosition);
        return getDataTable(list);
    }

    /**
     * 导出货位列表
     */
    @RequiresPermissions("basicInfo:position:export")
    @Log(title = "货位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FPosition fPosition)
    {
        List<FPosition> list = fPositionService.selectFPositionList(fPosition);
        ExcelUtil<FPosition> util = new ExcelUtil<FPosition>(FPosition.class);
        return util.exportExcel(list, "position");
    }

    /**
     * 新增货位
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存货位
     */
    @RequiresPermissions("basicInfo:position:add")
    @Log(title = "货位", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FPosition fPosition)
    {
        return toAjax(fPositionService.insertFPosition(fPosition));
    }

    /**
     * 修改货位
     */
    @GetMapping("/edit/{fPositionid}")
    public String edit(@PathVariable("fPositionid") String fPositionid, ModelMap mmap)
    {
        FPosition fPosition = fPositionService.selectFPositionById(fPositionid);
        mmap.put("fPosition", fPosition);
        return prefix + "/edit";
    }

    /**
     * 修改保存货位
     */
    @RequiresPermissions("basicInfo:position:edit")
    @Log(title = "货位", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FPosition fPosition)
    {
        return toAjax(fPositionService.updateFPosition(fPosition));
    }

    /**
     * 删除货位
     */
    @RequiresPermissions("basicInfo:position:remove")
    @Log(title = "货位", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fPositionService.deleteFPositionByIds(ids));
    }
}
