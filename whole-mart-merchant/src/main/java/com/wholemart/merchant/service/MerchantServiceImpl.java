package com.wholemart.merchant.service;

import com.wholemart.common.exception.BusinessException;
import com.wholemart.common.exception.ResourceNotFoundException;
import com.wholemart.merchant.constants.MerchantMessages;
import com.wholemart.merchant.dto.CreateMerchantRequest;
import com.wholemart.merchant.dto.MerchantResponse;
import com.wholemart.merchant.dto.UpdateMerchantRequest;
import com.wholemart.merchant.entity.Merchant;
import com.wholemart.merchant.entity.MerchantType;
import com.wholemart.merchant.mapper.MerchantMapper;
import com.wholemart.merchant.repository.MerchantRepository;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchants;
    private final MerchantMapper mapper;
    public MerchantServiceImpl(MerchantRepository merchants, MerchantMapper mapper) { this.merchants = merchants; this.mapper = mapper; }

    @Override @Transactional
    public MerchantResponse create(UUID userId, CreateMerchantRequest request) {
        if (merchants.existsByUserId(userId)) throw new BusinessException(MerchantMessages.ALREADY_EXISTS);
        String gstin = normalizeGstin(request.gstin());
        ensureGstinAvailable(gstin);
        return mapper.toResponse(merchants.save(new Merchant(userId, request.businessName().trim(), gstin, request.phone(), request.merchantType())));
    }

    @Override @Transactional(readOnly = true)
    public MerchantResponse get(UUID id) { return mapper.toResponse(merchants.findById(id).orElseThrow(() -> new ResourceNotFoundException(MerchantMessages.NOT_FOUND))); }

    @Override @Transactional
    public MerchantResponse update(UUID userId, UUID id, UpdateMerchantRequest request) {
        Merchant merchant = merchants.findByIdAndUserId(id, userId).orElseThrow(() -> new ResourceNotFoundException(MerchantMessages.NOT_FOUND));
        String gstin = normalizeGstin(request.gstin());
        if (gstin != null && !gstin.equals(merchant.getGstin())) ensureGstinAvailable(gstin);
        merchant.update(request.businessName().trim(), gstin, request.phone(), request.merchantType());
        return mapper.toResponse(merchant);
    }

    @Override @Transactional(readOnly = true)
    public Page<MerchantResponse> search(String name, MerchantType type, Pageable pageable) {
        String normalizedName = name == null || name.isBlank() ? null : name.trim();
        return merchants.search(normalizedName, type, pageable).map(mapper::toResponse);
    }

    private void ensureGstinAvailable(String gstin) {
        if (gstin != null && merchants.existsByGstin(gstin)) throw new BusinessException(MerchantMessages.GSTIN_ALREADY_REGISTERED);
    }
    private String normalizeGstin(String gstin) { return gstin == null || gstin.isBlank() ? null : gstin.trim().toUpperCase(java.util.Locale.ROOT); }
}
