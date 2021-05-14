package com.jeethink.web.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.jeethink.basicInfo.domain.FCamera;
import com.jeethink.business.domain.FCases;
import com.jeethink.business.domain.TdwyCase;
import com.jeethink.business.service.IFBorrowService;
import com.jeethink.business.service.ITdwyCaseService;
import com.jeethink.common.config.Global;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.requestutil.entity.kdcaseentity;
import com.jeethink.requestutil.entity.tdwyCase;
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
import com.jeethink.business.domain.FDeposit;
import com.jeethink.business.service.IFDepositService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;
import springfox.documentation.spring.web.json.Json;

/**
 * 存放案卷Controller
 * 
 * @author yhb
 * @date 2020-07-22
 */
@Controller
@RequestMapping("/business/deposit")
public class FDepositController extends BaseController
{
    private String prefix = "business/deposit";

    @Autowired
    private IFDepositService fDepositService;
    @Autowired
    private IFBorrowService fBorrowService;
    @Autowired
    private ITdwyCaseService tdwyCaseService;

    @RequiresPermissions("business:deposit:view")
    @GetMapping()
    public String deposit()
    {
        return prefix + "/deposit";
    }

    /**
     * 查询存放案卷列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FDeposit fDeposit)
    {
        startPage();
        List<FDeposit> list = fDepositService.selectFDepositList(fDeposit);
        return getDataTable(list);
    }
    /**
     * 查询科达案卷数据列表
     */
    @PostMapping("/getkdCaselist")
    @ResponseBody
    public TableDataInfo getkdCaselist(String caseName,String caseNumber,String policeCode)
    {
        startPage();
        List<kdcaseentity> list = fDepositService.getkdCase(caseName,caseNumber,policeCode);
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
     * 导出存放案卷列表
     */
    @Log(title = "存放案卷", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FDeposit fDeposit)
    {
        List<FDeposit> list = fDepositService.selectFDepositList(fDeposit);
        ExcelUtil<FDeposit> util = new ExcelUtil<FDeposit>(FDeposit.class);
        return util.exportExcel(list, "deposit");
    }

    /**
     * 新增存放案卷
     */
    @GetMapping("/add")
    public String add( ModelMap mmap)
    {
        String isFace=Global.getIsFace();
        mmap.put("peopleType", ShiroUtils.getType());
        mmap.put("isFace",isFace);
        return prefix + "/add";
    }
    /**
     * 天地伟业数据拉取
     */
    @GetMapping("/tdwyadd")
    public String tdwyadd(ModelMap mmap)
    {
        String isFace= Global.getIsFace();
        mmap.put("isFace",isFace);
        return prefix+"/tdwyadd";
    }

    @Log(title = "存放案卷（平台获取）", businessType = BusinessType.INSERT)
    @PostMapping("/addCaseIn")
    @ResponseBody
    public AjaxResult addCaseIn(String list,String lockerId,String positionId,String cardCode,String cardId,
                                String remark,String peopleType,String policeAccount,String policeName,
                                String openDoorType,String PolicePic)
    {
        List<TdwyCase> kdList= JSON.parseArray(list, TdwyCase.class);
        String msg= fDepositService.addCaseIn(kdList,lockerId,positionId,cardCode,cardId,
                remark,peopleType,policeAccount,policeName,openDoorType,PolicePic);
        return success("");
    }

    @Log(title = "归还案卷", businessType = BusinessType.INSERT)
    @PostMapping("/addCaseReturn")
    @ResponseBody
    public AjaxResult addCaseReturn(String list, String lockerId, String positionId, String cardCode, String cardId, String remark,
                                    String peopleType,String policeAccount,String policeName,
                                    String openDoorType,String PolicePic){
        List<FCases> List= JSON.parseArray(list, FCases.class);
        fDepositService.addCaseReturn(List,lockerId,positionId,cardCode,cardId,remark,peopleType,policeAccount,policeName,openDoorType,PolicePic);
        return success("");
    }

    @Log(title = "再次打开柜门(入库或者归还)", businessType = BusinessType.INSERT)
    @PostMapping("/OpenBox")
    @ResponseBody
    public AjaxResult OpenBox(String id,String type){
        String msg=fDepositService.OpenBox(id,type);
        return msg.isEmpty()?success("true"):error("false");
    }
    /**
     * 新增保存存放案卷
     */
    @Log(title = "存放案卷", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FDeposit fDeposit)
    {
        return toAjax(fDepositService.insertFDeposit(fDeposit));
    }
    /**
     * 案卷归还
     */
    @GetMapping("/returnadd")
    public String returnadd(ModelMap mmap)
    {
        String isFace= Global.getIsFace();
        mmap.put("isFace",isFace);
        return prefix+"/returnadd";
    }
    /**
     * 修改存放案卷
     */
    @GetMapping("/edit/{fDepositid}")
    public String edit(@PathVariable("fDepositid") String fDepositid, ModelMap mmap)
    {
        FDeposit fDeposit = fDepositService.selectFDepositById(fDepositid);
        mmap.put("fDeposit", fDeposit);
        return prefix + "/edit";
    }

    /**
     * 根据入库主表Id查询平台及时间信息
     * */
    @PostMapping("/getFcameraById")
    @ResponseBody
    public AjaxResult getFcameraById(String depositId){
        FCamera fCamera=fDepositService.getFcameraById(depositId);
        if(fCamera==null)
        {
            fCamera=fBorrowService.getFcameraById(depositId);
        }
        return success(JSONObject.toJSONString(fCamera));
    }

    /**
     * 修改保存存放案卷
     */
    @RequiresPermissions("business:deposit:edit")
    @Log(title = "存放案卷", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FDeposit fDeposit)
    {
        return toAjax(fDepositService.updateFDeposit(fDeposit));
    }

    /**
     * 删除存放案卷
     */
    @RequiresPermissions("business:deposit:remove")
    @Log(title = "存放案卷", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fDepositService.deleteFDepositByIds(ids));
    }
}
