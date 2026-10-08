package com.wholemart.identity.dto;

public record LoginResponse(String accessToken, String tokenType, long expiresInSeconds, UserResponse user) {}
