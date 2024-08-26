package com.bootcampPragma.EmazonTest01.domain.model;

import com.bootcampPragma.EmazonTest01.domain.exception.EmptyFieldException;
import com.bootcampPragma.EmazonTest01.domain.exception.FieldTooLongException;
import static java.util.Objects.requireNonNull;
import com.bootcampPragma.EmazonTest01.domain.util.DomainConstants;
import lombok.Getter;


@Getter
public class Category {
    private final Long id;
    private final String name;
    private final String description;

    public Category(Long id, String name, String description) {
        if (name == null) {
            throw new NullPointerException(DomainConstants.FIELD_NAME_NULL_MESSAGE);
        }
        if (description == null) {
            throw new NullPointerException(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        }
        if (name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if (description.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if (name.length() > DomainConstants.MAX_NAME_LENGTH) {
            throw new FieldTooLongException(DomainConstants.Field.NAME.toString(), DomainConstants.MAX_NAME_LENGTH);
        }
        if (description.length() > DomainConstants.MAX_DESCRIPTION_LENGTH) {
            throw new FieldTooLongException(DomainConstants.Field.DESCRIPTION.toString(), DomainConstants.MAX_DESCRIPTION_LENGTH);
        }

        this.id = id;
        this.name = requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}