package com.bootcampPragma.EmazonTest01.domain.exception;

import com.bootcampPragma.EmazonTest01.domain.util.DomainConstants;

public class BrandAlreadyExistsException extends RuntimeException{
    public BrandAlreadyExistsException(String name){
        super(String.format(DomainConstants.BRAND_ALREADY_EXISTS_MESSAGE, name));
    }
}
