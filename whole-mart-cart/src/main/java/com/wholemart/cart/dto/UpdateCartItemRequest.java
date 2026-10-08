package com.wholemart.cart.dto;

import com.wholemart.cart.constants.CartConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UpdateCartItemRequest(@Min(CartConstants.MIN_QUANTITY) @Max(CartConstants.MAX_QUANTITY) int quantity) {}
