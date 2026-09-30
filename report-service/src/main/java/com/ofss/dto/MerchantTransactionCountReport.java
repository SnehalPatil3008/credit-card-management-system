package com.ofss.dto;

public class MerchantTransactionCountReport {

    private Long merchantId;
    private String merchantName;
    private Long transactionCount;

    public MerchantTransactionCountReport() {
    }

    public MerchantTransactionCountReport(
            Long merchantId,
            String merchantName,
            Long transactionCount) {

        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.transactionCount = transactionCount;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public Long getTransactionCount() {
        return transactionCount;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }
}