package com.ruby.project.system.service;

import java.util.List;
import com.ruby.project.system.domain.ActBillReminder;

/**
 * 账单提醒Service接口
 * 
 * @author ruby
 * @date 2024-03-10
 */
public interface IActBillReminderService 
{
    /**
     * 查询账单提醒
     * 
     * @param id 账单提醒主键
     * @return 账单提醒
     */
    public ActBillReminder selectActBillReminderById(Long id);

    /**
     * 查询账单提醒列表
     * 
     * @param actBillReminder 账单提醒
     * @return 账单提醒集合
     */
    public List<ActBillReminder> selectActBillReminderList(ActBillReminder actBillReminder);

    /**
     * 新增账单提醒
     * 
     * @param actBillReminder 账单提醒
     * @return 结果
     */
    public int insertActBillReminder(ActBillReminder actBillReminder);

    /**
     * 修改账单提醒
     * 
     * @param actBillReminder 账单提醒
     * @return 结果
     */
    public int updateActBillReminder(ActBillReminder actBillReminder);

    /**
     * 批量删除账单提醒
     * 
     * @param ids 需要删除的账单提醒主键集合
     * @return 结果
     */
    public int deleteActBillReminderByIds(Long[] ids);

    /**
     * 删除账单提醒信息
     * 
     * @param id 账单提醒主键
     * @return 结果
     */
    public int deleteActBillReminderById(Long id);
}
