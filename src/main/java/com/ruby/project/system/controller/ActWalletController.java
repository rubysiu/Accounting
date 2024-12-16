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
import com.ruby.project.system.domain.ActWallet;
import com.ruby.project.system.service.IActWalletService;
import com.ruby.framework.web.controller.BaseController;
import com.ruby.framework.web.domain.AjaxResult;
import com.ruby.common.utils.poi.ExcelUtil;
import com.ruby.framework.web.page.TableDataInfo;

/**
 * 钱包管理Controller
 * 
 * @author ruby
 * @date 2024-03-10
 */
@RestController
@RequestMapping("/system/wallet")
public class ActWalletController extends BaseController
{
    @Autowired
    private IActWalletService actWalletService;

    /**
     * 查询钱包管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:wallet:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActWallet actWallet)
    {
        startPage();
        List<ActWallet> list = actWalletService.selectActWalletList(actWallet);
        return getDataTable(list);
    }

    /**
     * 导出钱包管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:wallet:export')")
    @Log(title = "钱包管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActWallet actWallet)
    {
        List<ActWallet> list = actWalletService.selectActWalletList(actWallet);
        ExcelUtil<ActWallet> util = new ExcelUtil<ActWallet>(ActWallet.class);
        util.exportExcel(response, list, "钱包管理数据");
    }

    /**
     * 获取钱包管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:wallet:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(actWalletService.selectActWalletById(id));
    }

    /**
     * 新增钱包管理
     */
    @PreAuthorize("@ss.hasPermi('system:wallet:add')")
    @Log(title = "钱包管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActWallet actWallet)
    {
        return toAjax(actWalletService.insertActWallet(actWallet));
    }

    /**
     * 修改钱包管理
     */
    @PreAuthorize("@ss.hasPermi('system:wallet:edit')")
    @Log(title = "钱包管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActWallet actWallet)
    {
        return toAjax(actWalletService.updateActWallet(actWallet));
    }

    /**
     * 删除钱包管理
     */
    @PreAuthorize("@ss.hasPermi('system:wallet:remove')")
    @Log(title = "钱包管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(actWalletService.deleteActWalletByIds(ids));
    }
}
