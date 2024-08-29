package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
