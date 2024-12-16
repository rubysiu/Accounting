package com.ruby.project.system.service.impl;

import java.util.List;
import com.ruby.common.utils.DateUtils;
import com.ruby.common.utils.SecurityUtils;
import com.ruby.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruby.project.system.mapper.ActBillTypeMapper;
import com.ruby.project.system.domain.ActBillType;
import com.ruby.project.system.service.IActBillTypeService;

/**
 * 账单类型Service业务层处理
 *
 * @author ruby
 * @date 2024-03-10
 */
@Service
public class ActBillTypeServiceImpl implements IActBillTypeService
{
    @Autowired
    private ActBillTypeMapper actBillTypeMapper;

    /**
     * 查询账单类型
     *
     * @param id 账单类型主键
     * @return 账单类型
     */
    @Override
    public ActBillType selectActBillTypeById(Long id)
    {
        return actBillTypeMapper.selectActBillTypeById(id);
    }

    /**
     * 查询账单类型列表
     *
     * @param actBillType 账单类型
     * @return 账单类型
     */
    @Override
    @DataScope(deptAlias = "act_bill_type", userAlias = "act_bill_type")
    public List<ActBillType> selectActBillTypeList(ActBillType actBillType)
    {
        return actBillTypeMapper.selectActBillTypeList(actBillType);
    }

    /**
     * 新增账单类型
     *
     * @param actBillType 账单类型
     * @return 结果
     */
    @Override
    public int insertActBillType(ActBillType actBillType)
    {
        actBillType.setUserId(SecurityUtils.getUserId());
        actBillType.setDeptId(SecurityUtils.getDeptId());
        actBillType.setCreateBy(SecurityUtils.getUsername());
        actBillType.setCreateTime(DateUtils.getNowDate());
        return actBillTypeMapper.insertActBillType(actBillType);
    }

    /**
     * 修改账单类型
     *
     * @param actBillType 账单类型
     * @return 结果
     */
    @Override
    public int updateActBillType(ActBillType actBillType)
    {
        actBillType.setUpdateBy(SecurityUtils.getUsername());
        actBillType.setUpdateTime(DateUtils.getNowDate());
        return actBillTypeMapper.updateActBillType(actBillType);
    }

    /**
     * 批量删除账单类型
     *
     * @param ids 需要删除的账单类型主键
     * @return 结果
     */
    @Override
    public int deleteActBillTypeByIds(Long[] ids)
    {
        return actBillTypeMapper.deleteActBillTypeByIds(ids);
    }

    /**
     * 删除账单类型信息
     *
     * @param id 账单类型主键
     * @return 结果
     */
    @Override
    public int deleteActBillTypeById(Long id)
    {
        return actBillTypeMapper.deleteActBillTypeById(id);
    }
}
