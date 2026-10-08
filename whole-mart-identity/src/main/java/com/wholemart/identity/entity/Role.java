package com.wholemart.identity.entity;

import com.wholemart.common.constants.ValidationConstants;
import jakarta.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "uk_roles_name", columnNames = "name"))
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = ValidationConstants.MAX_ROLE_NAME_LENGTH)
    private String name;
    protected Role() {}
    public Role(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}
