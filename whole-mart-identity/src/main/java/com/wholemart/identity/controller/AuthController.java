package com.wholemart.identity.controller;

import com.wholemart.common.response.ApiResponse;
import com.wholemart.common.message.MessageResolver;
import com.wholemart.identity.constants.IdentityApiPaths;
import com.wholemart.identity.constants.IdentityMessages;
import com.wholemart.identity.dto.*;
import com.wholemart.identity.service.IdentityFacadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IdentityApiPaths.AUTH)
public class AuthController {
    private final IdentityFacadeService identity;
    private final MessageResolver messages;
    public AuthController(IdentityFacadeService identity, MessageResolver messages) { this.identity = identity; this.messages = messages; }
    @PostMapping(IdentityApiPaths.REGISTER) @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<LoginResponse> register(@Valid @RequestBody RegisterRequest request) { return ApiResponse.success(identity.register(request), messages.get(IdentityMessages.ACCOUNT_CREATED)); }
    @PostMapping(IdentityApiPaths.LOGIN)
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) { return ApiResponse.success(identity.login(request.phone(), request.password())); }
    @PostMapping(IdentityApiPaths.SEND_OTP)
    public ApiResponse<Void> sendOtp(@Valid @RequestBody SendOtpRequest request) { identity.sendOtp(request.phone()); return ApiResponse.success(null, messages.get(IdentityMessages.OTP_SENT_IF_ELIGIBLE)); }
    @PostMapping(IdentityApiPaths.VERIFY_OTP)
    public ApiResponse<LoginResponse> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) { return ApiResponse.success(identity.verifyOtp(request.phone(), request.code())); }
}
