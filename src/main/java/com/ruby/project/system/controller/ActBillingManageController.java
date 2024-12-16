package com.ruby.project.system.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruby.common.utils.PayOrderAnalysisUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruby.framework.aspectj.lang.annotation.Log;
import com.ruby.framework.aspectj.lang.enums.BusinessType;
import com.ruby.project.system.domain.ActBillingManage;
import com.ruby.project.system.service.IActBillingManageService;
import com.ruby.framework.web.controller.BaseController;
import com.ruby.framework.web.domain.AjaxResult;
import com.ruby.common.utils.poi.ExcelUtil;
import com.ruby.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账单管理Controller
 *
 * @author ruby
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/system/manage")
public class ActBillingManageController extends BaseController
{
    @Autowired
    private IActBillingManageService actBillingManageService;

    /**
     * 查询账单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActBillingManage actBillingManage)
    {
//        startPage();
        List<ActBillingManage> list = actBillingManageService.selectActBillingManageList(actBillingManage);
        return getDataTable(list);
    }

    /**
     * 导出账单管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:manage:export')")
    @Log(title = "账单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActBillingManage actBillingManage)
    {
        List<ActBillingManage> list = actBillingManageService.selectActBillingManageList(actBillingManage);
        ExcelUtil<ActBillingManage> util = new ExcelUtil<ActBillingManage>(ActBillingManage.class);
        util.exportExcel(response, list, "账单管理数据");
    }

    /**
     * 获取账单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(actBillingManageService.selectActBillingManageById(id));
    }

    /**
     * 新增账单管理
     */
    @PreAuthorize("@ss.hasPermi('system:manage:add')")
    @Log(title = "账单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActBillingManage actBillingManage)
    {
        return toAjax(actBillingManageService.insertActBillingManage(actBillingManage));
    }

    /**
     * 修改账单管理
     */
    @PreAuthorize("@ss.hasPermi('system:manage:edit')")
    @Log(title = "账单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActBillingManage actBillingManage)
    {
        return toAjax(actBillingManageService.updateActBillingManage(actBillingManage));
    }

    /**
     * 删除账单管理
     */
    @PreAuthorize("@ss.hasPermi('system:manage:remove')")
    @Log(title = "账单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(actBillingManageService.deleteActBillingManageByIds(ids));
    }
    /**
     * 上传csv
     */
    @PreAuthorize("@ss.hasPermi('system:manage:import')")
    @Log(title = "账单管理", businessType = BusinessType.IMPORT)
    @PostMapping("/uploadCsv")
    public AjaxResult uploadCsv(String importType, MultipartFile file)
    {
        List<ActBillingManage> actBillingManages = actBillingManageService.insertActBillingManage2Csv(importType, file);

        return AjaxResult.success(actBillingManages);
    }
}
