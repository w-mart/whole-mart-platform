package com.wholemart.cart.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponse(UUID id, UUID wholesalerId, List<CartItemResponse> items, BigDecimal total) {}
