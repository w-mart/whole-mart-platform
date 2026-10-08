package com.wholemart.cart.repository;

import com.wholemart.cart.entity.CartItem;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    List<CartItem> findByCartId(UUID cartId);
    Optional<CartItem> findByIdAndCartId(UUID id, UUID cartId);
    Optional<CartItem> findByCartIdAndMerchantProductId(UUID cartId, UUID merchantProductId);
    long deleteByCartId(UUID cartId);
}
