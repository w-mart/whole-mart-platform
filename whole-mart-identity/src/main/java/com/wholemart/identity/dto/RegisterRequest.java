package com.wholemart.identity.dto;

import com.wholemart.common.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank @Pattern(regexp = ValidationConstants.INDIAN_MOBILE_REGEX) String phone,
                              @NotBlank @Size(min = ValidationConstants.MIN_PASSWORD_LENGTH, max = ValidationConstants.MAX_PASSWORD_LENGTH) String password,
                              @NotBlank @Size(max = ValidationConstants.MAX_PERSON_NAME_LENGTH) String fullName) {}
