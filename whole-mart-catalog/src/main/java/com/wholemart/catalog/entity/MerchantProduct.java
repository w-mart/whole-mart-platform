package com.wholemart.catalog.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "merchant_products", uniqueConstraints = @UniqueConstraint(name = "uk_merchant_products_merchant_product", columnNames = {"merchant_id", "product_id"}))
public class MerchantProduct {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(name = "merchant_id", nullable = false, updatable = false) private UUID merchantId;
    @Column(name = "product_id", nullable = false, updatable = false) private UUID productId;
    @Column(nullable = false, precision = 19, scale = 2) private BigDecimal price;
    @Column(nullable = false, updatable = false) private Instant createdAt = Instant.now();
    protected MerchantProduct() {}
    public MerchantProduct(UUID merchantId, UUID productId, BigDecimal price) { this.merchantId = merchantId; this.productId = productId; this.price = price; }
    public UUID getId() { return id; } public UUID getMerchantId() { return merchantId; } public UUID getProductId() { return productId; }
    public BigDecimal getPrice() { return price; } public Instant getCreatedAt() { return createdAt; }
    public void update(BigDecimal price) { this.price = price; }
}
