package com.wholemart.merchant.constants;

public final class MerchantConstants {
    public static final int MAX_BUSINESS_NAME_LENGTH = 150;
    public static final int GSTIN_LENGTH = 15;
    public static final String OPTIONAL_GSTIN_REGEX = "^$|^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][1-9A-Z]Z[0-9A-Z]$";
    public static final String INVALID_GSTIN = "GSTIN must be a valid 15-character GSTIN";
    public static final String INVALID_MOBILE = "Enter a valid Indian mobile number";
    public static final String BUSINESS_NAME_SORT_FIELD = "businessName";
    private MerchantConstants() {}
}
