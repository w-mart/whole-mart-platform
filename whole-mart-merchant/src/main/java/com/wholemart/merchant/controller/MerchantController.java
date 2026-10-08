package com.wholemart.merchant.controller;

import com.wholemart.common.response.ApiResponse;
import com.wholemart.common.response.PageResponse;
import com.wholemart.common.security.CurrentUser;
import com.wholemart.common.constants.BusinessConstants;
import com.wholemart.common.constants.ApiPathConstants;
import com.wholemart.common.constants.MessageConstants;
import com.wholemart.merchant.dto.CreateMerchantRequest;
import com.wholemart.merchant.dto.MerchantResponse;
import com.wholemart.merchant.dto.UpdateMerchantRequest;
import com.wholemart.merchant.entity.MerchantType;
import com.wholemart.merchant.service.MerchantService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPathConstants.MERCHANTS)
public class MerchantController {
    private final MerchantService merchants;

    public MerchantController(MerchantService merchants) {
        this.merchants = merchants;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MerchantResponse> create(@Valid @RequestBody CreateMerchantRequest request) {
        return ApiResponse.success(merchants.create(CurrentUser.id(), request), MessageConstants.MERCHANT_CREATED);
    }

    @GetMapping(ApiPathConstants.MERCHANT_BY_ID)
    public ApiResponse<MerchantResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(merchants.get(id));
    }

    @GetMapping(ApiPathConstants.MERCHANT_SEARCH)
    public ApiResponse<PageResponse<MerchantResponse>> search(@RequestParam(required = false) String name,
            @RequestParam(required = false) MerchantType type,
            @RequestParam(defaultValue = BusinessConstants.DEFAULT_PAGE_PARAMETER) int page,
            @RequestParam(defaultValue = BusinessConstants.DEFAULT_PAGE_SIZE_PARAMETER) int size) {
        if (page < BusinessConstants.DEFAULT_PAGE || size < BusinessConstants.MIN_PAGE_SIZE || size > BusinessConstants.MAX_PAGE_SIZE)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_PAGE_PARAMETERS);
        var result = merchants.search(name, type, PageRequest.of(page, size, Sort.by("businessName").ascending()));
        return ApiResponse.success(new PageResponse<>(result.getContent(), result.getNumber(), result.getSize(),
                result.getTotalElements(), result.getTotalPages(), result.isLast()));
    }

    @PutMapping(ApiPathConstants.MERCHANT_BY_ID)
    public ApiResponse<MerchantResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateMerchantRequest request) {
        return ApiResponse.success(merchants.update(CurrentUser.id(), id, request), MessageConstants.MERCHANT_UPDATED);
    }
}
