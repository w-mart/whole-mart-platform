package com.wholemart.cart.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(UUID id, UUID merchantProductId, String productName, BigDecimal unitPrice, int quantity, BigDecimal lineTotal) {}
