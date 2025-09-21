package com.cesarfraaga.productmanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstants {

    public static final String PERIOD = ".";
    public static final String CLIENT_ID_NOT_FOUND_MESSAGE_PREFIX = "Client not found with ID ";
    public static final String CLIENT_ID_NULL_MESSAGE = "Client id cannot be null.";
    public static final String CLIENT_NOT_FOUND_MESSAGE = "Client not found.";
    public static final String CLIENTS_NOT_FOUND_MESSAGE = "No clients found.";
    public static final String CLIENT_NAME_NULL_OR_EMPTY_MESSAGE = "Name cannot be null or empty.";
    public static final String CLIENT_NAME_LENGTH_MESSAGE = "Name cannot be less than 2 or longer than 50 characters.";
    public static final String CLIENT_NAME_NOT_SPECIAL_CHARACTER_MESSAGE = "The client name cannot contain special characters!";
    public static final String CLIENT_CPF_NULL_OR_EMPTY_MESSAGE = "CPF cannot be null or empty.";
    public static final String CLIENT_CPF_ONLY_NUMBER_MESSAGE = "Only numbers allowed!";
    public static final String CLIENT_BIRTHDAY_NULL_OR_EMPTY_MESSAGE = "Birth day cannot be null or empty.";
    public static final String CLIENT_BIRTHDAY_ONLY_NUMBER_MESSAGE = "Only numbers allowed, respect this format: MM/dd/yyyy";

    public static final String PRODUCT_ID_NOT_FOUND_MESSAGE = "Product not found with ID ";
    public static final String PRODUCT_ID_NOT_NULL_MESSAGE = "Product id cannot be null.";
    public static final String PRODUCT_NO_AVAILABLE_MESSAGE = "No products available.";
    public static final String PRODUCT_NAME_NULL_OR_EMPTY_MESSAGE = "Product name cannot be null or empty.";
    public static final String PRODUCT_NAME_LENGTH_MESSAGE = "Product name cannot be less than 2 or longer than 50 characters.";
    public static final String PRODUCT_NAME_NOT_SPECIAL_CHARACTER_MESSAGE = "The product name cannot contain special characters!";
    public static final String PRODUCT_DESCRIPTION_NULL_OR_EMPTY_MESSAGE = "Description cannot be null or empty.";
    public static final String PRODUCT_DESCRIPTION_NOT_SPECIAL_CHARACTER_MESSAGE = "The product description cannot contain special characters!";
    public static final String PRODUCT_PRICE_GREATER_THAN_ZERO_MESSAGE = "Price must be greater than zero.";

    public static final String SHOPPINGCART_PRODUCT_ID_IS_NULL_OR_EMPTY = "";
    public static final String SHOPPINGCART_ID_NOT_FOUND_MESSAGE = "Shopping Cart not found with ID " ;
    public static final String SHOPPINGCART_NOT_FOUND_MESSAGE = "Shopping Cart not found.";
    public static final String SHOPPINGCART_NO_AVAILABLE_MESSAGE = "No shopping carts available.";

    public static final String SALE_SHOPPINGCART_NOT_NULL_OR_EMPTY_MESSAGE = "Shopping cart cannot be null or empty.";
    public static final String SALE_CLIENT_ID_NOT_NULL_OR_EMPTY_MESSAGE = "Client id cannot be null or empty.";
    public static final String SALE_ID_NOT_FOUND_MESSAGE = "Sale not found with ID ";
    public static final String SALE_NOT_FOUND_MESSAGE = "Sale not found.";
    public static final String SALE_NOT_FOUND_FOR_DELETE_MESSAGE = "Sale not found for delete.";

    public static final String BASIC_CHARACTER = "^[a-zA-ZÀ-ÿ\\s]+$";
    public static final String ONLY_NUMBER = "^[0-9]+$";
    public static final String SHOPPINGCART_STOCK_NOT_ENOUGH_PATTERN = "Shopping cart asked for %s units of product \"%s\" (id=%s). Maximum available: %s.";
}
