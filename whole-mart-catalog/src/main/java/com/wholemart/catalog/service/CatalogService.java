package com.wholemart.catalog.service;

import com.wholemart.catalog.dto.*;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CatalogService {
    ProductResponse createProduct(CreateProductRequest request);
    Page<ProductResponse> searchProducts(String name, Pageable pageable);
    MerchantProductResponse createMerchantProduct(UUID userId, CreateMerchantProductRequest request);
    MerchantProductResponse updateMerchantProduct(UUID userId, UUID id, UpdateMerchantProductRequest request);
    List<MerchantProductResponse> merchantProducts(UUID merchantId);
}
