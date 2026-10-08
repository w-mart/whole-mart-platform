package com.wholemart.catalog.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "inventory", uniqueConstraints = @UniqueConstraint(name = "uk_inventory_merchant_product", columnNames = "merchant_product_id"))
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(name = "merchant_product_id", nullable = false, updatable = false) private UUID merchantProductId;
    @Column(nullable = false) private int quantity;
    protected Inventory() {}
    public Inventory(UUID merchantProductId, int quantity) { this.merchantProductId = merchantProductId; this.quantity = quantity; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
