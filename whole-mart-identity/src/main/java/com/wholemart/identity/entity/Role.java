package com.wholemart.identity.entity;

import com.wholemart.common.constants.ValidationConstants;
import com.wholemart.identity.constants.IdentityPersistenceConstants;
import jakarta.persistence.*;

@Entity
@Table(name = IdentityPersistenceConstants.ROLES_TABLE, uniqueConstraints = @UniqueConstraint(name = IdentityPersistenceConstants.ROLE_NAME_UNIQUE_CONSTRAINT, columnNames = IdentityPersistenceConstants.ROLE_NAME_COLUMN))
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
