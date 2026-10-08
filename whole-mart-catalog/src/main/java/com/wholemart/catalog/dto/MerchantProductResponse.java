package com.wholemart.catalog.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
public record MerchantProductResponse(UUID id, UUID merchantId, UUID productId, BigDecimal price, int stock, Instant createdAt) {}
