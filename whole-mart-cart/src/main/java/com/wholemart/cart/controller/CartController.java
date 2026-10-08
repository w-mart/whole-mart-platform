package com.wholemart.cart.controller;

import com.wholemart.cart.constants.CartConstants;
import com.wholemart.cart.constants.CartMessages;
import com.wholemart.cart.dto.*;
import com.wholemart.cart.service.CartService;
import com.wholemart.common.message.MessageResolver;
import com.wholemart.common.response.ApiResponse;
import com.wholemart.common.security.CurrentUser;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CartConstants.API_PATH)
public class CartController {
    private final CartService cart; private final MessageResolver messages;
    public CartController(CartService cart, MessageResolver messages) { this.cart = cart; this.messages = messages; }
    @GetMapping public ApiResponse<CartResponse> get() { return ApiResponse.success(cart.get(CurrentUser.id())); }
    @PostMapping(CartConstants.ITEMS_PATH) public ApiResponse<CartResponse> add(@Valid @RequestBody AddCartItemRequest request) { return ApiResponse.success(cart.add(CurrentUser.id(), request), messages.get(CartMessages.ITEM_ADDED)); }
    @PutMapping(CartConstants.ITEM_BY_ID_PATH) public ApiResponse<CartResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateCartItemRequest request) { return ApiResponse.success(cart.update(CurrentUser.id(), id, request), messages.get(CartMessages.ITEM_UPDATED)); }
    @DeleteMapping(CartConstants.ITEM_BY_ID_PATH) public ApiResponse<Void> remove(@PathVariable UUID id) { cart.remove(CurrentUser.id(), id); return ApiResponse.success(null, messages.get(CartMessages.ITEM_REMOVED)); }
}
