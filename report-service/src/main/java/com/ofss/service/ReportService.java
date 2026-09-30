package com.ofss.service;

import com.ofss.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportService {

    List<Map<String, Object>> getAllCustomers();

    List<Map<String, Object>> getAllCards();

    List<Map<String, Object>> getAllMerchants();

    List<Map<String, Object>> getTransactionHistory();

    CustomerOutstandingReport getHighestOutstanding();

    CustomerOutstandingReport getLowestOutstanding();

    MerchantSalesReport getHighestSalesMerchant();

    MerchantTransactionCountReport
    getMerchantWithMostTransactions();

    CardUsageReport getMostUsedCard();

    CardUsageReport getLeastUsedCard();

    BigDecimal getTodayPurchaseTotal();

    BigDecimal getTodayPaymentTotal();

    List<Map<String, Object>> getBlockedCards();

    List<Map<String, Object>> getLowCreditCards();

    CustomerSpendingReport getHighestSpendingCustomer();

    CustomerPaymentReport getHighestPaymentCustomer();

    BigDecimal getTotalOutstanding();

    BigDecimal getAveragePurchase();

    BigDecimal getLargestPurchase();

    List<MonthlySpendingReport>
    getMonthlySpendingSummary();
}