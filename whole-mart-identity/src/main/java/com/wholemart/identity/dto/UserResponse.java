package com.wholemart.identity.dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(UUID id, String fullName, String phone, Instant createdAt) {}
