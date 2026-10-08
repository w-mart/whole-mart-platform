package com.wholemart.identity.controller;

import com.wholemart.common.response.ApiResponse;
import com.wholemart.common.security.CurrentUser;
import com.wholemart.common.constants.ApiPathConstants;
import com.wholemart.identity.dto.UserResponse;
import com.wholemart.identity.service.IdentityFacadeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping(ApiPathConstants.USERS)
public class UserController {
    private final IdentityFacadeService identity;
    public UserController(IdentityFacadeService identity) { this.identity = identity; }
    @GetMapping(ApiPathConstants.USER_ME) public ApiResponse<UserResponse> me() { return ApiResponse.success(identity.currentUser(CurrentUser.id())); }
}
