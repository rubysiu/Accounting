package com.ruby.project.system.service.impl;

import java.util.List;
import com.ruby.common.utils.DateUtils;
import com.ruby.common.utils.SecurityUtils;
import com.ruby.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruby.project.system.mapper.ActWalletDayRecordsMapper;
import com.ruby.project.system.domain.ActWalletDayRecords;
import com.ruby.project.system.service.IActWalletDayRecordsService;

/**
 * 钱包日结余Service业务层处理
 *
 * @author ruby
 * @date 2024-03-10
 */
@Service
public class ActWalletDayRecordsServiceImpl implements IActWalletDayRecordsService
{
    @Autowired
    private ActWalletDayRecordsMapper actWalletDayRecordsMapper;

    /**
     * 查询钱包日结余
     *
     * @param id 钱包日结余主键
     * @return 钱包日结余
     */
    @Override
    public ActWalletDayRecords selectActWalletDayRecordsById(Long id)
    {
        return actWalletDayRecordsMapper.selectActWalletDayRecordsById(id);
    }

    /**
     * 查询钱包日结余列表
     *
     * @param actWalletDayRecords 钱包日结余
     * @return 钱包日结余
     */
    @Override
    @DataScope(deptAlias = "act_wallet_day_records", userAlias = "act_wallet_day_records")
    public List<ActWalletDayRecords> selectActWalletDayRecordsList(ActWalletDayRecords actWalletDayRecords)
    {
        return actWalletDayRecordsMapper.selectActWalletDayRecordsList(actWalletDayRecords);
    }

    /**
     * 新增钱包日结余
     *
     * @param actWalletDayRecords 钱包日结余
     * @return 结果
     */
    @Override
    public int insertActWalletDayRecords(ActWalletDayRecords actWalletDayRecords)
    {
        actWalletDayRecords.setUserId(SecurityUtils.getUserId());
        actWalletDayRecords.setDeptId(SecurityUtils.getDeptId());
        actWalletDayRecords.setCreateBy(SecurityUtils.getUsername());
        actWalletDayRecords.setCreateTime(DateUtils.getNowDate());
        return actWalletDayRecordsMapper.insertActWalletDayRecords(actWalletDayRecords);
    }

    /**
     * 修改钱包日结余
     *
     * @param actWalletDayRecords 钱包日结余
     * @return 结果
     */
    @Override
    public int updateActWalletDayRecords(ActWalletDayRecords actWalletDayRecords)
    {
        actWalletDayRecords.setUpdateBy(SecurityUtils.getUsername());
        actWalletDayRecords.setUpdateTime(DateUtils.getNowDate());
        return actWalletDayRecordsMapper.updateActWalletDayRecords(actWalletDayRecords);
    }

    /**
     * 批量删除钱包日结余
     *
     * @param ids 需要删除的钱包日结余主键
     * @return 结果
     */
    @Override
    public int deleteActWalletDayRecordsByIds(Long[] ids)
    {
        return actWalletDayRecordsMapper.deleteActWalletDayRecordsByIds(ids);
    }

    /**
     * 删除钱包日结余信息
     *
     * @param id 钱包日结余主键
     * @return 结果
     */
    @Override
    public int deleteActWalletDayRecordsById(Long id)
    {
        return actWalletDayRecordsMapper.deleteActWalletDayRecordsById(id);
    }
}
