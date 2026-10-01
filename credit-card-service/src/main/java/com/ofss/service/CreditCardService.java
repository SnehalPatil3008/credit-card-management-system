package com.ofss.service;

import com.ofss.dto.*;

import java.math.BigDecimal;
import java.util.List;

public interface CreditCardService {
	CreditCardResponse issueCard(CreditCardRequest request);

	CreditCardResponse getCardById(Long cardId);

	List<CreditCardResponse> getAllCards();

	List<CreditCardResponse> getCardsByCustomerId(Long customerId);

	CreditCardResponse updateCard(Long cardId, CreditCardUpdateRequest request);

	CreditCardResponse changeCardStatus(Long cardId, CardStatusRequest request);

	CreditCardResponse applyCashback(Long cardId, BigDecimal cashbackAmount);

	CreditCardResponse updateBalance(Long cardId, BalanceUpdateRequest request);

	CreditCardResponse updateRewardPoints(Long cardId, Integer rewardPoints);
}
