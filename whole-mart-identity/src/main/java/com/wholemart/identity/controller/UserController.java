package com.wholemart.identity.controller;

import com.wholemart.common.response.ApiResponse;
import com.wholemart.common.security.CurrentUser;
import com.wholemart.identity.constants.IdentityApiPaths;
import com.wholemart.identity.dto.UserResponse;
import com.wholemart.identity.service.IdentityFacadeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping(IdentityApiPaths.USERS)
public class UserController {
    private final IdentityFacadeService identity;
    public UserController(IdentityFacadeService identity) { this.identity = identity; }
    @GetMapping(IdentityApiPaths.CURRENT_USER) public ApiResponse<UserResponse> me() { return ApiResponse.success(identity.currentUser(CurrentUser.id())); }
}
