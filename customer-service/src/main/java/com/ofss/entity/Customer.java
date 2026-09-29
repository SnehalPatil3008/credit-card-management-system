package com.ofss.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "CUSTOMER",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_customer_email",
            columnNames = "email"
        ),
        @UniqueConstraint(
            name = "uq_customer_mobile",
            columnNames = "mobile_number"
        ),
        @UniqueConstraint(
            name = "uq_customer_pan",
            columnNames = "pan_number"
        )
    }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;

    @Column(name = "pan_number", nullable = false, length = 10)
    private String panNumber;

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}