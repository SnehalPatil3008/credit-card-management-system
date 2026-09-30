package com.ofss.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CREDIT_CARD", uniqueConstraints = @UniqueConstraint(name = "uq_credit_card_number", columnNames = "card_number"))
public class CreditCard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "card_id") private Long cardId;
    @Column(name = "customer_id", nullable = false) private Long customerId;
    @Column(name = "card_number", nullable = false, length = 16) private String cardNumber;
    @Enumerated(EnumType.STRING) @Column(name = "card_type", nullable = false, length = 20) private CardType cardType;
    @Column(name = "credit_limit", nullable = false, precision = 15, scale = 2) private BigDecimal creditLimit;
    @Column(name = "available_credit", nullable = false, precision = 15, scale = 2) private BigDecimal availableCredit;
    @Column(name = "outstanding_amount", nullable = false, precision = 15, scale = 2) private BigDecimal outstandingAmount;
    @Column(name = "expiry_date", nullable = false) private LocalDate expiryDate;
    @Enumerated(EnumType.STRING) @Column(name = "card_status", nullable = false, length = 20) private CardStatus cardStatus;
    public CreditCard() { }
    public Long getCardId() { return cardId; } public void setCardId(Long v) { cardId = v; }
    public Long getCustomerId() { return customerId; } public void setCustomerId(Long v) { customerId = v; }
    public String getCardNumber() { return cardNumber; } public void setCardNumber(String v) { cardNumber = v; }
    public CardType getCardType() { return cardType; } public void setCardType(CardType v) { cardType = v; }
    public BigDecimal getCreditLimit() { return creditLimit; } public void setCreditLimit(BigDecimal v) { creditLimit = v; }
    public BigDecimal getAvailableCredit() { return availableCredit; } public void setAvailableCredit(BigDecimal v) { availableCredit = v; }
    public BigDecimal getOutstandingAmount() { return outstandingAmount; } public void setOutstandingAmount(BigDecimal v) { outstandingAmount = v; }
    public LocalDate getExpiryDate() { return expiryDate; } public void setExpiryDate(LocalDate v) { expiryDate = v; }
    public CardStatus getCardStatus() { return cardStatus; } public void setCardStatus(CardStatus v) { cardStatus = v; }
}
