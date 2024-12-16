package com.ruby.project.system.mapper;

import java.util.List;
import com.ruby.project.system.domain.ActBillType;

/**
 * 账单类型Mapper接口
 * 
 * @author ruby
 * @date 2024-03-10
 */
public interface ActBillTypeMapper 
{
    /**
     * 查询账单类型
     * 
     * @param id 账单类型主键
     * @return 账单类型
     */
    public ActBillType selectActBillTypeById(Long id);

    /**
     * 查询账单类型列表
     * 
     * @param actBillType 账单类型
     * @return 账单类型集合
     */
    public List<ActBillType> selectActBillTypeList(ActBillType actBillType);

    /**
     * 新增账单类型
     * 
     * @param actBillType 账单类型
     * @return 结果
     */
    public int insertActBillType(ActBillType actBillType);

    /**
     * 修改账单类型
     * 
     * @param actBillType 账单类型
     * @return 结果
     */
    public int updateActBillType(ActBillType actBillType);

    /**
     * 删除账单类型
     * 
     * @param id 账单类型主键
     * @return 结果
     */
    public int deleteActBillTypeById(Long id);

    /**
     * 批量删除账单类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActBillTypeByIds(Long[] ids);
}
