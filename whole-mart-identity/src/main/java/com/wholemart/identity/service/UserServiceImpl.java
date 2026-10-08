package com.wholemart.identity.service;

import com.wholemart.common.exception.BusinessException;
import com.wholemart.common.exception.ResourceNotFoundException;
import com.wholemart.identity.constants.IdentityConstants;
import com.wholemart.identity.constants.IdentityMessages;
import com.wholemart.common.constants.SecurityConstants;
import com.wholemart.identity.dto.LoginResponse;
import com.wholemart.identity.dto.RegisterRequest;
import com.wholemart.identity.dto.UserResponse;
import com.wholemart.identity.entity.Role;
import com.wholemart.identity.entity.User;
import com.wholemart.identity.entity.UserRole;
import com.wholemart.identity.mapper.UserMapper;
import com.wholemart.identity.repository.RoleRepository;
import com.wholemart.identity.repository.UserRepository;
import com.wholemart.identity.repository.UserRoleRepository;
import com.wholemart.identity.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository users;
    private final RoleRepository roles;
    private final UserRoleRepository userRoles;
    private final PasswordEncoder passwords;
    private final UserMapper mapper;
    private final TokenService tokens;

    public UserServiceImpl(UserRepository users, RoleRepository roles, UserRoleRepository userRoles, PasswordEncoder passwords, UserMapper mapper, TokenService tokens) {
        this.users = users; this.roles = roles; this.userRoles = userRoles; this.passwords = passwords; this.mapper = mapper; this.tokens = tokens;
    }
    @Override @Transactional
    public User register(RegisterRequest request) {
        if (users.existsByPhone(request.phone())) throw new BusinessException(IdentityMessages.PHONE_ALREADY_REGISTERED);
        User user = users.save(new User(request.phone(), passwords.encode(request.password()), request.fullName().trim()));
        Role role = roles.findByName(IdentityConstants.DEFAULT_USER_ROLE).orElseGet(() -> roles.save(new Role(IdentityConstants.DEFAULT_USER_ROLE)));
        userRoles.save(new UserRole(user, role));
        return user;
    }
    @Override @Transactional(readOnly = true)
    public LoginResponse login(String phone, String password) {
        User user = getByPhone(phone);
        if (!user.isActive() || !passwords.matches(password, user.getPasswordHash())) throw new BusinessException(IdentityMessages.INVALID_CREDENTIALS);
        UserResponse response = mapper.toResponse(user);
        return new LoginResponse(tokens.issue(user.getId()), SecurityConstants.BEARER_TOKEN_TYPE, tokens.expiresInSeconds(), response);
    }
    @Override @Transactional(readOnly = true)
    public User getByPhone(String phone) { return users.findByPhone(phone).orElseThrow(() -> new ResourceNotFoundException(IdentityMessages.USER_NOT_FOUND)); }
    @Override @Transactional(readOnly = true)
    public User getById(UUID id) { return users.findById(id).orElseThrow(() -> new ResourceNotFoundException(IdentityMessages.USER_NOT_FOUND)); }
}
