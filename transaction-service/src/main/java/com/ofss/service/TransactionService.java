package com.ofss.service;

import com.ofss.dto.TransactionRequest;
import com.ofss.dto.TransactionResponse;
import com.ofss.entity.Transaction;
import com.ofss.exceptions.TransactionExceptionHandler.TransactionException;
import com.ofss.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public TransactionResponse createTransaction(TransactionRequest request) {

		Transaction transaction = new Transaction();

		transaction.setCardId(request.getCardId());
		transaction.setMerchantId(request.getMerchantId());
		transaction.setTransactionType(request.getTransactionType());
		transaction.setAmount(request.getAmount());
		transaction.setTransactionDateTime(request.getTransactionDateTime());
		transaction.setStatus(request.getStatus());
		transaction.setPaymentMode(request.getPaymentMode());

		return mapToResponse(transactionRepository.save(transaction));
	}

	public TransactionResponse getTransactionById(Long transactionId) {

		Transaction transaction = transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionException("Transaction not found with ID: " + transactionId));

		return mapToResponse(transaction);
	}

	public List<TransactionResponse> getAllTransactions() {

		List<Transaction> transactions =
	            transactionRepository.findAll();

	    List<TransactionResponse> responses =
	            new ArrayList<>();

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
				transaction.getTransactionDateTime(), transaction.getStatus(), transaction.getPaymentMode());
	}
}