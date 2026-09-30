package com.ofss.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MERCHANT")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "merchant_name", nullable = false, length = 150)
    private String merchantName;

    @Column(name = "category", nullable = false, length = 60)
    private String category;

    @Column(name = "location", nullable = false, length = 200)
    private String location;

    public Merchant() { }
    public Long getMerchantId() { return merchantId; }
    public void setMerchantId(Long merchantId) { this.merchantId = merchantId; }
    public String getMerchantName() { return merchantName; }
    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
