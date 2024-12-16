package com.ruby.project.system.service.impl;

import java.util.List;
import com.ruby.common.utils.DateUtils;
import com.ruby.common.utils.SecurityUtils;
import com.ruby.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruby.project.system.mapper.ActWalletConsumptionRecordsMapper;
import com.ruby.project.system.domain.ActWalletConsumptionRecords;
import com.ruby.project.system.service.IActWalletConsumptionRecordsService;

/**
 * 消费记录Service业务层处理
 *
 * @author ruby
 * @date 2024-03-10
 */
@Service
public class ActWalletConsumptionRecordsServiceImpl implements IActWalletConsumptionRecordsService
{
    @Autowired
    private ActWalletConsumptionRecordsMapper actWalletConsumptionRecordsMapper;

    /**
     * 查询消费记录
     *
     * @param id 消费记录主键
     * @return 消费记录
     */
    @Override
    public ActWalletConsumptionRecords selectActWalletConsumptionRecordsById(Long id)
    {
        return actWalletConsumptionRecordsMapper.selectActWalletConsumptionRecordsById(id);
    }

    /**
     * 查询消费记录列表
     *
     * @param actWalletConsumptionRecords 消费记录
     * @return 消费记录
     */
    @Override
    @DataScope(deptAlias = "act_wallet_consumption_records", userAlias = "act_wallet_consumption_records")
    public List<ActWalletConsumptionRecords> selectActWalletConsumptionRecordsList(ActWalletConsumptionRecords actWalletConsumptionRecords)
    {
        return actWalletConsumptionRecordsMapper.selectActWalletConsumptionRecordsList(actWalletConsumptionRecords);
    }

    /**
     * 新增消费记录
     *
     * @param actWalletConsumptionRecords 消费记录
     * @return 结果
     */
    @Override
    public int insertActWalletConsumptionRecords(ActWalletConsumptionRecords actWalletConsumptionRecords)
    {
        actWalletConsumptionRecords.setUserId(SecurityUtils.getUserId());
        actWalletConsumptionRecords.setDeptId(SecurityUtils.getDeptId());
        actWalletConsumptionRecords.setCreateBy(SecurityUtils.getUsername());
        actWalletConsumptionRecords.setCreateTime(DateUtils.getNowDate());
        return actWalletConsumptionRecordsMapper.insertActWalletConsumptionRecords(actWalletConsumptionRecords);
    }

    /**
     * 修改消费记录
     *
     * @param actWalletConsumptionRecords 消费记录
     * @return 结果
     */
    @Override
    public int updateActWalletConsumptionRecords(ActWalletConsumptionRecords actWalletConsumptionRecords)
    {
        actWalletConsumptionRecords.setUpdateBy(SecurityUtils.getUsername());
        actWalletConsumptionRecords.setUpdateTime(DateUtils.getNowDate());
        return actWalletConsumptionRecordsMapper.updateActWalletConsumptionRecords(actWalletConsumptionRecords);
    }

    /**
     * 批量删除消费记录
     *
     * @param ids 需要删除的消费记录主键
     * @return 结果
     */
    @Override
    public int deleteActWalletConsumptionRecordsByIds(Long[] ids)
    {
        return actWalletConsumptionRecordsMapper.deleteActWalletConsumptionRecordsByIds(ids);
    }

    /**
     * 删除消费记录信息
     *
     * @param id 消费记录主键
     * @return 结果
     */
    @Override
    public int deleteActWalletConsumptionRecordsById(Long id)
    {
        return actWalletConsumptionRecordsMapper.deleteActWalletConsumptionRecordsById(id);
    }
}
