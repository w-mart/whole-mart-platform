package com.wholemart.identity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wholemart.common.constants.BusinessConstants;
import com.wholemart.common.constants.MessageConstants;
import com.wholemart.common.constants.SecurityConstants;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenService {
    private final byte[] key;
    private final ObjectMapper json;
    public TokenService(@Value(SecurityConstants.JWT_SECRET_PROPERTY) String secret, ObjectMapper json) {
        if (secret.getBytes(StandardCharsets.UTF_8).length < SecurityConstants.JWT_MIN_SECRET_BYTES) throw new IllegalArgumentException(com.wholemart.common.constants.MessageConstants.JWT_SECRET_TOO_SHORT);
        this.key = secret.getBytes(StandardCharsets.UTF_8); this.json = json;
    }
    public long expiresInSeconds() { return BusinessConstants.ACCESS_TOKEN_TTL_SECONDS; }
    public String issue(UUID userId) {
        try {
            Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
            String header = encoder.encodeToString(SecurityConstants.JWT_HEADER.getBytes(StandardCharsets.UTF_8));
            long now = Instant.now().getEpochSecond();
            String payload = encoder.encodeToString(json.writeValueAsBytes(Map.of(SecurityConstants.JWT_SUBJECT_CLAIM, userId.toString(), SecurityConstants.JWT_ISSUED_AT_CLAIM, now, SecurityConstants.JWT_EXPIRATION_CLAIM, now + BusinessConstants.ACCESS_TOKEN_TTL_SECONDS)));
            String signingInput = header + "." + payload;
            return signingInput + "." + encoder.encodeToString(sign(signingInput));
        } catch (Exception exception) { throw new IllegalStateException(MessageConstants.ACCESS_TOKEN_ISSUE_FAILED, exception); }
    }
    public UUID verify(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return null;
            String input = parts[0] + "." + parts[1];
            if (!java.security.MessageDigest.isEqual(sign(input), Base64.getUrlDecoder().decode(parts[2]))) return null;
            Map<?, ?> claims = json.readValue(Base64.getUrlDecoder().decode(parts[1]), Map.class);
            if (!(claims.get(SecurityConstants.JWT_EXPIRATION_CLAIM) instanceof Number expires) || expires.longValue() <= Instant.now().getEpochSecond()) return null;
            return UUID.fromString((String) claims.get(SecurityConstants.JWT_SUBJECT_CLAIM));
        } catch (Exception exception) { return null; }
    }
    private byte[] sign(String value) throws Exception {
        Mac mac = Mac.getInstance(SecurityConstants.JWT_ALGORITHM); mac.init(new SecretKeySpec(key, SecurityConstants.JWT_ALGORITHM));
        return mac.doFinal(value.getBytes(StandardCharsets.US_ASCII));
    }
}
