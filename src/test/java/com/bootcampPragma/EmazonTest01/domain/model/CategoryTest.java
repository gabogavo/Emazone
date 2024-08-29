package com.bootcampPragma.EmazonTest01.domain.model;

import com.bootcampPragma.EmazonTest01.domain.exception.EmptyFieldException;
import com.bootcampPragma.EmazonTest01.domain.exception.FieldTooLongException;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import com.bootcampPragma.EmazonTest01.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    @Test
    public void testConstructor_validInput_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Category(1L, "Valid Name", "Valid Description"));
    }

    @Test
    public void testConstructor_nameNull_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(1L, null, "Valid Description"));
    }

    @Test
    public void testConstructor_descriptionNull_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(1L, "Valid Name", null));
    }

    @Test
    public void testConstructor_nameEmpty_shouldThrowEmptyFieldException() {
        assertThrows(EmptyFieldException.class, () -> new Category(1L, "", "Valid Description"));
    }

    @Test
    public void testConstructor_descriptionEmpty_shouldThrowEmptyFieldException() {
        assertThrows(EmptyFieldException.class, () -> new Category(1L, "Valid Name", ""));
    }

    @Test
    public void testConstructor_nameTooLong_shouldThrowFieldTooLongException() {
        String longName = "A".repeat(DomainConstants.MAX_NAME_LENGTH + 1);
        assertThrows(FieldTooLongException.class, () -> new Category(1L, longName, "Valid Description"));
    }

    @Test
    public void testConstructor_descriptionTooLong_shouldThrowFieldTooLongException() {
        String longDescription = "A".repeat(DomainConstants.MAX_DESCRIPTION_LENGTH + 1);
        assertThrows(FieldTooLongException.class, () -> new Category(1L, "Valid Name", longDescription));
    }

    @Test
    public void testGetters() {
        Category category = new Category(1L, "Category Name", "Category Description");
        assertEquals(1L, category.getId());
        assertEquals("Category Name", category.getName());
        assertEquals("Category Description", category.getDescription());
    }
}