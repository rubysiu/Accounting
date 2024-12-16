package com.ruby.project.system.service;

import java.util.List;
import com.ruby.project.system.domain.ActBillingManage;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账单管理Service接口
 *
 * @author ruby
 * @date 2024-03-10
 */
public interface IActBillingManageService
{
    /**
     * 查询账单管理
     *
     * @param id 账单管理主键
     * @return 账单管理
     */
    public ActBillingManage selectActBillingManageById(Long id);

    /**
     * 查询账单管理列表
     *
     * @param actBillingManage 账单管理
     * @return 账单管理集合
     */
    public List<ActBillingManage> selectActBillingManageList(ActBillingManage actBillingManage);

    /**
     * 新增账单管理
     *
     * @param actBillingManage 账单管理
     * @return 结果
     */
    public int insertActBillingManage(ActBillingManage actBillingManage);

    /**
     * 修改账单管理
     *
     * @param actBillingManage 账单管理
     * @return 结果
     */
    public int updateActBillingManage(ActBillingManage actBillingManage);

    /**
     * 批量删除账单管理
     *
     * @param ids 需要删除的账单管理主键集合
     * @return 结果
     */
    public int deleteActBillingManageByIds(Long[] ids);

    /**
     * 删除账单管理信息
     *
     * @param id 账单管理主键
     * @return 结果
     */
    public int deleteActBillingManageById(Long id);

    List<ActBillingManage> insertActBillingManage2Csv(String importType, MultipartFile file);
}
