package com.wholemart.identity.mapper;

import com.wholemart.identity.dto.UserResponse;
import com.wholemart.identity.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper { public UserResponse toResponse(User user) { return new UserResponse(user.getId(), user.getFullName(), user.getPhone(), user.getCreatedAt()); } }
