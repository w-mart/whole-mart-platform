package com.wholemart.merchant.dto;

import com.wholemart.merchant.entity.MerchantStatus;
import com.wholemart.merchant.entity.MerchantType;
import java.time.Instant;
import java.util.UUID;

public record MerchantResponse(UUID id, UUID userId, String businessName, String gstin, String phone,
                              MerchantType merchantType, MerchantStatus status, Instant createdAt) {}
