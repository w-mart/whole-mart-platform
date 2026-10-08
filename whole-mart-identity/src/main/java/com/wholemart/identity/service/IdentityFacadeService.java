package com.wholemart.identity.service;

import com.wholemart.identity.dto.LoginResponse;
import com.wholemart.identity.dto.RegisterRequest;
import com.wholemart.identity.dto.UserResponse;

public interface IdentityFacadeService {
    LoginResponse register(RegisterRequest request);
    LoginResponse login(String phone, String password);
    void sendOtp(String phone);
    LoginResponse verifyOtp(String phone, String code);
    UserResponse currentUser(java.util.UUID id);
}
