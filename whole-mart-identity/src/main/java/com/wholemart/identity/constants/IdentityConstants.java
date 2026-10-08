package com.wholemart.identity.constants;

import com.wholemart.common.constants.RoleConstants;

public final class IdentityConstants {
    public static final String DEFAULT_USER_ROLE = RoleConstants.SHOPKEEPER;
    public static final int OTP_LENGTH = 6;
    public static final int OTP_VALUE_COUNT = 1_000_000;
    public static final String OTP_FORMAT = "%06d";
    public static final long OTP_TTL_SECONDS = 300;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 72;
    public static final int MAX_PERSON_NAME_LENGTH = 100;
    private IdentityConstants() {}
}
