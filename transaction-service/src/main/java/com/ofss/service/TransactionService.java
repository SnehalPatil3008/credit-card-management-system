package com.ofss.service;

import com.ofss.dto.BalanceUpdateRequest;
import com.ofss.dto.TransactionRequest;
import com.ofss.dto.TransactionResponse;
import com.ofss.entity.Transaction;
import com.ofss.enums.TransactionStatus;
import com.ofss.enums.TransactionType;
import com.ofss.exceptions.TransactionExceptionHandler.TransactionException;
import com.ofss.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private RestTemplate restTemplate;

	public TransactionResponse createTransaction(TransactionRequest request) {

		Transaction transaction = new Transaction();

		transaction.setCardId(request.getCardId());
		transaction.setMerchantId(request.getMerchantId());
		transaction.setTransactionType(request.getTransactionType());
		transaction.setAmount(request.getAmount());
		transaction.setTransactionDateTime(request.getTransactionDateTime());
		transaction.setStatus(request.getStatus());
		transaction.setPaymentMode(request.getPaymentMode());

		if (request.getStatus() == TransactionStatus.SUCCESS) {

			BalanceUpdateRequest balanceRequest = new BalanceUpdateRequest();

			balanceRequest.setAmount(request.getAmount());
			balanceRequest.setTransactionType(request.getTransactionType().name());

			restTemplate.postForObject("http://credit-card-service/api/cards/{cardId}/balance", balanceRequest,
					Object.class, request.getCardId());
		}

		if (request.getTransactionType() == TransactionType.PURCHASE
				&& request.getStatus() == TransactionStatus.SUCCESS) {

			int points = request.getAmount().divide(BigDecimal.valueOf(100)).intValue();

			BigDecimal cashback = request.getAmount().multiply(BigDecimal.ONE).divide(BigDecimal.valueOf(100));

			transaction.setRewardPoints(points);
			transaction.setCashbackAmount(cashback);
			BalanceUpdateRequest rewardRequest = new BalanceUpdateRequest();
			rewardRequest.setRewardPoints(points);
			restTemplate.postForObject(
					"http://credit-card-service/api/cards/{cardId}/cashback" + "?cashbackAmount={cashbackAmount}", null,
					Object.class, transaction.getCardId(), cashback);
			restTemplate.postForObject(
					"http://credit-card-service/api/cards/{cardId}/rewards" + "?rewardPoints={rewardPoints}", null,
					Object.class, transaction.getCardId(), points);
		} else {
			transaction.setRewardPoints(0);
			transaction.setCashbackAmount(BigDecimal.ZERO);
		}

		return mapToResponse(transactionRepository.save(transaction));
	}

	public TransactionResponse getTransactionById(Long transactionId) {

		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionException("Transaction not found with ID: " + transactionId));

		return mapToResponse(transaction);
	}

	public List<TransactionResponse> getAllTransactions() {

		List<Transaction> transactions = transactionRepository.findAll();

		List<TransactionResponse> responses = new ArrayList<>();

		for (Transaction transaction : transactions) {
			responses.add(mapToResponse(transaction));
		}

		return responses;
	}

	public TransactionResponse updateTransaction(Long transactionId, TransactionRequest request) {

		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionException("Transaction not found with ID: " + transactionId));

		transaction.setCardId(request.getCardId());
		transaction.setMerchantId(request.getMerchantId());
		transaction.setTransactionType(request.getTransactionType());
		transaction.setAmount(request.getAmount());
		transaction.setTransactionDateTime(request.getTransactionDateTime());
		transaction.setStatus(request.getStatus());
		transaction.setPaymentMode(request.getPaymentMode());

		return mapToResponse(transactionRepository.save(transaction));
	}

	public void deleteTransaction(Long transactionId) {

		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionException("Transaction not found with ID: " + transactionId));

		transactionRepository.delete(transaction);
	}

	private TransactionResponse mapToResponse(Transaction transaction) {

		return new TransactionResponse(transaction.getTransactionId(), transaction.getCardId(),
				transaction.getMerchantId(), transaction.getTransactionType(), transaction.getAmount(),
				transaction.getTransactionDateTime(), transaction.getStatus(), transaction.getPaymentMode(),
				transaction.getRewardPoints(), transaction.getCashbackAmount());
	}

}