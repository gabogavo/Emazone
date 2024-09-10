package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception;

public class BrandAlreadyExistsException extends RuntimeException{

    public BrandAlreadyExistsException(String message)  {
        super(message);
    }
}
