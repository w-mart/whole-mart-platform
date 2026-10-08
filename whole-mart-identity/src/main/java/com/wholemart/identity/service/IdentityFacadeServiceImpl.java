package com.wholemart.identity.service;

import com.wholemart.common.exception.BusinessException;
import com.wholemart.identity.constants.IdentityConstants;
import com.wholemart.identity.constants.IdentityMessages;
import com.wholemart.common.constants.SecurityConstants;
import com.wholemart.common.message.MessageResolver;
import com.wholemart.identity.dto.LoginResponse;
import com.wholemart.identity.dto.RegisterRequest;
import com.wholemart.identity.dto.UserResponse;
import com.wholemart.identity.entity.User;
import com.wholemart.identity.mapper.UserMapper;
import com.wholemart.identity.security.TokenService;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IdentityFacadeServiceImpl implements IdentityFacadeService {
    private static final Logger log = LoggerFactory.getLogger(IdentityFacadeServiceImpl.class);
    private final UserService users; private final UserMapper mapper; private final TokenService tokens; private final MessageResolver messages;
    private final SecureRandom random = new SecureRandom();
    private final ConcurrentHashMap<String, Otp> otps = new ConcurrentHashMap<>();
    public IdentityFacadeServiceImpl(UserService users, UserMapper mapper, TokenService tokens, MessageResolver messages) { this.users = users; this.mapper = mapper; this.tokens = tokens; this.messages = messages; }
    @Override public LoginResponse register(RegisterRequest request) { User user = users.register(request); return response(user); }
    @Override public LoginResponse login(String phone, String password) { return users.login(phone, password); }
    @Override public void sendOtp(String phone) {
        String code = IdentityConstants.OTP_FORMAT.formatted(random.nextInt(IdentityConstants.OTP_VALUE_COUNT));
        otps.put(phone, new Otp(code, Instant.now().plusSeconds(IdentityConstants.OTP_TTL_SECONDS)));
        // Replace this development delivery log with an SMS provider before production.
        log.info(messages.get(IdentityMessages.DEVELOPMENT_OTP_LOG, phone, code));
    }
    @Override public LoginResponse verifyOtp(String phone, String code) {
        Otp otp = otps.remove(phone);
        if (otp == null || otp.expiresAt().isBefore(Instant.now()) || !java.security.MessageDigest.isEqual(otp.code().getBytes(), code.getBytes())) throw new BusinessException(IdentityMessages.INVALID_OR_EXPIRED_OTP);
        User user = users.getByPhone(phone);
        if (!user.isActive()) throw new BusinessException(IdentityMessages.INACTIVE_USER);
        return response(user);
    }
    @Override public UserResponse currentUser(java.util.UUID id) { return mapper.toResponse(users.getById(id)); }
    private LoginResponse response(User user) { return new LoginResponse(tokens.issue(user.getId()), SecurityConstants.BEARER_TOKEN_TYPE, tokens.expiresInSeconds(), mapper.toResponse(user)); }
    private record Otp(String code, Instant expiresAt) {}
}
