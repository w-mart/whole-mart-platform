package com.wholemart.merchant.repository;

import com.wholemart.merchant.entity.Merchant;
import com.wholemart.merchant.entity.MerchantType;
import com.wholemart.merchant.constants.MerchantPersistenceConstants;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    boolean existsByUserId(UUID userId);

    boolean existsByGstin(String gstin);

    Optional<Merchant> findByIdAndUserId(UUID id, UUID userId);

    @Query(MerchantPersistenceConstants.SEARCH_QUERY)
    Page<Merchant> search(@Param(MerchantPersistenceConstants.SEARCH_NAME_PARAMETER) String name,
            @Param(MerchantPersistenceConstants.SEARCH_TYPE_PARAMETER) MerchantType type, Pageable pageable);
}
