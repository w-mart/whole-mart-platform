package com.wholemart.catalog.constants;

public final class CatalogConstants {
    public static final int MAX_PRODUCT_NAME_LENGTH = 150;
    public static final int MAX_BRAND_LENGTH = 100;
    public static final int MAX_CATEGORY_LENGTH = 100;
    public static final int MAX_SKU_LENGTH = 64;
    public static final int MAX_QUANTITY = 1_000_000;
    public static final String MIN_PRICE = "0.01";
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";
    public static final int MIN_PAGE = 0;
    public static final int MIN_PAGE_SIZE = 1;
    public static final int MAX_PAGE_SIZE = 100;
    public static final String PRODUCT_NAME_SORT_FIELD = "name";
    public static final String PRODUCTS_PATH = "/api/products";
    public static final String MERCHANT_PRODUCTS_PATH = "/api/merchant-products";
    public static final String MERCHANT_PRODUCTS_BY_MERCHANT_PATH = "/api/merchants/{id}/products";
    public static final String SEARCH_PATH = "/search";
    public static final String BY_ID_PATH = "/{id}";
    private CatalogConstants() {}
}
