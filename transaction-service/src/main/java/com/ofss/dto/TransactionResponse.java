package com.ofss.dto;

import com.ofss.enums.TransactionStatus;
import com.ofss.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private Long transactionId;
    private Long cardId;
    private Long merchantId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime transactionDateTime;
    private TransactionStatus status;
    private String paymentMode;
    private Integer rewardPoints;
    private BigDecimal cashbackAmount;
    
    public TransactionResponse() {
    }

    public TransactionResponse(
            Long transactionId,
            Long cardId,
            Long merchantId,
            TransactionType transactionType,
            BigDecimal amount,
            LocalDateTime transactionDateTime,
            TransactionStatus status,
            String paymentMode,
            Integer rewardPoints,
            BigDecimal cashbackAmount) {

        this.transactionId = transactionId;
        this.cardId = cardId;
        this.merchantId = merchantId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
        this.status = status;
        this.paymentMode = paymentMode;
        this.rewardPoints = rewardPoints;
        this.cashbackAmount = cashbackAmount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCardId() {
        return cardId;
    }

    public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(
            LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public BigDecimal getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(BigDecimal cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }
}