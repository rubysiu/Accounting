package com.ruby.project.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruby.common.utils.DateUtils;
import com.ruby.common.utils.PayOrderAnalysisUtil;
import com.ruby.common.utils.SecurityUtils;
import com.ruby.framework.aspectj.lang.annotation.DataScope;
import com.ruby.project.system.domain.ActWallet;
import com.ruby.project.system.service.IActWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruby.project.system.mapper.ActBillingManageMapper;
import com.ruby.project.system.domain.ActBillingManage;
import com.ruby.project.system.service.IActBillingManageService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账单管理Service业务层处理
 *
 * @author ruby
 * @date 2024-03-10
 */
@Service
public class ActBillingManageServiceImpl implements IActBillingManageService
{
    @Autowired
    private ActBillingManageMapper actBillingManageMapper;

    /**
     * 查询账单管理
     *
     * @param id 账单管理主键
     * @return 账单管理
     */
    @Override
    public ActBillingManage selectActBillingManageById(Long id)
    {
        return actBillingManageMapper.selectActBillingManageById(id);
    }

    /**
     * 查询账单管理列表
     *
     * @param actBillingManage 账单管理
     * @return 账单管理
     */
    @Override
    @DataScope(deptAlias = "act_billing_manage", userAlias = "act_billing_manage")
    public List<ActBillingManage> selectActBillingManageList(ActBillingManage actBillingManage)
    {
        return actBillingManageMapper.selectActBillingManageList(actBillingManage);
    }

    /**
     * 新增账单管理
     *
     * @param actBillingManage 账单管理
     * @return 结果
     */
    @Override
    public int insertActBillingManage(ActBillingManage actBillingManage)
    {
        actBillingManage.setUserId(SecurityUtils.getUserId());
        actBillingManage.setDeptId(SecurityUtils.getDeptId());
        actBillingManage.setCreateBy(SecurityUtils.getUsername());
        actBillingManage.setCreateTime(DateUtils.getNowDate());
        return actBillingManageMapper.insertActBillingManage(actBillingManage);
    }

    /**
     * 修改账单管理
     *
     * @param actBillingManage 账单管理
     * @return 结果
     */
    @Override
    public int updateActBillingManage(ActBillingManage actBillingManage)
    {
        actBillingManage.setUpdateBy(SecurityUtils.getUsername());
        actBillingManage.setUpdateTime(DateUtils.getNowDate());
        return actBillingManageMapper.updateActBillingManage(actBillingManage);
    }

    /**
     * 批量删除账单管理
     *
     * @param ids 需要删除的账单管理主键
     * @return 结果
     */
    @Override
    public int deleteActBillingManageByIds(Long[] ids)
    {
        return actBillingManageMapper.deleteActBillingManageByIds(ids);
    }

    /**
     * 删除账单管理信息
     *
     * @param id 账单管理主键
     * @return 结果
     */
    @Override
    public int deleteActBillingManageById(Long id)
    {
        return actBillingManageMapper.deleteActBillingManageById(id);
    }
    @Autowired
    private IActWalletService actWalletService;
    @Override
    public List<ActBillingManage> insertActBillingManage2Csv(String importType, MultipartFile file) {
        List<Map<String, Object>> maps = null;
        List<ActBillingManage> actBillingManages = new ArrayList<>();
        List<ActWallet> actWallets = actWalletService.selectActWalletList(new ActWallet());
        if (importType.equals("wechat")) {
            maps = PayOrderAnalysisUtil.analysisWechatPayOrder(file);
        }else if (importType.equals("alipay")) {
            maps = PayOrderAnalysisUtil.analysisAlipayPayOrder(file);
            for (Map<String, Object> map : maps) {
                if (map.get("5").equals("不计收支")||map.get("8").equals("交易关闭"))continue;
                ActBillingManage actBillingManage = new ActBillingManage();
                actBillingManage.setUserId(SecurityUtils.getUserId());
                actBillingManage.setDeptId(SecurityUtils.getDeptId());
                actBillingManage.setCreateBy(SecurityUtils.getUsername());
                actBillingManage.setCreateTime(DateUtils.parseDate(map.get("0")));
                if(map.get("5").equals("支出")){
                    actBillingManage.setCollection4Disbursement("0");
                    actBillingManage.setMoney(new BigDecimal(String.valueOf(map.get("6"))).negate());
                }else {
                    actBillingManage.setCollection4Disbursement("1");
                    actBillingManage.setMoney(new BigDecimal(String.valueOf(map.get("6"))));
                }
                actBillingManage.setName(map.get("1")+"-"+map.get("2")+"-"+map.get("4"));
                actBillingManage.setWay(String.valueOf(map.get("7")));
                //断词与账户匹配
                for (ActWallet actWallet : actWallets) {
                    String way = actBillingManage.getWay();
                    if(way.contains("账户余额")||way.contains("余额宝")||way.contains("花呗")||way.contains("借呗")||way.contains("余利宝")){
                        way = "支付宝";
                    }
                    if (way.contains(actWallet.getName())){
                        actBillingManage.setWalletId(actWallet.getId());
                        break;
                    }
                }
                actBillingManageMapper.insertActBillingManage(actBillingManage);
                actBillingManages.add(actBillingManage);
            }
        }
        return actBillingManages;
    }
}
