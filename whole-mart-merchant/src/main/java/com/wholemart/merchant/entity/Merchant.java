package com.wholemart.merchant.entity;

import com.wholemart.common.constants.ValidationConstants;
import com.wholemart.merchant.constants.MerchantConstants;
import com.wholemart.merchant.constants.MerchantPersistenceConstants;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = MerchantPersistenceConstants.MERCHANTS_TABLE, uniqueConstraints = {
    @UniqueConstraint(name = MerchantPersistenceConstants.USER_UNIQUE_CONSTRAINT, columnNames = MerchantPersistenceConstants.USER_ID_COLUMN),
    @UniqueConstraint(name = MerchantPersistenceConstants.GSTIN_UNIQUE_CONSTRAINT, columnNames = MerchantPersistenceConstants.GSTIN_COLUMN)
})
public class Merchant {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = MerchantPersistenceConstants.USER_ID_COLUMN, nullable = false, updatable = false)
    private UUID userId;
    @Column(nullable = false, length = MerchantConstants.MAX_BUSINESS_NAME_LENGTH)
    private String businessName;
    @Column(length = MerchantConstants.GSTIN_LENGTH)
    private String gstin;
    @Column(nullable = false, length = ValidationConstants.PHONE_LENGTH)
    private String phone;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private MerchantType merchantType;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private MerchantStatus status = MerchantStatus.ACTIVE;
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Merchant() {}
    public Merchant(UUID userId, String businessName, String gstin, String phone, MerchantType merchantType) {
        this.userId = userId; this.businessName = businessName; this.gstin = gstin; this.phone = phone; this.merchantType = merchantType;
    }
    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getBusinessName() { return businessName; }
    public String getGstin() { return gstin; }
    public String getPhone() { return phone; }
    public MerchantType getMerchantType() { return merchantType; }
    public MerchantStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public void update(String businessName, String gstin, String phone, MerchantType merchantType) {
        this.businessName = businessName; this.gstin = gstin; this.phone = phone; this.merchantType = merchantType;
    }
}
