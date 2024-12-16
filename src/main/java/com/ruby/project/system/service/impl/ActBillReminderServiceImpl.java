package com.ruby.project.system.service.impl;

import java.util.List;
import com.ruby.common.utils.DateUtils;
import com.ruby.common.utils.SecurityUtils;
import com.ruby.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruby.project.system.mapper.ActBillReminderMapper;
import com.ruby.project.system.domain.ActBillReminder;
import com.ruby.project.system.service.IActBillReminderService;

/**
 * 账单提醒Service业务层处理
 *
 * @author ruby
 * @date 2024-03-10
 */
@Service
public class ActBillReminderServiceImpl implements IActBillReminderService
{
    @Autowired
    private ActBillReminderMapper actBillReminderMapper;

    /**
     * 查询账单提醒
     *
     * @param id 账单提醒主键
     * @return 账单提醒
     */
    @Override
    public ActBillReminder selectActBillReminderById(Long id)
    {
        return actBillReminderMapper.selectActBillReminderById(id);
    }

    /**
     * 查询账单提醒列表
     *
     * @param actBillReminder 账单提醒
     * @return 账单提醒
     */
    @Override
    @DataScope(deptAlias = "act_bill_reminder", userAlias = "act_bill_reminder")
    public List<ActBillReminder> selectActBillReminderList(ActBillReminder actBillReminder)
    {
        return actBillReminderMapper.selectActBillReminderList(actBillReminder);
    }

    /**
     * 新增账单提醒
     *
     * @param actBillReminder 账单提醒
     * @return 结果
     */
    @Override
    public int insertActBillReminder(ActBillReminder actBillReminder)
    {
        actBillReminder.setUserId(SecurityUtils.getUserId());
        actBillReminder.setDeptId(SecurityUtils.getDeptId());
        actBillReminder.setCreateBy(SecurityUtils.getUsername());
        actBillReminder.setCreateTime(DateUtils.getNowDate());
        return actBillReminderMapper.insertActBillReminder(actBillReminder);
    }

    /**
     * 修改账单提醒
     *
     * @param actBillReminder 账单提醒
     * @return 结果
     */
    @Override
    public int updateActBillReminder(ActBillReminder actBillReminder)
    {
        actBillReminder.setUpdateBy(SecurityUtils.getUsername());
        actBillReminder.setUpdateTime(DateUtils.getNowDate());
        return actBillReminderMapper.updateActBillReminder(actBillReminder);
    }

    /**
     * 批量删除账单提醒
     *
     * @param ids 需要删除的账单提醒主键
     * @return 结果
     */
    @Override
    public int deleteActBillReminderByIds(Long[] ids)
    {
        return actBillReminderMapper.deleteActBillReminderByIds(ids);
    }

    /**
     * 删除账单提醒信息
     *
     * @param id 账单提醒主键
     * @return 结果
     */
    @Override
    public int deleteActBillReminderById(Long id)
    {
        return actBillReminderMapper.deleteActBillReminderById(id);
    }
}
