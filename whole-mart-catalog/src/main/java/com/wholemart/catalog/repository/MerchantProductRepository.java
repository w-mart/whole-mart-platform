package com.wholemart.catalog.repository;

import com.wholemart.catalog.entity.MerchantProduct;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantProductRepository extends JpaRepository<MerchantProduct, UUID> {
    boolean existsByMerchantIdAndProductId(UUID merchantId, UUID productId);
    List<MerchantProduct> findByMerchantId(UUID merchantId);
}
