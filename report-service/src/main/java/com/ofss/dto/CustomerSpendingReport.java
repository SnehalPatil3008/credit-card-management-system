package com.ofss.dto;

import java.math.BigDecimal;

public class CustomerSpendingReport {

    private Long customerId;
    private String customerName;
    private BigDecimal totalSpent;

    public CustomerSpendingReport() {
    }

    public CustomerSpendingReport(
            Long customerId,
            String customerName,
            BigDecimal totalSpent) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.totalSpent = totalSpent;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
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

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }
}