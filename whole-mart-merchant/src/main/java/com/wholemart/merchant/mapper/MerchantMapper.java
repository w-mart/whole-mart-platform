package com.wholemart.merchant.mapper;

import com.wholemart.merchant.dto.MerchantResponse;
import com.wholemart.merchant.entity.Merchant;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {
    public MerchantResponse toResponse(Merchant merchant) {
        return new MerchantResponse(merchant.getId(), merchant.getUserId(), merchant.getBusinessName(), merchant.getGstin(), merchant.getPhone(), merchant.getMerchantType(), merchant.getStatus(), merchant.getCreatedAt());
    }
}
