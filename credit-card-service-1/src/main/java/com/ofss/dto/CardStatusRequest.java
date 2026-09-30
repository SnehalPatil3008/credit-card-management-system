package com.ofss.dto;

import com.ofss.entity.CardStatus;
import jakarta.validation.constraints.NotNull;
public class CardStatusRequest { @NotNull private CardStatus cardStatus; public CardStatusRequest() { } public CardStatus getCardStatus() { return cardStatus; } public void setCardStatus(CardStatus v) { cardStatus = v; } }
