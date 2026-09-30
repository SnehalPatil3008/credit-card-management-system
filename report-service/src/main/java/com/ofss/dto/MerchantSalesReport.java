package com.ofss.dto;

import java.math.BigDecimal;

public class MerchantSalesReport {

    private Long merchantId;
    private String merchantName;
    private BigDecimal totalSales;

    public MerchantSalesReport() {
    }

    public MerchantSalesReport(
            Long merchantId,
            String merchantName,
            BigDecimal totalSales) {

        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.totalSales = totalSales;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }
}