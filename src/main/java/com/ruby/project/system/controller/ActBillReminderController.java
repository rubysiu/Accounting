package com.ruby.project.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruby.project.system.domain.ActBillReminder;
import com.ruby.project.system.service.IActBillReminderService;
import com.ruby.framework.web.controller.BaseController;
import com.ruby.framework.web.domain.AjaxResult;
import com.ruby.common.utils.poi.ExcelUtil;
import com.ruby.framework.web.page.TableDataInfo;

/**
 * 账单提醒Controller
 * 
 * @author ruby
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/system/reminder")
public class ActBillReminderController extends BaseController
{
    @Autowired
    private IActBillReminderService actBillReminderService;

    /**
     * 查询账单提醒列表
     */
    @PreAuthorize("@ss.hasPermi('system:reminder:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActBillReminder actBillReminder)
    {
        startPage();
        List<ActBillReminder> list = actBillReminderService.selectActBillReminderList(actBillReminder);
        return getDataTable(list);
    }

    /**
     * 导出账单提醒列表
     */
    @PreAuthorize("@ss.hasPermi('system:reminder:export')")
    @Log(title = "账单提醒", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActBillReminder actBillReminder)
    {
        List<ActBillReminder> list = actBillReminderService.selectActBillReminderList(actBillReminder);
        ExcelUtil<ActBillReminder> util = new ExcelUtil<ActBillReminder>(ActBillReminder.class);
        util.exportExcel(response, list, "账单提醒数据");
    }

    /**
     * 获取账单提醒详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:reminder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(actBillReminderService.selectActBillReminderById(id));
    }

    /**
     * 新增账单提醒
     */
    @PreAuthorize("@ss.hasPermi('system:reminder:add')")
    @Log(title = "账单提醒", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActBillReminder actBillReminder)
    {
        return toAjax(actBillReminderService.insertActBillReminder(actBillReminder));
    }

    /**
     * 修改账单提醒
     */
    @PreAuthorize("@ss.hasPermi('system:reminder:edit')")
    @Log(title = "账单提醒", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActBillReminder actBillReminder)
    {
        return toAjax(actBillReminderService.updateActBillReminder(actBillReminder));
    }

    /**
     * 删除账单提醒
     */
    @PreAuthorize("@ss.hasPermi('system:reminder:remove')")
    @Log(title = "账单提醒", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(actBillReminderService.deleteActBillReminderByIds(ids));
    }
}
