package com.wholemart.cart.service;

import com.wholemart.cart.constants.CartMessages;
import com.wholemart.cart.dto.*;
import com.wholemart.cart.entity.Cart;
import com.wholemart.cart.entity.CartItem;
import com.wholemart.cart.repository.CartItemRepository;
import com.wholemart.cart.repository.CartRepository;
import com.wholemart.catalog.entity.Inventory;
import com.wholemart.catalog.entity.MerchantProduct;
import com.wholemart.catalog.entity.Product;
import com.wholemart.catalog.repository.InventoryRepository;
import com.wholemart.catalog.repository.MerchantProductRepository;
import com.wholemart.catalog.repository.ProductRepository;
import com.wholemart.common.exception.BusinessException;
import com.wholemart.common.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository carts; private final CartItemRepository items; private final MerchantProductRepository merchantProducts;
    private final InventoryRepository inventory; private final ProductRepository products;
    public CartServiceImpl(CartRepository carts, CartItemRepository items, MerchantProductRepository merchantProducts, InventoryRepository inventory, ProductRepository products) { this.carts = carts; this.items = items; this.merchantProducts = merchantProducts; this.inventory = inventory; this.products = products; }
    @Override @Transactional(readOnly = true) public CartResponse get(UUID userId) { return carts.findByUserId(userId).map(this::response).orElseGet(() -> new CartResponse(null, null, List.of(), BigDecimal.ZERO)); }
    @Override @Transactional public CartResponse add(UUID userId, AddCartItemRequest request) {
        MerchantProduct merchantProduct = merchantProducts.findById(request.merchantProductId()).orElseThrow(() -> new ResourceNotFoundException(CartMessages.MERCHANT_PRODUCT_NOT_FOUND));
        assertAvailable(merchantProduct.getId(), request.quantity());
        Cart cart = carts.findByUserId(userId).orElseGet(() -> carts.save(new Cart(userId, merchantProduct.getMerchantId())));
        if (cart.getWholesalerId() != null && !cart.getWholesalerId().equals(merchantProduct.getMerchantId())) throw new BusinessException(CartMessages.DIFFERENT_WHOLESALER);
        CartItem item = items.findByCartIdAndMerchantProductId(cart.getId(), merchantProduct.getId()).orElseGet(() -> new CartItem(cart.getId(), merchantProduct.getId(), 0));
        int quantity = item.getQuantity() + request.quantity(); assertAvailable(merchantProduct.getId(), quantity); item.setQuantity(quantity); items.save(item);
        return response(cart);
    }
    @Override @Transactional public CartResponse update(UUID userId, UUID itemId, UpdateCartItemRequest request) {
        Cart cart = cartFor(userId); CartItem item = items.findByIdAndCartId(itemId, cart.getId()).orElseThrow(() -> new ResourceNotFoundException(CartMessages.ITEM_NOT_FOUND));
        assertAvailable(item.getMerchantProductId(), request.quantity()); item.setQuantity(request.quantity()); items.save(item); return response(cart);
    }
    @Override @Transactional public void remove(UUID userId, UUID itemId) {
        Cart cart = cartFor(userId); CartItem item = items.findByIdAndCartId(itemId, cart.getId()).orElseThrow(() -> new ResourceNotFoundException(CartMessages.ITEM_NOT_FOUND)); items.delete(item);
        if (items.findByCartId(cart.getId()).isEmpty()) cart.clearWholesaler();
    }
    private Cart cartFor(UUID userId) { return carts.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(CartMessages.ITEM_NOT_FOUND)); }
    private void assertAvailable(UUID merchantProductId, int quantity) { Inventory stock = inventory.findByMerchantProductId(merchantProductId).orElseThrow(() -> new BusinessException(CartMessages.PRODUCT_OUT_OF_STOCK)); if (stock.getQuantity() < quantity) throw new BusinessException(CartMessages.QUANTITY_EXCEEDS_STOCK); }
    private CartResponse response(Cart cart) {
        List<CartItemResponse> result = items.findByCartId(cart.getId()).stream().map(item -> {
            MerchantProduct merchantProduct = merchantProducts.findById(item.getMerchantProductId()).orElseThrow(() -> new ResourceNotFoundException(CartMessages.MERCHANT_PRODUCT_NOT_FOUND));
            Product product = products.findById(merchantProduct.getProductId()).orElseThrow(() -> new ResourceNotFoundException(CartMessages.MERCHANT_PRODUCT_NOT_FOUND));
            BigDecimal lineTotal = merchantProduct.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            return new CartItemResponse(item.getId(), item.getMerchantProductId(), product.getName(), merchantProduct.getPrice(), item.getQuantity(), lineTotal);
        }).toList();
        return new CartResponse(cart.getId(), cart.getWholesalerId(), result, result.stream().map(CartItemResponse::lineTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
