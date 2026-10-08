package com.wholemart.merchant.constants;

public final class MerchantPersistenceConstants {
    public static final String MERCHANTS_TABLE = "merchants";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String GSTIN_COLUMN = "gstin";
    public static final String USER_UNIQUE_CONSTRAINT = "uk_merchants_user";
    public static final String GSTIN_UNIQUE_CONSTRAINT = "uk_merchants_gstin";
    public static final String SEARCH_NAME_PARAMETER = "name";
    public static final String SEARCH_TYPE_PARAMETER = "type";
    public static final String SEARCH_QUERY = "select m from Merchant m where m.status = com.wholemart.merchant.entity.MerchantStatus.ACTIVE "
        + "and (:name is null or lower(m.businessName) like lower(concat('%', :name, '%'))) "
        + "and (:type is null or m.merchantType = :type)";
    private MerchantPersistenceConstants() {}
}
