package com.bootcampPragma.EmazonTest01.domain.api.usecase;

import com.bootcampPragma.EmazonTest01.domain.model.Category;
import com.bootcampPragma.EmazonTest01.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.EmazonTest01.domain.exception.CategoryAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_CategoryExists_ThrowsException() {
        // Arrange
        Category category = new Category(1L, "CategoryName", "Description");
        when(categoryPersistencePort.categoryExistsByName(anyString())).thenReturn(true);

        // Act & Assert
        CategoryAlreadyExistsException thrown = assertThrows(
                CategoryAlreadyExistsException.class,
                () -> categoryUseCase.saveCategory(category),
                "Expected saveCategory to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Category with name 'CategoryName' already exists."));
        verify(categoryPersistencePort, never()).saveCategory(any());
    }

    @Test
    void saveCategory_CategoryDoesNotExist_Success() {
        // Arrange
        Category category = new Category(1L, "NewCategory", "Description");
        when(categoryPersistencePort.categoryExistsByName(anyString())).thenReturn(false);

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(categoryPersistencePort).saveCategory(category);
    }

    @Test
    void getAllCategories_ReturnsList() {
        // Arrange
        Category category = new Category(1L, "CategoryName", "Description");
        when(categoryPersistencePort.getAllCategories(anyInt(), anyInt(),anyBoolean())).thenReturn(Collections.singletonList(category));

        // Act
        List<Category> result = categoryUseCase.getAllCategories(0, 10, true);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(category, result.get(0));
    }

    @Test
    void getCategory_ReturnsCategory() {
        // Arrange
        Category category = new Category(1L, "CategoryName", "Description");
        when(categoryPersistencePort.getCategory(anyLong())).thenReturn(category);

        // Act
        Category result = categoryUseCase.getCategory(1L);

        // Assert
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void updateCategory_Success() {
        // Arrange
        Category category = new Category(1L, "UpdatedCategory", "UpdatedDescription");

        // Act
        Category result = categoryUseCase.updateCategory(category);

        // Assert
        verify(categoryPersistencePort).updateCategory(category);
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void deleteCategory_Success() {
        // Arrange
        doNothing().when(categoryPersistencePort).deleteCategory(anyLong());

        // Act & Assert
        assertDoesNotThrow(() -> categoryUseCase.deleteCategory(1L));
        verify(categoryPersistencePort).deleteCategory(1L);
    }


}