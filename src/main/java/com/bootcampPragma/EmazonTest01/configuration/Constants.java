package com.bootcampPragma.EmazonTest01.configuration;

public class Constants {

    // Constructor privado para evitar instanciación
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Constantes para mensajes de excepciones
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "The field '%s' cannot be empty.";
    public static final String FIELD_TOO_LONG_EXCEPTION_MESSAGE = "The field '%s' exceeds the allowed length.";
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE = "Category already exists.";
    public static final String CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE = "Category not found.";
}