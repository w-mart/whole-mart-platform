package com.wholemart.merchant.service;

import com.wholemart.merchant.dto.CreateMerchantRequest;
import com.wholemart.merchant.dto.MerchantResponse;
import com.wholemart.merchant.dto.UpdateMerchantRequest;
import com.wholemart.merchant.entity.MerchantType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface MerchantService {
    MerchantResponse create(UUID userId, CreateMerchantRequest request);
    MerchantResponse get(UUID id);
    MerchantResponse update(UUID userId, UUID id, UpdateMerchantRequest request);
    Page<MerchantResponse> search(String name, MerchantType type, Pageable pageable);
}
