package com.ofss.dto;

import java.math.BigDecimal;

public class MonthlySpendingReport {

    private Long customerId;
    private String customerName;
    private String month;
    private BigDecimal totalSpent;

    public MonthlySpendingReport() {
    }

    public MonthlySpendingReport(
            Long customerId,
            String customerName,
            String month,
            BigDecimal totalSpent) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.month = month;
        this.totalSpent = totalSpent;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMonth() {
        return month;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }
}