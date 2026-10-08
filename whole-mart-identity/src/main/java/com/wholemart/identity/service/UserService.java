package com.wholemart.identity.service;

import com.wholemart.identity.dto.LoginResponse;
import com.wholemart.identity.dto.RegisterRequest;
import com.wholemart.identity.entity.User;
import java.util.UUID;

public interface UserService {
    User register(RegisterRequest request);
    LoginResponse login(String phone, String password);
    User getByPhone(String phone);
    User getById(UUID id);
}
