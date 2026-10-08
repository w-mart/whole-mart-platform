package com.wholemart.identity.entity;

import jakarta.persistence.*;
import com.wholemart.common.constants.ValidationConstants;
import com.wholemart.identity.constants.IdentityConstants;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uk_users_phone", columnNames = "phone"))
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = ValidationConstants.PHONE_LENGTH)
    private String phone;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false, length = IdentityConstants.MAX_PERSON_NAME_LENGTH)
    private String fullName;
    @Column(nullable = false)
    private boolean active = true;
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected User() {}
    public User(String phone, String passwordHash, String fullName) { this.phone = phone; this.passwordHash = passwordHash; this.fullName = fullName; }
    public UUID getId() { return id; }
    public String getPhone() { return phone; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }
}
