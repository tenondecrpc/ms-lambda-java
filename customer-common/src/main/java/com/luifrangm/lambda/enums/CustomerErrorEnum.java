package com.luifrangm.lambda.enums;

import lombok.Getter;

import java.util.Arrays;

import static com.luifrangm.lambda.constants.CustomerConstants.*;

@Getter
public enum CustomerErrorEnum {

    UNEXPECTED_EXCEPTION(
        UNEXPECTED_ERROR,INTERNAL_SERVER_ERROR),
    CUSTOMER_NOT_EXIST_EXCEPTION(
        CUSTOMER_NOT_EXIST_ERROR,NOT_FOUND),
    CREATE_CUSTOMER_EXCEPTION(
        CREATE_CUSTOMER_ERROR,INTERNAL_SERVER_ERROR),
    CUSTOMER_ID_EXCEPTION(
        CUSTOMER_ID_WRONG_ERROR,BAD_REQUEST),
    VALUE_NULL_EXCEPTION(
        VALUE_NULL_ERROR,BAD_REQUEST),
    VALUE_EMPTY_EXCEPTION(
        VALUE_EMPTY_ERROR,BAD_REQUEST),
    VALUE_BLANK_EXCEPTION(
        VALUE_BLANK_ERROR,BAD_REQUEST),
    INVALID_EMAIL_EXCEPTION(
        INVALID_EMAIL_ERROR,BAD_REQUEST);

    private static final String SPACE = " - ";
    private static final String SEPARATOR = ": ";

    private final String statusDescription;
    private final String cause;

    CustomerErrorEnum(String statusDescription, String cause) {
        this.statusDescription = statusDescription;
        this.cause = cause;
    }

    public static CustomerErrorEnum getCustomerError(final String errorDescription) {
        return
            Arrays.stream(values()).filter(item->
                item.getStatusDescription().equals(errorDescription))
                .findFirst()
                .orElse(UNEXPECTED_EXCEPTION);
    }

    @Override
    public String toString() {
        return
            new StringBuilder(this.cause)
                .append(SPACE)
                .append(this.name())
                .append(SEPARATOR)
                .append(this.statusDescription)
                .toString();
    }

}
