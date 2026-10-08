package com.wholemart.cart.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "cart_items", uniqueConstraints = @UniqueConstraint(name = "uk_cart_items_cart_merchant_product", columnNames = {"cart_id", "merchant_product_id"}))
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(name = "cart_id", nullable = false, updatable = false) private UUID cartId;
    @Column(name = "merchant_product_id", nullable = false, updatable = false) private UUID merchantProductId;
    @Column(nullable = false) private int quantity;
    @Column(nullable = false, updatable = false) private Instant createdAt = Instant.now();
    protected CartItem() {}
    public CartItem(UUID cartId, UUID merchantProductId, int quantity) { this.cartId = cartId; this.merchantProductId = merchantProductId; this.quantity = quantity; }
    public UUID getId() { return id; } public UUID getMerchantProductId() { return merchantProductId; } public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
