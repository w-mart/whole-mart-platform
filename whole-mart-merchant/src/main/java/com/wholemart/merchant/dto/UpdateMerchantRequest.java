package com.wholemart.merchant.dto;

import com.wholemart.common.constants.ValidationConstants;
import com.wholemart.merchant.constants.MerchantConstants;
import com.wholemart.merchant.entity.MerchantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateMerchantRequest(
    @NotBlank @Size(max = MerchantConstants.MAX_BUSINESS_NAME_LENGTH) String businessName,
    @Pattern(regexp = MerchantConstants.OPTIONAL_GSTIN_REGEX, message = MerchantConstants.INVALID_GSTIN) String gstin,
    @NotBlank @Pattern(regexp = ValidationConstants.INDIAN_MOBILE_REGEX, message = MerchantConstants.INVALID_MOBILE) String phone,
    @NotNull MerchantType merchantType) {}
