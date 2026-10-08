package com.wholemart.common.util;

import com.wholemart.common.constants.BusinessConstants;
import java.math.BigDecimal;

public final class AmountUtil {
    private AmountUtil() {
    }

    public static BigDecimal normalize(BigDecimal amount) {
        return amount.setScale(BusinessConstants.CURRENCY_FRACTION_DIGITS, BusinessConstants.CURRENCY_ROUNDING_MODE);
    }
}
