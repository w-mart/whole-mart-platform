package com.wholemart.identity.security;

import com.wholemart.common.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class BearerTokenFilter extends OncePerRequestFilter {
    private final TokenService tokens;
    public BearerTokenFilter(TokenService tokens) { this.tokens = tokens; }
    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorization = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);
        if (authorization != null && authorization.startsWith(SecurityConstants.BEARER_PREFIX)) {
            var userId = tokens.verify(authorization.substring(SecurityConstants.BEARER_PREFIX.length()));
            if (userId != null) SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, java.util.List.of()));
        }
        chain.doFilter(request, response);
    }
}
