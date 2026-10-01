package com.ofss.dto;

import com.ofss.entity.CardType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCardUpdateRequest {
	@NotNull
	private CardType cardType;
	@NotNull
	@DecimalMin(value = "0.01")
	@Digits(integer = 13, fraction = 2)
	private BigDecimal creditLimit;
	@NotNull
	@Future
	private LocalDate expiryDate;

	public CreditCardUpdateRequest() {
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType v) {
		cardType = v;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal v) {
		creditLimit = v;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate v) {
		expiryDate = v;
	}
}
