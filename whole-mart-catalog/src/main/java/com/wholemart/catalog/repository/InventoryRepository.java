package com.wholemart.catalog.repository;

import com.wholemart.catalog.entity.Inventory;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> { Optional<Inventory> findByMerchantProductId(UUID merchantProductId); }
