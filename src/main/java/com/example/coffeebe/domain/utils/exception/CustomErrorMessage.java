package com.example.coffeebe.domain.utils.exception;


public enum CustomErrorMessage {
    PRODUCT_NOT_FOUND("Product not found"),

    PRODUCT_RATING_NOT_FOUND("ProductRating not found"),
    CATEGORY_NOT_FOUND("Category not found"),
    SLIDER_NOT_FOUND("Slider not found"),
    DISCOUNT_NOT_FOUND("Discount not found"),
    ORDER_NOT_FOUND("Oder not found"),
    TRANSACTION_NOT_FOUND("Transaction not found"),
    USER_NOT_FOUND("User not found"),
    DISCOUNT_EXPIRED_TIME("Discount expired time"),
    PRODUCT_INVALID("Product invalid"),
    INVENTORY_NOT_ENOUGH("Inventory not enough"),
    TRANSACTION_STATUS_INCORRECT("Transaction status incorrect"),
    TIME_INVALID("Invalid time"),
    NAME_EXISTS("Name already exists"),
    DUPLICATE_TIME_RECORD("Duplicate time record"),
    INVALID_PRODUCT_ID("ProductID invalid"),
    PAYMENT_ERROR("Payment error");

    public final String val;

    private CustomErrorMessage(String label) {
        val = label;
    }
}
