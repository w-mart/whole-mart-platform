package com.wholemart.common.security;

import com.wholemart.common.exception.UnauthorizedException;
import com.wholemart.common.constants.MessageConstants;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class CurrentUser {
    private CurrentUser() {
    }

    public static UUID id() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UUID id)) {
            throw new UnauthorizedException(MessageConstants.AUTHENTICATION_REQUIRED);
        }
        return id;
    }
}
