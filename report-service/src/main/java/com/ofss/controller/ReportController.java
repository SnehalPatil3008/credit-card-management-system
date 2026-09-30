package com.ofss.controller;

import com.ofss.dto.*;
import com.ofss.service.ReportService;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public List<Map<String, Object>> allCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/cards")
    public List<Map<String, Object>> allCards() {
        return service.getAllCards();
    }

    @GetMapping("/merchants")
    public List<Map<String, Object>> allMerchants() {
        return service.getAllMerchants();
    }

    @GetMapping("/transactions")
    public List<Map<String, Object>>
    transactionHistory() {

        return service.getTransactionHistory();
    }

    @GetMapping("/customers/highest-outstanding")
    public CustomerOutstandingReport
    highestOutstanding() {

        return service.getHighestOutstanding();
    }

    @GetMapping("/customers/lowest-outstanding")
    public CustomerOutstandingReport
    lowestOutstanding() {

        return service.getLowestOutstanding();
    }

    @GetMapping("/merchants/highest-sales")
    public MerchantSalesReport highestSalesMerchant() {

        return service.getHighestSalesMerchant();
    }

    @GetMapping("/merchants/most-transactions")
    public MerchantTransactionCountReport
    merchantWithMostTransactions() {

        return service.getMerchantWithMostTransactions();
    }

    @GetMapping("/cards/most-used")
    public CardUsageReport mostUsedCard() {
        return service.getMostUsedCard();
    }

    @GetMapping("/cards/least-used")
    public CardUsageReport leastUsedCard() {
        return service.getLeastUsedCard();
    }

    @GetMapping("/purchases/today")
    public BigDecimal todayPurchases() {
        return service.getTodayPurchaseTotal();
    }

    @GetMapping("/payments/today")
    public BigDecimal todayPayments() {
        return service.getTodayPaymentTotal();
    }

    @GetMapping("/cards/blocked")
    public List<Map<String, Object>> blockedCards() {
        return service.getBlockedCards();
    }

    @GetMapping("/cards/low-credit")
    public List<Map<String, Object>> lowCreditCards() {
        return service.getLowCreditCards();
    }

    @GetMapping("/customers/highest-spending")
    public CustomerSpendingReport
    highestSpendingCustomer() {

        return service.getHighestSpendingCustomer();
    }

    @GetMapping("/customers/highest-payment")
    public CustomerPaymentReport
    highestPaymentCustomer() {

        return service.getHighestPaymentCustomer();
    }

    @GetMapping("/outstanding/total")
    public BigDecimal totalOutstanding() {
        return service.getTotalOutstanding();
    }

    @GetMapping("/purchases/average")
    public BigDecimal averagePurchase() {
        return service.getAveragePurchase();
    }

    @GetMapping("/purchases/largest")
    public BigDecimal largestPurchase() {
        return service.getLargestPurchase();
    }

    @GetMapping("/customers/monthly-spending")
    public List<MonthlySpendingReport>
    monthlySpending() {

        return service.getMonthlySpendingSummary();
    }
}