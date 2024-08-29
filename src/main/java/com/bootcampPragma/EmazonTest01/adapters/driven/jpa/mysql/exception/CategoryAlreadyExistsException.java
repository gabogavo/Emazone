package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}