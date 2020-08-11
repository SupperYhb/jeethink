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
import com.jeethink.business.domain.FTrack;
import com.jeethink.business.service.IFTrackService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 案卷轨迹Controller
 * 
 * @author yhb
 * @date 2020-08-03
 */
@Controller
@RequestMapping("/business/track")
public class FTrackController extends BaseController
{
    private String prefix = "business/track";

    @Autowired
    private IFTrackService fTrackService;

    @RequiresPermissions("business:track:view")
    @GetMapping()
    public String track()
    {
        return prefix + "/track";
    }

    /**
     * 查询案卷轨迹列表
     */
    @RequiresPermissions("business:track:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FTrack fTrack)
    {
        startPage();
        List<FTrack> list = fTrackService.selectFTrackList(fTrack);
        return getDataTable(list);
    }

    /**
     * 导出案卷轨迹列表
     */
    @RequiresPermissions("business:track:export")
    @Log(title = "案卷轨迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FTrack fTrack)
    {
        List<FTrack> list = fTrackService.selectFTrackList(fTrack);
        ExcelUtil<FTrack> util = new ExcelUtil<FTrack>(FTrack.class);
        return util.exportExcel(list, "track");
    }

    /**
     * 根据案卷编号查询轨迹列表
     * */
    @PostMapping("/selectFtrackByCaseCode")
    @ResponseBody
    public TableDataInfo selectFtrackByCaseCode(String caseCode){
        List<FTrack> list=fTrackService.selectFtrackByCaseCode(caseCode);
        return getDataTable(list);
    }

    /**
     * 查看案卷轨迹
     */
    @GetMapping("/checktrack")
    public String checktrack(String FtrackId,ModelMap mmap)
    {
        mmap.put("FtrackId",FtrackId);
        return prefix + "/checktrack";
    }

    /**
     * 新增保存案卷轨迹
     */
    @RequiresPermissions("business:track:add")
    @Log(title = "案卷轨迹", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FTrack fTrack)
    {
        return toAjax(fTrackService.insertFTrack(fTrack));
    }

    /**
     * 修改案卷轨迹
     */
    @GetMapping("/edit/{fTrackid}")
    public String edit(@PathVariable("fTrackid") String fTrackid, ModelMap mmap)
    {
        FTrack fTrack = fTrackService.selectFTrackById(fTrackid);
        mmap.put("fTrack", fTrack);
        return prefix + "/edit";
    }

    /**
     * 修改保存案卷轨迹
     */
    @RequiresPermissions("business:track:edit")
    @Log(title = "案卷轨迹", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FTrack fTrack)
    {
        return toAjax(fTrackService.updateFTrack(fTrack));
    }

    /**
     * 删除案卷轨迹
     */
    @RequiresPermissions("business:track:remove")
    @Log(title = "案卷轨迹", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fTrackService.deleteFTrackByIds(ids));
    }
}
