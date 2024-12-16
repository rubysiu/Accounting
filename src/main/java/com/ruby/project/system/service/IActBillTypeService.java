package com.ruby.project.system.service;

import java.util.List;
import com.ruby.project.system.domain.ActBillType;

/**
 * 账单类型Service接口
 * 
 * @author ruby
 * @date 2024-03-10
 */
public interface IActBillTypeService 
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
     * 批量删除账单类型
     * 
     * @param ids 需要删除的账单类型主键集合
     * @return 结果
     */
    public int deleteActBillTypeByIds(Long[] ids);

    /**
     * 删除账单类型信息
     * 
     * @param id 账单类型主键
     * @return 结果
     */
    public int deleteActBillTypeById(Long id);
}
