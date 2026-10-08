package com.wholemart.identity.constants;

public final class IdentityPersistenceConstants {
    public static final String USERS_TABLE = "users";
    public static final String ROLES_TABLE = "roles";
    public static final String USER_ROLES_TABLE = "user_roles";
    public static final String PHONE_COLUMN = "phone";
    public static final String ROLE_NAME_COLUMN = "name";
    public static final String USER_ID_ATTRIBUTE = "userId";
    public static final String ROLE_ID_ATTRIBUTE = "roleId";
    public static final String USER_PHONE_UNIQUE_CONSTRAINT = "uk_users_phone";
    public static final String ROLE_NAME_UNIQUE_CONSTRAINT = "uk_roles_name";
    private IdentityPersistenceConstants() {}
}
