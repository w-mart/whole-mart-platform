package com.wholemart.merchant.constants;

import com.wholemart.common.constants.CommonConstants;

public final class MerchantApiPaths {
    public static final String MERCHANTS = CommonConstants.API_PREFIX + "/merchants";
    public static final String BY_ID = "/{id}";
    public static final String SEARCH = "/search";

    private MerchantApiPaths() {
    }
}
