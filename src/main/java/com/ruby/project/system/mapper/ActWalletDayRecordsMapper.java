package com.ruby.project.system.mapper;

import java.util.List;
import com.ruby.project.system.domain.ActWalletDayRecords;

/**
 * 钱包日结余Mapper接口
 * 
 * @author ruby
 * @date 2024-03-10
 */
public interface ActWalletDayRecordsMapper 
{
    /**
     * 查询钱包日结余
     * 
     * @param id 钱包日结余主键
     * @return 钱包日结余
     */
    public ActWalletDayRecords selectActWalletDayRecordsById(Long id);

    /**
     * 查询钱包日结余列表
     * 
     * @param actWalletDayRecords 钱包日结余
     * @return 钱包日结余集合
     */
    public List<ActWalletDayRecords> selectActWalletDayRecordsList(ActWalletDayRecords actWalletDayRecords);

    /**
     * 新增钱包日结余
     * 
     * @param actWalletDayRecords 钱包日结余
     * @return 结果
     */
    public int insertActWalletDayRecords(ActWalletDayRecords actWalletDayRecords);

    /**
     * 修改钱包日结余
     * 
     * @param actWalletDayRecords 钱包日结余
     * @return 结果
     */
    public int updateActWalletDayRecords(ActWalletDayRecords actWalletDayRecords);

    /**
     * 删除钱包日结余
     * 
     * @param id 钱包日结余主键
     * @return 结果
     */
    public int deleteActWalletDayRecordsById(Long id);

    /**
     * 批量删除钱包日结余
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActWalletDayRecordsByIds(Long[] ids);
}
