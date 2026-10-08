package com.wholemart.catalog.dto;

import com.wholemart.catalog.constants.CatalogConstants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record UpdateMerchantProductRequest(@NotNull @DecimalMin(CatalogConstants.MIN_PRICE) BigDecimal price,
        @Min(CatalogConstants.MIN_PAGE) @Max(CatalogConstants.MAX_QUANTITY) int stock) {}
