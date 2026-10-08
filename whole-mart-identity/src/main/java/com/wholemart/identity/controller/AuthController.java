package com.wholemart.identity.controller;

import com.wholemart.common.response.ApiResponse;
import com.wholemart.common.constants.ApiPathConstants;
import com.wholemart.common.constants.MessageConstants;
import com.wholemart.identity.dto.*;
import com.wholemart.identity.service.IdentityFacadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPathConstants.AUTH)
public class AuthController {
    private final IdentityFacadeService identity;
    public AuthController(IdentityFacadeService identity) { this.identity = identity; }
    @PostMapping(ApiPathConstants.AUTH_REGISTER) @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<LoginResponse> register(@Valid @RequestBody RegisterRequest request) { return ApiResponse.success(identity.register(request), MessageConstants.ACCOUNT_CREATED); }
    @PostMapping(ApiPathConstants.AUTH_LOGIN)
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) { return ApiResponse.success(identity.login(request.phone(), request.password())); }
    @PostMapping(ApiPathConstants.AUTH_SEND_OTP)
    public ApiResponse<Void> sendOtp(@Valid @RequestBody SendOtpRequest request) { identity.sendOtp(request.phone()); return ApiResponse.success(null, MessageConstants.OTP_SENT_IF_ELIGIBLE); }
    @PostMapping(ApiPathConstants.AUTH_VERIFY_OTP)
    public ApiResponse<LoginResponse> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) { return ApiResponse.success(identity.verifyOtp(request.phone(), request.code())); }
}
