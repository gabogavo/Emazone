package com.bootcampPragma.EmazonTest01.domain.exception;

import com.bootcampPragma.EmazonTest01.domain.util.DomainConstants;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String name) {
        super(String.format(DomainConstants.CATEGORY_ALREADY_EXISTS_MESSAGE, name));
    }
}

