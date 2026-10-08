package com.wholemart.catalog.entity;

import com.wholemart.catalog.constants.CatalogConstants;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(name = "uk_products_sku", columnNames = "sku"))
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID) private UUID id;
    @Column(nullable = false, length = CatalogConstants.MAX_PRODUCT_NAME_LENGTH) private String name;
    @Column(length = CatalogConstants.MAX_BRAND_LENGTH) private String brand;
    @Column(length = CatalogConstants.MAX_CATEGORY_LENGTH) private String category;
    @Column(nullable = false, length = CatalogConstants.MAX_SKU_LENGTH) private String sku;
    @Column(nullable = false, updatable = false) private Instant createdAt = Instant.now();
    protected Product() {}
    public Product(String name, String brand, String category, String sku) { this.name = name; this.brand = brand; this.category = category; this.sku = sku; }
    public UUID getId() { return id; } public String getName() { return name; } public String getBrand() { return brand; }
    public String getCategory() { return category; } public String getSku() { return sku; } public Instant getCreatedAt() { return createdAt; }
}
