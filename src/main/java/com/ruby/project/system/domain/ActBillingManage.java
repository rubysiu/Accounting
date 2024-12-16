package com.ruby.project.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruby.framework.aspectj.lang.annotation.Excel;
import com.ruby.framework.web.domain.BaseEntity;

/**
 * 账单管理对象 act_billing_manage
 * 
 * @author ruby
 * @date 2024-03-10
 */
public class ActBillingManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 分类id */
    @Excel(name = "分类id")
    private Long categoryId;

    /** 钱包id */
    @Excel(name = "钱包id")
    private Long walletId;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String way;

    /** 收支 */
    @Excel(name = "收支")
    private String collection4Disbursement;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;

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
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setWalletId(Long walletId) 
    {
        this.walletId = walletId;
    }

    public Long getWalletId() 
    {
        return walletId;
    }
    public void setWay(String way) 
    {
        this.way = way;
    }

    public String getWay() 
    {
        return way;
    }
    public void setCollection4Disbursement(String collection4Disbursement) 
    {
        this.collection4Disbursement = collection4Disbursement;
    }

    public String getCollection4Disbursement() 
    {
        return collection4Disbursement;
    }
    public void setMoney(BigDecimal money) 
    {
        this.money = money;
    }

    public BigDecimal getMoney() 
    {
        return money;
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
            .append("categoryId", getCategoryId())
            .append("walletId", getWalletId())
            .append("way", getWay())
            .append("collection4Disbursement", getCollection4Disbursement())
            .append("money", getMoney())
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
