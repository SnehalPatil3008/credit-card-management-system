package com.ofss.dto;

import java.math.BigDecimal;

public class CustomerPaymentReport {

    private Long customerId;
    private String customerName;
    private BigDecimal totalPayment;

    public CustomerPaymentReport() {
    }

    public CustomerPaymentReport(
            Long customerId,
            String customerName,
            BigDecimal totalPayment) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPayment = totalPayment;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }
}