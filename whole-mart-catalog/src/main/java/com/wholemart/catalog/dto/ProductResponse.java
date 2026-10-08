package com.wholemart.catalog.dto;

import java.time.Instant;
import java.util.UUID;
public record ProductResponse(UUID id, String name, String brand, String category, String sku, Instant createdAt) {}
