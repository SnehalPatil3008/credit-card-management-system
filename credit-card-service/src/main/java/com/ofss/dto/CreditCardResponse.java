package com.ofss.dto;

import com.ofss.entity.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCardResponse {
	private Long cardId;
	private Long customerId;
	private String cardNumber;
	private CardType cardType;
	private BigDecimal creditLimit;
	private BigDecimal availableCredit;
	private BigDecimal outstandingAmount;
	private LocalDate expiryDate;
	private CardStatus cardStatus;
	private Integer availableRewardPoints;
	public CreditCardResponse() {
	}

	public CreditCardResponse(Long cardId, Long customerId, String cardNumber, CardType cardType,
			BigDecimal creditLimit, BigDecimal availableCredit, BigDecimal outstandingAmount, LocalDate expiryDate,
			CardStatus cardStatus,Integer availableRewardPoints) {
		this.cardId = cardId;
		this.customerId = customerId;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.creditLimit = creditLimit;
		this.availableCredit = availableCredit;
		this.outstandingAmount = outstandingAmount;
		this.expiryDate = expiryDate;
		this.cardStatus = cardStatus;
		this.availableRewardPoints=availableRewardPoints;
	}

	public Long getCardId() {
		return cardId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public CardType getCardType() {
		return cardType;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public BigDecimal getAvailableCredit() {
		return availableCredit;
	}

	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public CardStatus getCardStatus() {
		return cardStatus;
	}

	public Integer getAvailableRewardPoints() {
		return availableRewardPoints;
	}
	
	
}
