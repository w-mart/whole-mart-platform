package com.wholemart.cart.service;

import com.wholemart.cart.dto.*;
import java.util.UUID;

public interface CartService {
    CartResponse get(UUID userId);
    CartResponse add(UUID userId, AddCartItemRequest request);
    CartResponse update(UUID userId, UUID itemId, UpdateCartItemRequest request);
    void remove(UUID userId, UUID itemId);
}
