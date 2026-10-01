package com.ofss.entity;

import com.ofss.enums.TransactionStatus;
import com.ofss.enums.TransactionType;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "CARD_TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "card_id", nullable = false)
	private Long cardId;

	@Column(name = "merchant_id")
	private Long merchantId;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type", nullable = false, length = 10)
	private TransactionType transactionType;

	@Column(name = "amount", nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;

	@Column(name = "transaction_date_time", nullable = false)
	private LocalDateTime transactionDateTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 10)
	private TransactionStatus status;

	@Column(name = "payment_mode", length = 30)
	private String paymentMode;

	@Column(name = "reward_points", nullable = false)
	private Integer rewardPoints = 0;

	@Column(
	        name = "cashback_amount",
	        nullable = false,
	        precision = 15,
	        scale = 2
	)
	private BigDecimal cashbackAmount = BigDecimal.ZERO;
	
	
	public Transaction() {
	}

	@PrePersist
	public void prePersist() {
		if (transactionDateTime == null) {
			transactionDateTime = LocalDateTime.now();
		}
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

	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
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
	
	public Integer getRewardPoints() {
	    return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
	    this.rewardPoints = rewardPoints;
	}

	public BigDecimal getCashbackAmount() {
	    return cashbackAmount;
	}

	public void setCashbackAmount(BigDecimal cashbackAmount) {
	    this.cashbackAmount = cashbackAmount;
	}
}