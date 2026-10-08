package com.wholemart.catalog.service;

import com.wholemart.catalog.constants.CatalogMessages;
import com.wholemart.catalog.dto.*;
import com.wholemart.catalog.entity.*;
import com.wholemart.catalog.repository.*;
import com.wholemart.common.exception.BusinessException;
import com.wholemart.common.exception.ResourceNotFoundException;
import com.wholemart.merchant.repository.MerchantRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogServiceImpl implements CatalogService {
    private final ProductRepository products; private final MerchantProductRepository merchantProducts;
    private final InventoryRepository inventory; private final MerchantRepository merchants;
    public CatalogServiceImpl(ProductRepository products, MerchantProductRepository merchantProducts, InventoryRepository inventory, MerchantRepository merchants) { this.products = products; this.merchantProducts = merchantProducts; this.inventory = inventory; this.merchants = merchants; }
    @Override @Transactional public ProductResponse createProduct(CreateProductRequest request) {
        String sku = request.sku().trim().toUpperCase(java.util.Locale.ROOT);
        if (products.existsBySku(sku)) throw new BusinessException(CatalogMessages.PRODUCT_ALREADY_EXISTS);
        return productResponse(products.save(new Product(request.name().trim(), trim(request.brand()), trim(request.category()), sku)));
    }
    @Override @Transactional(readOnly = true) public Page<ProductResponse> searchProducts(String name, Pageable pageable) { return products.findByNameContainingIgnoreCase(name == null ? "" : name.trim(), pageable).map(this::productResponse); }
    @Override @Transactional public MerchantProductResponse createMerchantProduct(UUID userId, CreateMerchantProductRequest request) {
        ensureMerchantOwner(userId, request.merchantId());
        if (!products.existsById(request.productId())) throw new ResourceNotFoundException(CatalogMessages.PRODUCT_NOT_FOUND);
        if (merchantProducts.existsByMerchantIdAndProductId(request.merchantId(), request.productId())) throw new BusinessException(CatalogMessages.MERCHANT_PRODUCT_ALREADY_EXISTS);
        MerchantProduct merchantProduct = merchantProducts.save(new MerchantProduct(request.merchantId(), request.productId(), request.price()));
        Inventory stock = inventory.save(new Inventory(merchantProduct.getId(), request.stock()));
        return merchantProductResponse(merchantProduct, stock.getQuantity());
    }
    @Override @Transactional public MerchantProductResponse updateMerchantProduct(UUID userId, UUID id, UpdateMerchantProductRequest request) {
        MerchantProduct merchantProduct = merchantProducts.findById(id).orElseThrow(() -> new ResourceNotFoundException(CatalogMessages.MERCHANT_PRODUCT_NOT_FOUND));
        ensureMerchantOwner(userId, merchantProduct.getMerchantId()); merchantProduct.update(request.price());
        Inventory stock = inventory.findByMerchantProductId(id).orElseGet(() -> new Inventory(id, request.stock())); stock.setQuantity(request.stock()); inventory.save(stock);
        return merchantProductResponse(merchantProduct, stock.getQuantity());
    }
    @Override @Transactional(readOnly = true) public List<MerchantProductResponse> merchantProducts(UUID merchantId) { return merchantProducts.findByMerchantId(merchantId).stream().map(item -> merchantProductResponse(item, inventory.findByMerchantProductId(item.getId()).map(Inventory::getQuantity).orElse(0))).toList(); }
    private void ensureMerchantOwner(UUID userId, UUID merchantId) { var merchant = merchants.findById(merchantId).orElseThrow(() -> new ResourceNotFoundException(CatalogMessages.MERCHANT_NOT_FOUND)); if (!merchant.getUserId().equals(userId)) throw new BusinessException(CatalogMessages.MERCHANT_ACCESS_DENIED); }
    private ProductResponse productResponse(Product product) { return new ProductResponse(product.getId(), product.getName(), product.getBrand(), product.getCategory(), product.getSku(), product.getCreatedAt()); }
    private MerchantProductResponse merchantProductResponse(MerchantProduct item, int stock) { return new MerchantProductResponse(item.getId(), item.getMerchantId(), item.getProductId(), item.getPrice(), stock, item.getCreatedAt()); }
    private String trim(String value) { return value == null ? null : value.trim(); }
}
