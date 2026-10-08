package com.wholemart.common.util;

import com.wholemart.common.constants.ValidationConstants;
import java.util.regex.Pattern;

public final class ValidationUtil {
    private static final Pattern PHONE = Pattern.compile(ValidationConstants.INDIAN_MOBILE_REGEX);

    private ValidationUtil() {
    }

    public static boolean isIndianMobile(String value) {
        return value != null && PHONE.matcher(value).matches();
    }
}
