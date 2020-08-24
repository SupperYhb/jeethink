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
import com.jeethink.basicInfo.domain.FCamera;
import com.jeethink.basicInfo.service.IFCameraService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 摄像头Controller
 * 
 * @author yhb
 * @date 2020-08-11
 */
@Controller
@RequestMapping("/basicInfo/camera")
public class FCameraController extends BaseController
{
    private String prefix = "basicInfo/camera";

    @Autowired
    private IFCameraService fCameraService;

    @RequiresPermissions("basicInfo:camera:view")
    @GetMapping()
    public String camera()
    {
        return prefix + "/camera";
    }

    /**
     * 查看视频
     * */
    @GetMapping("/video")
    public String video(String Id,ModelMap mmap)
    {
        mmap.put("BusinessId",Id);
        return prefix + "/video";
    }
    /**
     * 查询摄像头列表
     */
    @RequiresPermissions("basicInfo:camera:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FCamera fCamera)
    {
        startPage();
        List<FCamera> list = fCameraService.selectFCameraList(fCamera);
        return getDataTable(list);
    }

    /**
     * 导出摄像头列表
     */
    @RequiresPermissions("basicInfo:camera:export")
    @Log(title = "摄像头", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FCamera fCamera)
    {
        List<FCamera> list = fCameraService.selectFCameraList(fCamera);
        ExcelUtil<FCamera> util = new ExcelUtil<FCamera>(FCamera.class);
        return util.exportExcel(list, "camera");
    }

    /**
     * 新增摄像头
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存摄像头
     */
    @RequiresPermissions("basicInfo:camera:add")
    @Log(title = "摄像头", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FCamera fCamera)
    {
        return toAjax(fCameraService.insertFCamera(fCamera));
    }

    /**
     * 修改摄像头
     */
    @GetMapping("/edit/{fCameraid}")
    public String edit(@PathVariable("fCameraid") String fCameraid, ModelMap mmap)
    {
        FCamera fCamera = fCameraService.selectFCameraById(fCameraid);
        mmap.put("fCamera", fCamera);
        return prefix + "/edit";
    }

    /**
     * 修改保存摄像头
     */
    @RequiresPermissions("basicInfo:camera:edit")
    @Log(title = "摄像头", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FCamera fCamera)
    {
        return toAjax(fCameraService.updateFCamera(fCamera));
    }

    /**
     * 删除摄像头
     */
    @RequiresPermissions("basicInfo:camera:remove")
    @Log(title = "摄像头", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fCameraService.deleteFCameraByIds(ids));
    }
}
