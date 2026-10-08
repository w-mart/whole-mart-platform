package com.wholemart.identity.constants;

import com.wholemart.common.constants.CommonConstants;

public final class IdentityApiPaths {
    public static final String AUTH = CommonConstants.API_PREFIX + "/auth";
    public static final String AUTH_WILDCARD = AUTH + "/**";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String SEND_OTP = "/send-otp";
    public static final String VERIFY_OTP = "/verify-otp";
    public static final String USERS = CommonConstants.API_PREFIX + "/users";
    public static final String CURRENT_USER = "/me";
    private IdentityApiPaths() {}
}
