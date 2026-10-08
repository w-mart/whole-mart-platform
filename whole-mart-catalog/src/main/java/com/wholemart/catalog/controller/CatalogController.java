package com.wholemart.catalog.controller;

import com.wholemart.catalog.constants.CatalogConstants;
import com.wholemart.catalog.constants.CatalogMessages;
import com.wholemart.catalog.dto.*;
import com.wholemart.catalog.service.CatalogService;
import com.wholemart.common.message.MessageResolver;
import com.wholemart.common.constants.MessageConstants;
import com.wholemart.common.constants.CommonConstants;
import com.wholemart.common.response.ApiResponse;
import com.wholemart.common.response.PageResponse;
import com.wholemart.common.security.CurrentUser;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CatalogController {
    private final CatalogService catalog; private final MessageResolver messages;
    public CatalogController(CatalogService catalog, MessageResolver messages) { this.catalog = catalog; this.messages = messages; }
    @PostMapping(CatalogConstants.PRODUCTS_PATH) @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) { return ApiResponse.success(catalog.createProduct(request), messages.get(CatalogMessages.PRODUCT_CREATED)); }
    @GetMapping(CatalogConstants.PRODUCTS_PATH + CatalogConstants.SEARCH_PATH)
    public ApiResponse<PageResponse<ProductResponse>> searchProducts(@RequestParam(defaultValue = CommonConstants.EMPTY) String name, @RequestParam(defaultValue = CatalogConstants.DEFAULT_PAGE) int page, @RequestParam(defaultValue = CatalogConstants.DEFAULT_PAGE_SIZE) int size) {
        if (page < CatalogConstants.MIN_PAGE || size < CatalogConstants.MIN_PAGE_SIZE || size > CatalogConstants.MAX_PAGE_SIZE) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_PAGE_PARAMETERS);
        var result = catalog.searchProducts(name, PageRequest.of(page, size, Sort.by(CatalogConstants.PRODUCT_NAME_SORT_FIELD))); return ApiResponse.success(PageResponse.from(result));
    }
    @PostMapping(CatalogConstants.MERCHANT_PRODUCTS_PATH) @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MerchantProductResponse> createMerchantProduct(@Valid @RequestBody CreateMerchantProductRequest request) { return ApiResponse.success(catalog.createMerchantProduct(CurrentUser.id(), request), messages.get(CatalogMessages.MERCHANT_PRODUCT_CREATED)); }
    @PutMapping(CatalogConstants.MERCHANT_PRODUCTS_PATH + CatalogConstants.BY_ID_PATH)
    public ApiResponse<MerchantProductResponse> updateMerchantProduct(@PathVariable UUID id, @Valid @RequestBody UpdateMerchantProductRequest request) { return ApiResponse.success(catalog.updateMerchantProduct(CurrentUser.id(), id, request), messages.get(CatalogMessages.MERCHANT_PRODUCT_UPDATED)); }
    @GetMapping(CatalogConstants.MERCHANT_PRODUCTS_BY_MERCHANT_PATH)
    public ApiResponse<java.util.List<MerchantProductResponse>> merchantProducts(@PathVariable UUID id) { return ApiResponse.success(catalog.merchantProducts(id)); }
}
