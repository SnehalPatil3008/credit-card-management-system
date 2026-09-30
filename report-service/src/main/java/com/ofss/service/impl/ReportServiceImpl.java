package com.ofss.service.impl;

import com.ofss.dto.*;
import com.ofss.repository.ReportRepository;
import com.ofss.service.ReportService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;

    public ReportServiceImpl(
            ReportRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<Map<String, Object>> getAllCustomers() {
        return repository.getAllCustomers();
    }

    @Override
    public List<Map<String, Object>> getAllCards() {
        return repository.getAllCreditCards();
    }

    @Override
    public List<Map<String, Object>> getAllMerchants() {
        return repository.getAllMerchants();
    }

    @Override
    public List<Map<String, Object>>
    getTransactionHistory() {

        return repository.getTransactionHistory();
    }

    @Override
    public CustomerOutstandingReport
    getHighestOutstanding() {

        return repository.getHighestOutstandingCustomer();
    }

    @Override
    public CustomerOutstandingReport
    getLowestOutstanding() {

        return repository.getLowestOutstandingCustomer();
    }

    @Override
    public MerchantSalesReport
    getHighestSalesMerchant() {

        return repository.getHighestSalesMerchant();
    }

    @Override
    public MerchantTransactionCountReport
    getMerchantWithMostTransactions() {

        return repository.getMerchantWithMostTransactions();
    }

    @Override
    public CardUsageReport getMostUsedCard() {
        return repository.getMostUsedCard();
    }

    @Override
    public CardUsageReport getLeastUsedCard() {
        return repository.getLeastUsedCard();
    }

    @Override
    public BigDecimal getTodayPurchaseTotal() {
        return repository.getTodayPurchaseTotal();
    }

    @Override
    public BigDecimal getTodayPaymentTotal() {
        return repository.getTodayPaymentTotal();
    }

    @Override
    public List<Map<String, Object>> getBlockedCards() {
        return repository.getBlockedCards();
    }

    @Override
    public List<Map<String, Object>> getLowCreditCards() {
        return repository.getLowCreditCards();
    }

    @Override
    public CustomerSpendingReport
    getHighestSpendingCustomer() {

        return repository.getHighestSpendingCustomer();
    }

    @Override
    public CustomerPaymentReport
    getHighestPaymentCustomer() {

        return repository.getHighestPaymentCustomer();
    }

    @Override
    public BigDecimal getTotalOutstanding() {
        return repository.getTotalOutstanding();
    }

    @Override
    public BigDecimal getAveragePurchase() {
        return repository.getAveragePurchase();
    }

    @Override
    public BigDecimal getLargestPurchase() {
        return repository.getLargestPurchase();
    }

    @Override
    public List<MonthlySpendingReport>
    getMonthlySpendingSummary() {

        return repository.getMonthlySpendingSummary();
    }
}