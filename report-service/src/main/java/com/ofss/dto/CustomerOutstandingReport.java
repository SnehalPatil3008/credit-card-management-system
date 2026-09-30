package com.ofss.dto;

import java.math.BigDecimal;

public class CustomerOutstandingReport {

    private Long customerId;
    private String customerName;
    private BigDecimal outstandingAmount;

    public CustomerOutstandingReport() {
    }

    public CustomerOutstandingReport(
            Long customerId,
            String customerName,
            BigDecimal outstandingAmount) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.outstandingAmount = outstandingAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOutstandingAmount(
            BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
}