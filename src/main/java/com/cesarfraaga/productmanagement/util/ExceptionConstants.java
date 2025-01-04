package com.cesarfraaga.productmanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstants {

    public static final String PERIOD = ".";
    public static final String CLIENT_ID_NOT_FOUND_MESSAGE = "Client not found with ID ";
    public static final String CLIENT_NOT_FOUND_MESSAGE = "Client not found.";
    public static final String CLIENT_NAME_NULL_OR_EMPTY_MESSAGE = "Name cannot be null or empty.";
    public static final String CLIENT_ID_NULL_MESSAGE = "Id cannot be null.";
    public static final String CLIENT_NAME_LENGTH_MESSAGE = "Name cannot be less than 2 or longer than 50 characters.";
    public static final String CLIENT_CPF_NULL_OR_EMPTY_MESSAGE = "CPF cannot be null or empty.";
    public static final String CLIENT_BIRTHDAY_NULL_OR_EMPTY_MESSAGE = "Birth day cannot be null or empty.";
    public static final String CLIENTS_NOT_FOUND_MESSAGE = "No clients found.";
}
