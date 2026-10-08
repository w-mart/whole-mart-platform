package com.wholemart.cart.constants;

public final class CartConstants {
    public static final String API_PATH = "/api/cart";
    public static final String ITEMS_PATH = "/items";
    public static final String ITEM_BY_ID_PATH = "/items/{id}";
    public static final int MIN_QUANTITY = 1;
    public static final int MAX_QUANTITY = 1_000_000;
    private CartConstants() {}
}
