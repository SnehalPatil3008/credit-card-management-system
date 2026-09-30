package com.ofss.repository;

import com.ofss.dto.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public class ReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // -----------------------------------------------------
    // 1. ALL CUSTOMERS
    // -----------------------------------------------------

    public List<Map<String, Object>> getAllCustomers() {

        String sql = """
            SELECT customer_id,
                   customer_name,
                   email,
                   mobile_number,
                   pan_number
            FROM customer
            ORDER BY customer_id
            """;

        return jdbcTemplate.queryForList(sql);
    }


    // -----------------------------------------------------
    // 2. ALL CREDIT CARDS
    // -----------------------------------------------------

    public List<Map<String, Object>> getAllCreditCards() {

        String sql = """
            SELECT *
            FROM credit_card
            ORDER BY card_id
            """;

        return jdbcTemplate.queryForList(sql);
    }


    // -----------------------------------------------------
    // 3. ALL MERCHANTS
    // -----------------------------------------------------

    public List<Map<String, Object>> getAllMerchants() {

        String sql = """
            SELECT *
            FROM merchant
            ORDER BY merchant_id
            """;

        return jdbcTemplate.queryForList(sql);
    }


    // -----------------------------------------------------
    // 4. COMPLETE TRANSACTION HISTORY
    // -----------------------------------------------------

    public List<Map<String, Object>>
    getTransactionHistory() {

        String sql = """
            SELECT *
            FROM card_transaction
            ORDER BY transaction_date_time DESC
            """;

        return jdbcTemplate.queryForList(sql);
    }


    // -----------------------------------------------------
    // 5. CUSTOMER WITH HIGHEST OUTSTANDING
    // -----------------------------------------------------

    public CustomerOutstandingReport
    getHighestOutstandingCustomer() {

        String sql = """
            SELECT c.customer_id,
                   c.customer_name,
                   SUM(cc.outstanding_amount) outstanding
            FROM customer c
            JOIN credit_card cc
              ON c.customer_id = cc.customer_id
            GROUP BY c.customer_id, c.customer_name
            ORDER BY outstanding DESC
            FETCH FIRST 1 ROW ONLY
            """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new CustomerOutstandingReport(
                                rs.getLong("customer_id"),
                                rs.getString("customer_name"),
                                rs.getBigDecimal("outstanding")
                        )
        );
    }


    // -----------------------------------------------------
    // 6. CUSTOMER WITH LOWEST OUTSTANDING
    // -----------------------------------------------------

    public CustomerOutstandingReport
    getLowestOutstandingCustomer() {

        String sql = """
            SELECT c.customer_id,
                   c.customer_name,
                   SUM(cc.outstanding_amount) outstanding
            FROM customer c
            JOIN credit_card cc
              ON c.customer_id = cc.customer_id
            GROUP BY c.customer_id, c.customer_name
            ORDER BY outstanding ASC
            FETCH FIRST 1 ROW ONLY
            """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new CustomerOutstandingReport(
                                rs.getLong("customer_id"),
                                rs.getString("customer_name"),
                                rs.getBigDecimal("outstanding")
                        )
        );
    }


    // -----------------------------------------------------
    // 7. MERCHANT WITH HIGHEST SALES
    // -----------------------------------------------------

    public MerchantSalesReport getHighestSalesMerchant() {

        String sql = """
            SELECT m.merchant_id,
                   m.merchant_name,
                   SUM(t.amount) total_sales
            FROM merchant m
            JOIN card_transaction t
              ON m.merchant_id = t.merchant_id
            WHERE t.transaction_type = 'PURCHASE'
              AND t.status = 'SUCCESS'
            GROUP BY m.merchant_id, m.merchant_name
            ORDER BY total_sales DESC
            FETCH FIRST 1 ROW ONLY
            """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new MerchantSalesReport(
                                rs.getLong("merchant_id"),
                                rs.getString("merchant_name"),
                                rs.getBigDecimal("total_sales")
                        )
        );
    }


    // -----------------------------------------------------
    // 8. MERCHANT WITH MOST TRANSACTIONS
    // -----------------------------------------------------

    public MerchantTransactionCountReport
    getMerchantWithMostTransactions() {

        String sql = """
            SELECT m.merchant_id,
                   m.merchant_name,
                   COUNT(*) transaction_count
            FROM merchant m
            JOIN card_transaction t
              ON m.merchant_id = t.merchant_id
            WHERE t.transaction_type = 'PURCHASE'
              AND t.status = 'SUCCESS'
            GROUP BY m.merchant_id, m.merchant_name
            ORDER BY transaction_count DESC
            FETCH FIRST 1 ROW ONLY
            """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new MerchantTransactionCountReport(
                                rs.getLong("merchant_id"),
                                rs.getString("merchant_name"),
                                rs.getLong("transaction_count")
                        )
        );
    }


    // -----------------------------------------------------
    // 9. MOST FREQUENTLY USED CARD
    // -----------------------------------------------------

    public CardUsageReport getMostUsedCard() {

        return getCardUsage("DESC");
    }


    // -----------------------------------------------------
    // 10. LEAST FREQUENTLY USED CARD
    // -----------------------------------------------------

    public CardUsageReport getLeastUsedCard() {

        return getCardUsage("ASC");
    }

    private CardUsageReport getCardUsage(String order) {

        String sql = """
            SELECT cc.card_id,
                   cc.card_number,
                   COUNT(t.transaction_id) usage_count
            FROM credit_card cc
            JOIN card_transaction t
              ON cc.card_id = t.card_id
            WHERE t.transaction_type = 'PURCHASE'
              AND t.status = 'SUCCESS'
            GROUP BY cc.card_id, cc.card_number
            ORDER BY usage_count
            """ + " " + order + " FETCH FIRST 1 ROW ONLY";

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new CardUsageReport(
                                rs.getLong("card_id"),
                                rs.getString("card_number"),
                                rs.getLong("usage_count")
                        )
        );
    }


    // -----------------------------------------------------
    // 11. TOTAL PURCHASE TODAY
    // -----------------------------------------------------

    public BigDecimal getTodayPurchaseTotal() {

        String sql = """
            SELECT NVL(SUM(amount), 0)
            FROM card_transaction
            WHERE transaction_type = 'PURCHASE'
              AND status = 'SUCCESS'
              AND transaction_date_time >= TRUNC(SYSDATE)
              AND transaction_date_time < TRUNC(SYSDATE) + 1
            """;

        return jdbcTemplate.queryForObject(
                sql,
                BigDecimal.class
        );
    }


    // -----------------------------------------------------
    // 12. TOTAL PAYMENT TODAY
    // -----------------------------------------------------

    public BigDecimal getTodayPaymentTotal() {

        String sql = """
            SELECT NVL(SUM(amount), 0)
            FROM card_transaction
            WHERE transaction_type = 'PAYMENT'
              AND status = 'SUCCESS'
              AND transaction_date_time >= TRUNC(SYSDATE)
              AND transaction_date_time < TRUNC(SYSDATE) + 1
            """;

        return jdbcTemplate.queryForObject(
                sql,
                BigDecimal.class
        );
    }


    // -----------------------------------------------------
    // 13. BLOCKED CARDS
    // -----------------------------------------------------

    public List<Map<String, Object>> getBlockedCards() {

        String sql = """
            SELECT *
            FROM credit_card
            WHERE card_status = 'BLOCKED'
            ORDER BY card_id
            """;

        return jdbcTemplate.queryForList(sql);
    }


    // -----------------------------------------------------
    // 14. AVAILABLE CREDIT BELOW 20%
    // -----------------------------------------------------

    public List<Map<String, Object>> getLowCreditCards() {

        String sql = """
            SELECT *
            FROM credit_card
            WHERE available_credit <
                  (credit_limit * 0.20)
            ORDER BY available_credit
            """;

        return jdbcTemplate.queryForList(sql);
    }


    // -----------------------------------------------------
    // 15. HIGHEST SPENDING CUSTOMER
    // -----------------------------------------------------

    public CustomerSpendingReport
    getHighestSpendingCustomer() {

        String sql = """
            SELECT c.customer_id,
                   c.customer_name,
                   SUM(t.amount) total_spent
            FROM customer c
            JOIN credit_card cc
              ON c.customer_id = cc.customer_id
            JOIN card_transaction t
              ON cc.card_id = t.card_id
            WHERE t.transaction_type = 'PURCHASE'
              AND t.status = 'SUCCESS'
            GROUP BY c.customer_id, c.customer_name
            ORDER BY total_spent DESC
            FETCH FIRST 1 ROW ONLY
            """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new CustomerSpendingReport(
                                rs.getLong("customer_id"),
                                rs.getString("customer_name"),
                                rs.getBigDecimal("total_spent")
                        )
        );
    }


    // -----------------------------------------------------
    // 16. CUSTOMER WITH HIGHEST PAYMENT
    // -----------------------------------------------------

    public CustomerPaymentReport
    getHighestPaymentCustomer() {

        String sql = """
            SELECT c.customer_id,
                   c.customer_name,
                   SUM(t.amount) total_payment
            FROM customer c
            JOIN credit_card cc
              ON c.customer_id = cc.customer_id
            JOIN card_transaction t
              ON cc.card_id = t.card_id
            WHERE t.transaction_type = 'PAYMENT'
              AND t.status = 'SUCCESS'
            GROUP BY c.customer_id, c.customer_name
            ORDER BY total_payment DESC
            FETCH FIRST 1 ROW ONLY
            """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) ->
                        new CustomerPaymentReport(
                                rs.getLong("customer_id"),
                                rs.getString("customer_name"),
                                rs.getBigDecimal("total_payment")
                        )
        );
    }


    // -----------------------------------------------------
    // 17. TOTAL OUTSTANDING
    // -----------------------------------------------------

    public BigDecimal getTotalOutstanding() {

        String sql = """
            SELECT NVL(SUM(outstanding_amount), 0)
            FROM credit_card
            """;

        return jdbcTemplate.queryForObject(
                sql,
                BigDecimal.class
        );
    }


    // -----------------------------------------------------
    // 18. AVERAGE PURCHASE
    // -----------------------------------------------------

    public BigDecimal getAveragePurchase() {

        String sql = """
            SELECT NVL(AVG(amount), 0)
            FROM card_transaction
            WHERE transaction_type = 'PURCHASE'
              AND status = 'SUCCESS'
            """;

        return jdbcTemplate.queryForObject(
                sql,
                BigDecimal.class
        );
    }


    // -----------------------------------------------------
    // 19. LARGEST PURCHASE
    // -----------------------------------------------------

    public BigDecimal getLargestPurchase() {

        String sql = """
            SELECT NVL(MAX(amount), 0)
            FROM card_transaction
            WHERE transaction_type = 'PURCHASE'
              AND status = 'SUCCESS'
            """;

        return jdbcTemplate.queryForObject(
                sql,
                BigDecimal.class
        );
    }


    // -----------------------------------------------------
    // 20. MONTHLY SPENDING SUMMARY
    // -----------------------------------------------------

    public List<MonthlySpendingReport>
    getMonthlySpendingSummary() {

        String sql = """
            SELECT c.customer_id,
                   c.customer_name,
                   TO_CHAR(
                       t.transaction_date_time,
                       'YYYY-MM'
                   ) spending_month,
                   SUM(t.amount) total_spent
            FROM customer c
            JOIN credit_card cc
              ON c.customer_id = cc.customer_id
            JOIN card_transaction t
              ON cc.card_id = t.card_id
            WHERE t.transaction_type = 'PURCHASE'
              AND t.status = 'SUCCESS'
            GROUP BY
                c.customer_id,
                c.customer_name,
                TO_CHAR(
                    t.transaction_date_time,
                    'YYYY-MM'
                )
            ORDER BY spending_month,
                     c.customer_id
            """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new MonthlySpendingReport(
                                rs.getLong("customer_id"),
                                rs.getString("customer_name"),
                                rs.getString("spending_month"),
                                rs.getBigDecimal("total_spent")
                        )
        );
    }
}