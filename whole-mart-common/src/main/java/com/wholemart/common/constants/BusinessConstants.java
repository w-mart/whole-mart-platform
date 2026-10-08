package com.wholemart.common.constants;

import java.math.RoundingMode;

public final class BusinessConstants {
    public static final long ACCESS_TOKEN_TTL_SECONDS = 3600;
    public static final int CURRENCY_FRACTION_DIGITS = 2;
    public static final RoundingMode CURRENCY_ROUNDING_MODE = RoundingMode.HALF_EVEN;
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final String DEFAULT_PAGE_PARAMETER = "0";
    public static final String DEFAULT_PAGE_SIZE_PARAMETER = "20";
    public static final int MIN_PAGE_SIZE = 1;
    public static final int MAX_PAGE_SIZE = 100;
    private BusinessConstants() {}
}
