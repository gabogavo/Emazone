package com.bootcampPragma.EmazonTest01.domain.api.usecase;

import com.bootcampPragma.EmazonTest01.domain.exception.BrandAlreadyExistsException;
import com.bootcampPragma.EmazonTest01.domain.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import com.bootcampPragma.EmazonTest01.domain.spi.IBrandPersistencePort;
import com.bootcampPragma.EmazonTest01.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_BrandExists_ThrowsException() {
        // Arrange
        Brand brand = new Brand(1L, "BrandName", "Description");
        when(brandPersistencePort.brandExistsByName(anyString())).thenReturn(true);

        // Act & Assert
        BrandAlreadyExistsException thrown = assertThrows(
                BrandAlreadyExistsException.class,
                () -> brandUseCase.saveBrand(brand),
                "Expected saveBrand to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Brand with name 'BrandName' already exists."));
        verify(brandPersistencePort, never()).saveBrand(any());
    }

    @Test
    void saveBrand_BrandDoesNotExist_Success() {
        // Arrange
        Brand brand = new Brand(1L, "NewBrand", "Description");
        when(brandPersistencePort.brandExistsByName(anyString())).thenReturn(false);

        // Act
        brandUseCase.saveBrand(brand);

        // Assert
        verify(brandPersistencePort).saveBrand(brand);
    }

    @Test
    void getAllBrands_ReturnsList() {
        // Arrange
        Brand brand = new Brand(1L, "BrandName", "Description");
        when(brandPersistencePort.getAllBrands(anyInt(), anyInt(), anyBoolean())).thenReturn(Collections.singletonList(brand));

        // Act
        List<Brand> result = brandUseCase.getAllBrands(0, 10, true);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(brand, result.get(0));
    }

    @Test
    void getBrand_ReturnsCategory() {
        // Arrange
        Brand brand = new Brand(1L, "CategoryName", "Description");
        when(brandPersistencePort.getBrand(anyLong())).thenReturn(brand);

        // Act
        Brand result = brandUseCase.getBrand(1L);

        // Assert
        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    void updateBrand_Success() {
        // Arrange
        Brand brand = new Brand(1L, "UpdatedBrand", "UpdatedDescription");

        // Act
        Brand result = brandUseCase.updateBrand(brand);

        // Assert
        verify(brandPersistencePort).updateBrand(brand);
        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    void deleteBrand_Success() {
        // Arrange
        doNothing().when(brandPersistencePort).deleteBrand(anyLong());

        // Act & Assert
        assertDoesNotThrow(() -> brandUseCase.deleteBrand(1L));
        verify(brandPersistencePort).deleteBrand(1L);
    }

}