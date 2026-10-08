package com.wholemart.common.constants;

public final class SecurityConstants {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String JWT_SECRET_PROPERTY = "${wholemart.security.jwt-secret}";
    public static final String BEARER_TOKEN_TYPE = "Bearer";
    public static final String JWT_ALGORITHM = "HmacSHA256";
    public static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    public static final String JWT_SUBJECT_CLAIM = "sub";
    public static final String JWT_ISSUED_AT_CLAIM = "iat";
    public static final String JWT_EXPIRATION_CLAIM = "exp";
    public static final String JWT_TOKEN_SEPARATOR = ".";
    public static final String JWT_TOKEN_SPLIT_REGEX = "\\.";
    public static final int JWT_TOKEN_PART_COUNT = 3;
    public static final int JWT_MIN_SECRET_BYTES = 32;
    public static final String JWT_SECRET_TOO_SHORT = "common.error.jwt-secret-too-short";
    private SecurityConstants() {}
}
