package com.ruby.project.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruby.framework.aspectj.lang.annotation.Excel;
import com.ruby.framework.web.domain.BaseEntity;

/**
 * 消费记录对象 act_wallet_consumption_records
 * 
 * @author ruby
 * @date 2024-03-10
 */
public class ActWalletConsumptionRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;

    /** 结前金额 */
    @Excel(name = "结前金额")
    private BigDecimal preMoney;

    /** 结余金额 */
    @Excel(name = "结余金额")
    private BigDecimal balanceMoney;

    /** 收支 */
    @Excel(name = "收支")
    private Integer type;

    /** 钱包id */
    @Excel(name = "钱包id")
    private Long walletId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
    }
    public void setPreMoney(BigDecimal preMoney) 
    {
        this.preMoney = preMoney;
    }

    public BigDecimal getPreMoney() 
    {
        return preMoney;
    }
    public void setBalanceMoney(BigDecimal balanceMoney) 
    {
        this.balanceMoney = balanceMoney;
    }

    public BigDecimal getBalanceMoney() 
    {
        return balanceMoney;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setWalletId(Long walletId) 
    {
        this.walletId = walletId;
    }

    public Long getWalletId() 
    {
        return walletId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("money", getMoney())
            .append("preMoney", getPreMoney())
            .append("balanceMoney", getBalanceMoney())
            .append("type", getType())
            .append("walletId", getWalletId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
