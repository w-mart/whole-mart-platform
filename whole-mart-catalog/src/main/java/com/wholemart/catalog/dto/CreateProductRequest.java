package com.wholemart.catalog.dto;

import com.wholemart.catalog.constants.CatalogConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(@NotBlank @Size(max = CatalogConstants.MAX_PRODUCT_NAME_LENGTH) String name,
        @Size(max = CatalogConstants.MAX_BRAND_LENGTH) String brand,
        @Size(max = CatalogConstants.MAX_CATEGORY_LENGTH) String category,
        @NotBlank @Size(max = CatalogConstants.MAX_SKU_LENGTH) String sku) {}
