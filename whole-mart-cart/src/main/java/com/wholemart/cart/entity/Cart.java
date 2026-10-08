package com.wholemart.cart.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "carts", uniqueConstraints = @UniqueConstraint(name = "uk_carts_user", columnNames = "user_id"))
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(name = "user_id", nullable = false, updatable = false) private UUID userId;
    @Column(name = "wholesaler_id") private UUID wholesalerId;
    @Column(nullable = false, updatable = false) private Instant createdAt = Instant.now();
    protected Cart() {}
    public Cart(UUID userId, UUID wholesalerId) { this.userId = userId; this.wholesalerId = wholesalerId; }
    public UUID getId() { return id; } public UUID getUserId() { return userId; } public UUID getWholesalerId() { return wholesalerId; }
    public void clearWholesaler() { this.wholesalerId = null; }
}
