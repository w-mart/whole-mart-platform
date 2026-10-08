package com.wholemart.common.constants;

public final class ApiPathConstants {
    public static final String AUTH = CommonConstants.API_PREFIX + "/auth";
    public static final String AUTH_REGISTER = "/register";
    public static final String AUTH_LOGIN = "/login";
    public static final String AUTH_SEND_OTP = "/send-otp";
    public static final String AUTH_VERIFY_OTP = "/verify-otp";
    public static final String USERS = CommonConstants.API_PREFIX + "/users";
    public static final String USER_ME = "/me";
    public static final String MERCHANTS = CommonConstants.API_PREFIX + "/merchants";
    public static final String MERCHANT_SEARCH = "/search";
    public static final String MERCHANT_BY_ID = "/{id}";
    public static final String ACTUATOR_HEALTH = "/actuator/health";
    private ApiPathConstants() {}
}
