package com.jeethink.web.controller.business;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.jeethink.basicInfo.service.IFCardService;
import com.jeethink.business.domain.FCases;
import com.jeethink.business.service.IFBorrowdetailService;
import com.jeethink.business.service.IFCasesService;
import com.jeethink.common.config.Global;
import com.jeethink.requestutil.entity.kdcaseentity;
import com.jeethink.system.service.ISysUserService;
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
import com.jeethink.business.domain.FBorrow;
import com.jeethink.business.service.IFBorrowService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 借阅Controller
 * 
 * @author yhb
 * @date 2020-07-28
 */
@Controller
@RequestMapping("/business/borrow")
public class FBorrowController extends BaseController
{
    private String prefix = "business/borrow";

    @Autowired
    private IFBorrowService fBorrowService;



    @RequiresPermissions("business:borrow:view")
    @GetMapping()
    public String borrow()
    {
        return prefix + "/borrow";
    }

    /**
     * 查询借阅列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FBorrow fBorrow)
    {
        startPage();
        List<FBorrow> list = fBorrowService.selectFBorrowList(fBorrow);
        return getDataTable(list);
    }

    /**
     * 导出借阅列表
     */
    @Log(title = "借阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FBorrow fBorrow)
    {
        List<FBorrow> list = fBorrowService.selectFBorrowList(fBorrow);
        ExcelUtil<FBorrow> util = new ExcelUtil<FBorrow>(FBorrow.class);
        return util.exportExcel(list, "borrow");
    }

    /**
     * 借阅案卷
     */
    @PostMapping("/outCase")
    @ResponseBody
    public AjaxResult outCase(String list,String cardCode,String cardId,String remark,
                              String peopleType,String fIsBack,String policeAccount,String policeName,
                              String openDoorType,String PolicePic){
        List<FCases> casesList= JSON.parseArray(list, FCases.class);
        fBorrowService.outCase(casesList,cardCode,cardId,remark,fIsBack,peopleType,policeAccount,policeName,openDoorType,PolicePic);
        return success("");
    }
    /**
     * 新增借阅
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        String isFace= Global.getIsFace();
        mmap.put("isFace",isFace);
        return prefix + "/add";
    }

    /**
     * 新增保存借阅
     */
    @Log(title = "借阅", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FBorrow fBorrow)
    {
        return toAjax(fBorrowService.insertFBorrow(fBorrow));
    }

    @Log(title = "再次打开柜门（借阅）", businessType = BusinessType.INSERT)
    @PostMapping("/OpenBox")
    @ResponseBody
    public AjaxResult OpenBox(String id,String type){
        String msg=fBorrowService.OpenBox(id,type);
        return msg.isEmpty()?success("true"):error("false");
    }
    /**
     * 修改借阅
     */
    @GetMapping("/edit/{fBorrowid}")
    public String edit(@PathVariable("fBorrowid") String fBorrowid, ModelMap mmap)
    {
        FBorrow fBorrow = fBorrowService.selectFBorrowById(fBorrowid);
        mmap.put("fBorrow", fBorrow);
        return prefix + "/edit";
    }

    /**
     * 修改保存借阅
     */
    @RequiresPermissions("business:borrow:edit")
    @Log(title = "借阅", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FBorrow fBorrow)
    {
        return toAjax(fBorrowService.updateFBorrow(fBorrow));
    }

    /**
     * 删除借阅
     */
    @RequiresPermissions("business:borrow:remove")
    @Log(title = "借阅", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fBorrowService.deleteFBorrowByIds(ids));
    }
}
