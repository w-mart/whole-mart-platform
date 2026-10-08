package com.wholemart.merchant.repository;

import com.wholemart.merchant.entity.Merchant;
import com.wholemart.merchant.entity.MerchantType;
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
    @Query("select m from Merchant m where m.status = com.wholemart.merchant.entity.MerchantStatus.ACTIVE " +
        "and (:name is null or lower(m.businessName) like lower(concat('%', :name, '%'))) " +
        "and (:type is null or m.merchantType = :type)")
    Page<Merchant> search(@Param("name") String name, @Param("type") MerchantType type, Pageable pageable);
}
