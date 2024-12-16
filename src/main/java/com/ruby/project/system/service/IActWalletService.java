package com.ruby.project.system.service;

import java.util.List;
import com.ruby.project.system.domain.ActWallet;

/**
 * 钱包管理Service接口
 * 
 * @author ruby
 * @date 2024-03-10
 */
public interface IActWalletService 
{
    /**
     * 查询钱包管理
     * 
     * @param id 钱包管理主键
     * @return 钱包管理
     */
    public ActWallet selectActWalletById(Long id);

    /**
     * 查询钱包管理列表
     * 
     * @param actWallet 钱包管理
     * @return 钱包管理集合
     */
    public List<ActWallet> selectActWalletList(ActWallet actWallet);

    /**
     * 新增钱包管理
     * 
     * @param actWallet 钱包管理
     * @return 结果
     */
    public int insertActWallet(ActWallet actWallet);

    /**
     * 修改钱包管理
     * 
     * @param actWallet 钱包管理
     * @return 结果
     */
    public int updateActWallet(ActWallet actWallet);

    /**
     * 批量删除钱包管理
     * 
     * @param ids 需要删除的钱包管理主键集合
     * @return 结果
     */
    public int deleteActWalletByIds(Long[] ids);

    /**
     * 删除钱包管理信息
     * 
     * @param id 钱包管理主键
     * @return 结果
     */
    public int deleteActWalletById(Long id);
}
