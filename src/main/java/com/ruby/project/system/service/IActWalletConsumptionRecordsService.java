package com.ruby.project.system.service;

import java.util.List;
import com.ruby.project.system.domain.ActWalletConsumptionRecords;

/**
 * 消费记录Service接口
 * 
 * @author ruby
 * @date 2024-03-10
 */
public interface IActWalletConsumptionRecordsService 
{
    /**
     * 查询消费记录
     * 
     * @param id 消费记录主键
     * @return 消费记录
     */
    public ActWalletConsumptionRecords selectActWalletConsumptionRecordsById(Long id);

    /**
     * 查询消费记录列表
     * 
     * @param actWalletConsumptionRecords 消费记录
     * @return 消费记录集合
     */
    public List<ActWalletConsumptionRecords> selectActWalletConsumptionRecordsList(ActWalletConsumptionRecords actWalletConsumptionRecords);

    /**
     * 新增消费记录
     * 
     * @param actWalletConsumptionRecords 消费记录
     * @return 结果
     */
    public int insertActWalletConsumptionRecords(ActWalletConsumptionRecords actWalletConsumptionRecords);

    /**
     * 修改消费记录
     * 
     * @param actWalletConsumptionRecords 消费记录
     * @return 结果
     */
    public int updateActWalletConsumptionRecords(ActWalletConsumptionRecords actWalletConsumptionRecords);

    /**
     * 批量删除消费记录
     * 
     * @param ids 需要删除的消费记录主键集合
     * @return 结果
     */
    public int deleteActWalletConsumptionRecordsByIds(Long[] ids);

    /**
     * 删除消费记录信息
     * 
     * @param id 消费记录主键
     * @return 结果
     */
    public int deleteActWalletConsumptionRecordsById(Long id);
}
