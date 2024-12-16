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
import com.ruby.project.system.domain.ActWalletConsumptionRecords;
import com.ruby.project.system.service.IActWalletConsumptionRecordsService;
import com.ruby.framework.web.controller.BaseController;
import com.ruby.framework.web.domain.AjaxResult;
import com.ruby.common.utils.poi.ExcelUtil;
import com.ruby.framework.web.page.TableDataInfo;

/**
 * 消费记录Controller
 *
 * @author ruby
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/system/walletConsumptionRecords")
public class ActWalletConsumptionRecordsController extends BaseController
{
    @Autowired
    private IActWalletConsumptionRecordsService actWalletConsumptionRecordsService;

    /**
     * 查询消费记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:walletConsumptionRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActWalletConsumptionRecords actWalletConsumptionRecords)
    {
        startPage();
        List<ActWalletConsumptionRecords> list = actWalletConsumptionRecordsService.selectActWalletConsumptionRecordsList(actWalletConsumptionRecords);
        return getDataTable(list);
    }

    /**
     * 导出消费记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:walletConsumptionRecords:export')")
    @Log(title = "消费记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActWalletConsumptionRecords actWalletConsumptionRecords)
    {
        List<ActWalletConsumptionRecords> list = actWalletConsumptionRecordsService.selectActWalletConsumptionRecordsList(actWalletConsumptionRecords);
        ExcelUtil<ActWalletConsumptionRecords> util = new ExcelUtil<ActWalletConsumptionRecords>(ActWalletConsumptionRecords.class);
        util.exportExcel(response, list, "消费记录数据");
    }

    /**
     * 获取消费记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:walletConsumptionRecords:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(actWalletConsumptionRecordsService.selectActWalletConsumptionRecordsById(id));
    }

    /**
     * 新增消费记录
     */
    @PreAuthorize("@ss.hasPermi('system:walletConsumptionRecords:add')")
    @Log(title = "消费记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActWalletConsumptionRecords actWalletConsumptionRecords)
    {
        return toAjax(actWalletConsumptionRecordsService.insertActWalletConsumptionRecords(actWalletConsumptionRecords));
    }

    /**
     * 修改消费记录
     */
    @PreAuthorize("@ss.hasPermi('system:walletConsumptionRecords:edit')")
    @Log(title = "消费记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActWalletConsumptionRecords actWalletConsumptionRecords)
    {
        return toAjax(actWalletConsumptionRecordsService.updateActWalletConsumptionRecords(actWalletConsumptionRecords));
    }

    /**
     * 删除消费记录
     */
    @PreAuthorize("@ss.hasPermi('system:walletConsumptionRecords:remove')")
    @Log(title = "消费记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(actWalletConsumptionRecordsService.deleteActWalletConsumptionRecordsByIds(ids));
    }
}
