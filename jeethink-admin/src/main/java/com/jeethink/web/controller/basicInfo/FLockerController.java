package com.jeethink.web.controller.basicInfo;

import java.util.List;

import com.jeethink.common.extend.createId;
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
import com.jeethink.basicInfo.domain.FLocker;
import com.jeethink.basicInfo.service.IFLockerService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 卷宗柜Controller
 *
 * @author yhb
 * @date 2020-07-20
 */
@Controller
@RequestMapping("/basicInfo/locker")
public class FLockerController extends BaseController
{
    private String prefix = "basicInfo/locker";

    @Autowired
    private IFLockerService fLockerService;

    @RequiresPermissions("basicInfo:locker:view")
    @GetMapping()
    public String locker()
    {
        return prefix + "/locker";
    }

    /**
     * 查询卷宗柜列表
     */
    @RequiresPermissions("basicInfo:locker:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FLocker fLocker)
    {
        startPage();
        List<FLocker> list = fLockerService.selectFLockerList(fLocker);
        return getDataTable(list);
    }

    /**
     * 导出卷宗柜列表
     */
    @RequiresPermissions("basicInfo:locker:export")
    @Log(title = "卷宗柜", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FLocker fLocker)
    {
        List<FLocker> list = fLockerService.selectFLockerList(fLocker);
        ExcelUtil<FLocker> util = new ExcelUtil<FLocker>(FLocker.class);
        return util.exportExcel(list, "locker");
    }

    /**
     * 新增卷宗柜
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存卷宗柜
     */
    @RequiresPermissions("basicInfo:locker:add")
    @Log(title = "卷宗柜", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FLocker fLocker)
    {
        fLocker.setfLockerid(createId.getID());
        return toAjax(fLockerService.insertFLocker(fLocker));
    }

    /**
     * 修改卷宗柜
     */
    @GetMapping("/edit/{fLockerid}")
    public String edit(@PathVariable("fLockerid") String fLockerid, ModelMap mmap)
    {
        FLocker fLocker = fLockerService.selectFLockerById(fLockerid);
        mmap.put("fLocker", fLocker);
        return prefix + "/edit";
    }

    /**
     * 设置卷宗柜
     * */
    @GetMapping("/setfrom")
    public String setForm(){
        return prefix+"/setfrom";
    }
    /**
     * 修改保存卷宗柜
     */
    @RequiresPermissions("basicInfo:locker:edit")
    @Log(title = "卷宗柜", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FLocker fLocker)
    {
        return toAjax(fLockerService.updateFLocker(fLocker));
    }

    /**
     * 删除卷宗柜
     */
    @RequiresPermissions("basicInfo:locker:remove")
    @Log(title = "卷宗柜", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fLockerService.deleteFLockerByIds(ids));
    }
}
