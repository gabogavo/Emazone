package com.bootcampPragma.EmazonTest01.domain.exception;

public class FieldTooLongException extends RuntimeException {
    public FieldTooLongException(String fieldName, int maxLength) {
        super(String.format("The field '%s' exceeds the maximum length of %d characters.", fieldName, maxLength));
    }
}
