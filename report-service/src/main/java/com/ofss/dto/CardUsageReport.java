package com.ofss.dto;

public class CardUsageReport {

    private Long cardId;
    private String cardNumber;
    private Long usageCount;

    public CardUsageReport() {
    }

    public CardUsageReport(
            Long cardId,
            String cardNumber,
            Long usageCount) {

        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.usageCount = usageCount;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Long getUsageCount() {
        return usageCount;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setUsageCount(Long usageCount) {
        this.usageCount = usageCount;
    }
}