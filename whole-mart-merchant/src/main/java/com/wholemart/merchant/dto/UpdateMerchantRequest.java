package com.wholemart.merchant.dto;

import com.wholemart.common.constants.ValidationConstants;
import com.wholemart.merchant.entity.MerchantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateMerchantRequest(
    @NotBlank @Size(max = ValidationConstants.MAX_BUSINESS_NAME_LENGTH) String businessName,
    @Pattern(regexp = ValidationConstants.OPTIONAL_GSTIN_REGEX, message = ValidationConstants.INVALID_GSTIN) String gstin,
    @NotBlank @Pattern(regexp = ValidationConstants.INDIAN_MOBILE_REGEX, message = ValidationConstants.INVALID_INDIAN_MOBILE) String phone,
    @NotNull MerchantType merchantType) {}
