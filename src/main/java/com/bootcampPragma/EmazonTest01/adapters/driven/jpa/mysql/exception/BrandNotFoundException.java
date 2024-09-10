package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception;

public class BrandNotFoundException extends RuntimeException{

    public BrandNotFoundException(String message) {
        super(message);
    }
}
