package com.ruby.project.system.service.impl;

import java.util.List;
import com.ruby.common.utils.DateUtils;
import com.ruby.common.utils.SecurityUtils;
import com.ruby.framework.aspectj.lang.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruby.project.system.mapper.ActWalletMapper;
import com.ruby.project.system.domain.ActWallet;
import com.ruby.project.system.service.IActWalletService;

/**
 * 钱包管理Service业务层处理
 *
 * @author ruby
 * @date 2024-03-10
 */
@Service
public class ActWalletServiceImpl implements IActWalletService
{
    @Autowired
    private ActWalletMapper actWalletMapper;

    /**
     * 查询钱包管理
     *
     * @param id 钱包管理主键
     * @return 钱包管理
     */
    @Override
    public ActWallet selectActWalletById(Long id)
    {
        return actWalletMapper.selectActWalletById(id);
    }

    /**
     * 查询钱包管理列表
     *
     * @param actWallet 钱包管理
     * @return 钱包管理
     */
    @Override
    @DataScope(deptAlias = "act_wallet", userAlias = "act_wallet")
    public List<ActWallet> selectActWalletList(ActWallet actWallet)
    {
        return actWalletMapper.selectActWalletList(actWallet);
    }

    /**
     * 新增钱包管理
     *
     * @param actWallet 钱包管理
     * @return 结果
     */
    @Override
    public int insertActWallet(ActWallet actWallet)
    {
        actWallet.setUserId(SecurityUtils.getUserId());
        actWallet.setDeptId(SecurityUtils.getDeptId());
        actWallet.setCreateBy(SecurityUtils.getUsername());
        actWallet.setCreateTime(DateUtils.getNowDate());
        return actWalletMapper.insertActWallet(actWallet);
    }

    /**
     * 修改钱包管理
     *
     * @param actWallet 钱包管理
     * @return 结果
     */
    @Override
    public int updateActWallet(ActWallet actWallet)
    {
        actWallet.setUpdateBy(SecurityUtils.getUsername());
        actWallet.setUpdateTime(DateUtils.getNowDate());
        return actWalletMapper.updateActWallet(actWallet);
    }

    /**
     * 批量删除钱包管理
     *
     * @param ids 需要删除的钱包管理主键
     * @return 结果
     */
    @Override
    public int deleteActWalletByIds(Long[] ids)
    {
        return actWalletMapper.deleteActWalletByIds(ids);
    }

    /**
     * 删除钱包管理信息
     *
     * @param id 钱包管理主键
     * @return 结果
     */
    @Override
    public int deleteActWalletById(Long id)
    {
        return actWalletMapper.deleteActWalletById(id);
    }
}
