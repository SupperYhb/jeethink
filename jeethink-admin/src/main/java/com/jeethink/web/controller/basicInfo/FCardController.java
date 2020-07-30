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
import com.jeethink.basicInfo.domain.FCard;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 卡Controller
 * 
 * @author yhb
 * @date 2020-07-23
 */
@Controller
@RequestMapping("/basicInfo/card")
public class FCardController extends BaseController
{
    private String prefix = "basicInfo/card";

    @Autowired
    private IFCardService fCardService;

    @RequiresPermissions("basicInfo:card:view")
    @GetMapping()
    public String card()
    {
        return prefix + "/card";
    }

    /**
     * 查询卡列表
     */
    @RequiresPermissions("basicInfo:card:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FCard fCard)
    {
        startPage();
        List<FCard> list = fCardService.selectFCardList(fCard);
        return getDataTable(list);
    }


    /**
     * 根据用户Id查询使用的卡
     */
    @PostMapping("/selectCardByUserId")
    @ResponseBody
    public TableDataInfo selectCardByUserId(FCard fCard)
    {
        List<FCard> list = fCardService.selectCardByUserId(fCard.getfUserid());
        return getDataTable(list);
    }
    /**
     * 导出卡列表
     */
    @RequiresPermissions("basicInfo:card:export")
    @Log(title = "卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FCard fCard)
    {
        List<FCard> list = fCardService.selectFCardList(fCard);
        ExcelUtil<FCard> util = new ExcelUtil<FCard>(FCard.class);
        return util.exportExcel(list, "card");
    }

    /**
     * 验证卡是否可用
     * */
    @PostMapping("/verificationCard")
    @ResponseBody
    public AjaxResult verificationCard(FCard fCard){
    List<FCard> list=fCardService.verificationCard(fCard);
    return success(list.size()>0?list.get(0).getfCardid():"");
    }

    /**
     * 新增卡
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    /**
     * 刷卡页面
     */
    @GetMapping("/swipingform")
    public String swipingform()
    {
        return prefix + "/swipingform";
    }

    /**
     * 新增保存卡
     */
    @RequiresPermissions("basicInfo:card:add")
    @Log(title = "卡", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FCard fCard)
    {
        return toAjax(fCardService.insertFCard(fCard));
    }

    /**
     * 修改卡
     */
    @GetMapping("/edit/{fCardid}")
    public String edit(@PathVariable("fCardid") String fCardid, ModelMap mmap)
    {
        FCard fCard = fCardService.selectFCardById(fCardid);
        mmap.put("fCard", fCard);
        return prefix + "/edit";
    }

    /**
     * 修改保存卡
     */
    @RequiresPermissions("basicInfo:card:edit")
    @Log(title = "卡", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FCard fCard)
    {
        return toAjax(fCardService.updateFCard(fCard));
    }

    /**
     * 删除卡
     */
    @RequiresPermissions("basicInfo:card:remove")
    @Log(title = "卡", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fCardService.deleteFCardByIds(ids));
    }
}
