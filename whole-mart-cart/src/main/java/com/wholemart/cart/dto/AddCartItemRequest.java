package com.wholemart.cart.dto;

import com.wholemart.cart.constants.CartConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AddCartItemRequest(@NotNull UUID merchantProductId, @Min(CartConstants.MIN_QUANTITY) @Max(CartConstants.MAX_QUANTITY) int quantity) {}
