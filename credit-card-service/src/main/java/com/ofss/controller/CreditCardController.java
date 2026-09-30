package com.ofss.controller;

import com.ofss.dto.*;
import com.ofss.service.CreditCardService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CreditCardController {
    private final CreditCardService creditCardService;
    public CreditCardController(CreditCardService creditCardService) { this.creditCardService = creditCardService; }
    @PostMapping public ResponseEntity<CreditCardResponse> issueCard(@Valid @RequestBody CreditCardRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(creditCardService.issueCard(request)); }
    @GetMapping("/{cardId}") public ResponseEntity<CreditCardResponse> getCardById(@PathVariable Long cardId) { return ResponseEntity.ok(creditCardService.getCardById(cardId)); }
    @GetMapping public ResponseEntity<List<CreditCardResponse>> getAllCards() { return ResponseEntity.ok(creditCardService.getAllCards()); }
    @GetMapping("/customer/{customerId}") public ResponseEntity<List<CreditCardResponse>> getCardsByCustomerId(@PathVariable Long customerId) { return ResponseEntity.ok(creditCardService.getCardsByCustomerId(customerId)); }
    @PutMapping("/{cardId}") public ResponseEntity<CreditCardResponse> updateCard(@PathVariable Long cardId, @Valid @RequestBody CreditCardUpdateRequest request) { return ResponseEntity.ok(creditCardService.updateCard(cardId, request)); }
    @PatchMapping("/{cardId}/status") public ResponseEntity<CreditCardResponse> changeCardStatus(@PathVariable Long cardId, @Valid @RequestBody CardStatusRequest request) { return ResponseEntity.ok(creditCardService.changeCardStatus(cardId, request)); }
}
