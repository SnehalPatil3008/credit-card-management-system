package com.ofss.dto;

import com.ofss.entity.CardType;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCardRequest {
    @NotNull @Positive private Long customerId;
    @NotBlank @Pattern(regexp = "^[0-9]{16}$", message = "Card number must contain exactly 16 digits") private String cardNumber;
    @NotNull private CardType cardType;
    @NotNull @DecimalMin(value = "0.01") @Digits(integer = 13, fraction = 2) private BigDecimal creditLimit;
    @NotNull @Future private LocalDate expiryDate;
    public CreditCardRequest() { }
    public Long getCustomerId() { return customerId; } public void setCustomerId(Long v) { customerId = v; }
    public String getCardNumber() { return cardNumber; } public void setCardNumber(String v) { cardNumber = v; }
    public CardType getCardType() { return cardType; } public void setCardType(CardType v) { cardType = v; }
    public BigDecimal getCreditLimit() { return creditLimit; } public void setCreditLimit(BigDecimal v) { creditLimit = v; }
    public LocalDate getExpiryDate() { return expiryDate; } public void setExpiryDate(LocalDate v) { expiryDate = v; }
}
