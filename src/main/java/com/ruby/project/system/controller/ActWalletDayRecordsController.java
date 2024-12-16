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
import com.ruby.project.system.domain.ActWalletDayRecords;
import com.ruby.project.system.service.IActWalletDayRecordsService;
import com.ruby.framework.web.controller.BaseController;
import com.ruby.framework.web.domain.AjaxResult;
import com.ruby.common.utils.poi.ExcelUtil;
import com.ruby.framework.web.page.TableDataInfo;

/**
 * 钱包日结余Controller
 * 
 * @author ruby
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/system/records")
public class ActWalletDayRecordsController extends BaseController
{
    @Autowired
    private IActWalletDayRecordsService actWalletDayRecordsService;

    /**
     * 查询钱包日结余列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActWalletDayRecords actWalletDayRecords)
    {
        startPage();
        List<ActWalletDayRecords> list = actWalletDayRecordsService.selectActWalletDayRecordsList(actWalletDayRecords);
        return getDataTable(list);
    }

    /**
     * 导出钱包日结余列表
     */
    @PreAuthorize("@ss.hasPermi('system:records:export')")
    @Log(title = "钱包日结余", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActWalletDayRecords actWalletDayRecords)
    {
        List<ActWalletDayRecords> list = actWalletDayRecordsService.selectActWalletDayRecordsList(actWalletDayRecords);
        ExcelUtil<ActWalletDayRecords> util = new ExcelUtil<ActWalletDayRecords>(ActWalletDayRecords.class);
        util.exportExcel(response, list, "钱包日结余数据");
    }

    /**
     * 获取钱包日结余详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(actWalletDayRecordsService.selectActWalletDayRecordsById(id));
    }

    /**
     * 新增钱包日结余
     */
    @PreAuthorize("@ss.hasPermi('system:records:add')")
    @Log(title = "钱包日结余", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActWalletDayRecords actWalletDayRecords)
    {
        return toAjax(actWalletDayRecordsService.insertActWalletDayRecords(actWalletDayRecords));
    }

    /**
     * 修改钱包日结余
     */
    @PreAuthorize("@ss.hasPermi('system:records:edit')")
    @Log(title = "钱包日结余", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActWalletDayRecords actWalletDayRecords)
    {
        return toAjax(actWalletDayRecordsService.updateActWalletDayRecords(actWalletDayRecords));
    }

    /**
     * 删除钱包日结余
     */
    @PreAuthorize("@ss.hasPermi('system:records:remove')")
    @Log(title = "钱包日结余", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(actWalletDayRecordsService.deleteActWalletDayRecordsByIds(ids));
    }
}
