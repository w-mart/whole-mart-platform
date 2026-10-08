package com.wholemart.identity.dto;

import com.wholemart.common.constants.ValidationConstants;
import com.wholemart.identity.constants.IdentityConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank @Pattern(regexp = ValidationConstants.INDIAN_MOBILE_REGEX) String phone,
                              @NotBlank @Size(min = IdentityConstants.MIN_PASSWORD_LENGTH, max = IdentityConstants.MAX_PASSWORD_LENGTH) String password,
                              @NotBlank @Size(max = IdentityConstants.MAX_PERSON_NAME_LENGTH) String fullName) {}
