package com.wholemart.identity.dto;

import com.wholemart.common.constants.ValidationConstants;
import com.wholemart.identity.constants.IdentityConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerifyOtpRequest(@NotBlank @Pattern(regexp = ValidationConstants.INDIAN_MOBILE_REGEX) String phone, @NotBlank @Pattern(regexp = IdentityConstants.OTP_REGEX) String code) {}
