package com.bootcampPragma.EmazonTest01.domain.model;

import com.bootcampPragma.EmazonTest01.domain.exception.EmptyFieldException;
import com.bootcampPragma.EmazonTest01.domain.exception.FieldTooLongException;
import com.bootcampPragma.EmazonTest01.domain.util.DomainConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    public void testConstructor_validInput_shouldNotThrowException() {
        assertDoesNotThrow(() -> new Brand(1L, "Valid Name", "Valid Description"));
    }

    @Test
    public void testConstructor_nameNull_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Brand(1L, null, "Valid Description"));
    }

    @Test
    public void testConstructor_descriptionNull_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Brand(1L, "Valid Name", null));
    }

    @Test
    public void testConstructor_nameEmpty_shouldThrowEmptyFieldException() {
        assertThrows(EmptyFieldException.class, () -> new Brand(1L, "", "Valid Description"));
    }

    @Test
    public void testConstructor_descriptionEmpty_shouldThrowEmptyFieldException() {
        assertThrows(EmptyFieldException.class, () -> new Brand(1L, "Valid Name", ""));
    }

    @Test
    public void testConstructor_nameTooLong_shouldThrowFieldTooLongException() {
        String longName = "A".repeat(DomainConstants.MAX_NAME_LENGTH + 1);
        assertThrows(FieldTooLongException.class, () -> new Brand(1L, longName, "Valid Description"));
    }

    @Test
    public void testConstructor_descriptionTooLong_shouldThrowFieldTooLongException() {
        String longDescription = "A".repeat(DomainConstants.MAX_DESCRIPTION_LENGTH_BRAND + 1);
        assertThrows(FieldTooLongException.class, () -> new Brand(1L, "Valid Name", longDescription));
    }

    @Test
    public void testGetters() {
        Brand brand = new Brand(1L, "Brand Name", "Brand Description");
        assertEquals(1L, brand.getId());
        assertEquals("Brand Name", brand.getName());
        assertEquals("Brand Description", brand.getDescription());
    }
}