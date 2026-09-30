package com.ofss.controller;

import com.ofss.dto.TransactionRequest;
import com.ofss.dto.TransactionResponse;
import com.ofss.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest request) {

		TransactionResponse response = transactionService.createTransaction(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Long transactionId) {

		return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
	}

	@GetMapping
	public ResponseEntity<List<TransactionResponse>> getAllTransactions() {

		return ResponseEntity.ok(transactionService.getAllTransactions());
	}

	@PutMapping("/{transactionId}")
	public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long transactionId,
			@Valid @RequestBody TransactionRequest request) {

		return ResponseEntity.ok(transactionService.updateTransaction(transactionId, request));
	}

	@DeleteMapping("/{transactionId}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {

		transactionService.deleteTransaction(transactionId);

		return ResponseEntity.noContent().build();
	}
}