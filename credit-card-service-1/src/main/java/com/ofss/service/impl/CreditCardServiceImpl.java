package com.ofss.service.impl;

import com.ofss.dto.*;
import com.ofss.entity.*;
import com.ofss.exception.CreditCardNotFoundException;
import com.ofss.repository.CreditCardRepository;
import com.ofss.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final RestTemplate restTemplate;
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, RestTemplate restTemplate) { this.creditCardRepository = creditCardRepository; this.restTemplate = restTemplate; }
    @Override public CreditCardResponse issueCard(CreditCardRequest request) {
        validateCustomerExists(request.getCustomerId());
        if (creditCardRepository.existsByCardNumber(request.getCardNumber())) throw new IllegalArgumentException("Card number already exists");
        CreditCard card = new CreditCard();
        card.setCustomerId(request.getCustomerId()); card.setCardNumber(request.getCardNumber()); card.setCardType(request.getCardType());
        card.setCreditLimit(request.getCreditLimit()); card.setAvailableCredit(request.getCreditLimit()); card.setOutstandingAmount(BigDecimal.ZERO);
        card.setExpiryDate(request.getExpiryDate()); card.setCardStatus(CardStatus.ACTIVE);
        return mapToResponse(creditCardRepository.save(card));
    }
    @Override @Transactional(readOnly = true) public CreditCardResponse getCardById(Long cardId) { return mapToResponse(findCard(cardId)); }
    @Override @Transactional(readOnly = true) public List<CreditCardResponse> getAllCards() { return creditCardRepository.findAll().stream().map(this::mapToResponse).toList(); }
    @Override @Transactional(readOnly = true) public List<CreditCardResponse> getCardsByCustomerId(Long customerId) { return creditCardRepository.findByCustomerId(customerId).stream().map(this::mapToResponse).toList(); }
    @Override public CreditCardResponse updateCard(Long cardId, CreditCardUpdateRequest request) {
        CreditCard card = findCard(cardId);
        if (request.getCreditLimit().compareTo(card.getOutstandingAmount()) < 0) throw new IllegalArgumentException("Credit limit cannot be less than outstanding amount");
        card.setCardType(request.getCardType()); card.setCreditLimit(request.getCreditLimit()); card.setAvailableCredit(request.getCreditLimit().subtract(card.getOutstandingAmount())); card.setExpiryDate(request.getExpiryDate());
        return mapToResponse(creditCardRepository.save(card));
    }
    @Override public CreditCardResponse changeCardStatus(Long cardId, CardStatusRequest request) { CreditCard card = findCard(cardId); card.setCardStatus(request.getCardStatus()); return mapToResponse(creditCardRepository.save(card)); }
    private CreditCard findCard(Long cardId) { return creditCardRepository.findById(cardId).orElseThrow(() -> new CreditCardNotFoundException("Credit card not found with ID: " + cardId)); }
    private void validateCustomerExists(Long customerId) {
        try { restTemplate.getForObject("http://customer-service/api/customers/{customerId}", Object.class, customerId); }
        catch (RestClientResponseException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) throw new IllegalArgumentException("Customer not found with ID: " + customerId);
            throw new IllegalArgumentException("Customer service could not validate customer");
        } catch (Exception exception) { throw new IllegalArgumentException("Customer service is unavailable. Start Eureka Server and Customer Service first"); }
    }
    private CreditCardResponse mapToResponse(CreditCard card) { return new CreditCardResponse(card.getCardId(), card.getCustomerId(), card.getCardNumber(), card.getCardType(), card.getCreditLimit(), card.getAvailableCredit(), card.getOutstandingAmount(), card.getExpiryDate(), card.getCardStatus()); }
}
