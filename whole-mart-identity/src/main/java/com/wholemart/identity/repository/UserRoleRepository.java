package com.wholemart.identity.repository;

import com.wholemart.identity.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRole.Id> {}
