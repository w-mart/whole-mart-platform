package com.wholemart.identity.entity;

import jakarta.persistence.*;
import com.wholemart.identity.constants.IdentityPersistenceConstants;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = IdentityPersistenceConstants.USER_ROLES_TABLE)
public class UserRole {
    @EmbeddedId private Id id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @MapsId(IdentityPersistenceConstants.USER_ID_ATTRIBUTE) private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @MapsId(IdentityPersistenceConstants.ROLE_ID_ATTRIBUTE) private Role role;
    protected UserRole() {}
    public UserRole(User user, Role role) { this.user = user; this.role = role; this.id = new Id(user.getId(), role.getId()); }
    @Embeddable public static class Id implements Serializable {
        private java.util.UUID userId; private Long roleId;
        public Id() {} public Id(java.util.UUID userId, Long roleId) { this.userId = userId; this.roleId = roleId; }
        @Override public boolean equals(Object o) { return o instanceof Id that && Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId); }
        @Override public int hashCode() { return Objects.hash(userId, roleId); }
    }
}
