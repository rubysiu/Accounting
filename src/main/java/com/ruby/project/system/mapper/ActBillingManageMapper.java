package com.ruby.project.system.mapper;

import java.util.List;
import com.ruby.project.system.domain.ActBillingManage;

/**
 * 账单管理Mapper接口
 *
 * @author ruby
 * @date 2024-03-10
 */
public interface ActBillingManageMapper
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
     * 删除账单管理
     *
     * @param id 账单管理主键
     * @return 结果
     */
    public int deleteActBillingManageById(Long id);

    /**
     * 批量删除账单管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActBillingManageByIds(Long[] ids);

    void batchInsertActBillingManage(List<ActBillingManage> actBillingManages);
}
