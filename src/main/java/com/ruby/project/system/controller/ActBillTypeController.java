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
import com.ruby.project.system.domain.ActBillType;
import com.ruby.project.system.service.IActBillTypeService;
import com.ruby.framework.web.controller.BaseController;
import com.ruby.framework.web.domain.AjaxResult;
import com.ruby.common.utils.poi.ExcelUtil;
import com.ruby.framework.web.page.TableDataInfo;

/**
 * 账单类型Controller
 *
 * @author ruby
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/system/type")
public class ActBillTypeController extends BaseController
{
    @Autowired
    private IActBillTypeService actBillTypeService;

    /**
     * 查询账单类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActBillType actBillType)
    {
//        startPage();
        List<ActBillType> list = actBillTypeService.selectActBillTypeList(actBillType);
        return getDataTable(list);
    }

    /**
     * 导出账单类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "账单类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActBillType actBillType)
    {
        List<ActBillType> list = actBillTypeService.selectActBillTypeList(actBillType);
        ExcelUtil<ActBillType> util = new ExcelUtil<ActBillType>(ActBillType.class);
        util.exportExcel(response, list, "账单类型数据");
    }

    /**
     * 获取账单类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(actBillTypeService.selectActBillTypeById(id));
    }

    /**
     * 新增账单类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "账单类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActBillType actBillType)
    {
        return toAjax(actBillTypeService.insertActBillType(actBillType));
    }

    /**
     * 修改账单类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "账单类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActBillType actBillType)
    {
        return toAjax(actBillTypeService.updateActBillType(actBillType));
    }

    /**
     * 删除账单类型
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "账单类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(actBillTypeService.deleteActBillTypeByIds(ids));
    }
}
