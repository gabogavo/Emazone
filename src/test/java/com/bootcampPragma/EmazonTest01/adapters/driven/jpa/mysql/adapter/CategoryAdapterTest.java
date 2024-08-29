package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.adapter;

import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.CategoryNotFoundException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_WhenCategoryExists_ThrowsCategoryAlreadyExistsException() {
        // Arrange
        Category category = new Category(1L, "Electronics name", "Electronics description");
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(new CategoryEntity(1L, "Electronics name", "Electronics description")));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryAdapter.saveCategory(category));
        verify(categoryRepository, never()).save(any(CategoryEntity.class));
    }

    @Test
    void saveCategory_WhenCategoryDoesNotExist_SavesCategory() {
        // Arrange
        Category category = new Category(1L, "Electronics name", "Electronics description");
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(new CategoryEntity(1L, "Electronics name", "Electronics description"));

        // Act
        categoryAdapter.saveCategory(category);

        // Assert
        verify(categoryRepository).save(any(CategoryEntity.class));
    }

    @Test
    void getCategory_CategoryExists_ReturnsCategory() {
        // Arrange
        Long categoryId = 1L;
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Original Name", "Original Description");
        Category category = new Category(1L, "Original Name", "Original Description");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toModel(categoryEntity)).thenReturn(category);

        // Act
        Category result = categoryAdapter.getCategory(categoryId);

        // Assert
        assertNotNull(result, "El resultado no debe ser null");
        assertEquals(category, result, "El objeto Category devuelto no es el esperado");
    }

    @Test
    void getCategory_CategoryDoesNotExist_ThrowsCategoryNotFoundException() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryAdapter.getCategory(categoryId));
    }


    @Test
    void getAllCategories_ReturnsListOfCategories() {
        // Arrange
        List<CategoryEntity> categoryEntities = List.of(new CategoryEntity(1L, "Electronics name", "Electronics description"));
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(categoryEntities));
        when(categoryEntityMapper.toModelList(categoryEntities)).thenReturn(List.of(new Category(1L, "Electronics name", "Electronics description")));

        // Act
        List<Category> categories = categoryAdapter.getAllCategories(0, 10);

        // Assert
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
    }

    @Test
    void updateCategory_WhenCategoryExists_UpdatesCategory() {
        // Datos de prueba
        Category categoryToUpdate = new Category(1L, "Updated Name", "Updated Description");
        CategoryEntity existingEntity = new CategoryEntity(1L, "Original Name", "Original Description");
        CategoryEntity updatedEntity = new CategoryEntity(1L, "Updated Name", "Updated Description");

        // Configuración del mock para devolver la entidad existente
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        // Configuración del mock para convertir el modelo a entidad
        when(categoryEntityMapper.toEntity(categoryToUpdate)).thenReturn(updatedEntity);

        // Configuración del mock para guardar la entidad y devolver la entidad actualizada
        when(categoryRepository.save(updatedEntity)).thenReturn(updatedEntity);

        // Configuración del mock para convertir la entidad guardada en el modelo de dominio
        when(categoryEntityMapper.toModel(updatedEntity)).thenReturn(categoryToUpdate);

        // Llamada al método a probar
        Category updatedCategory = categoryAdapter.updateCategory(categoryToUpdate);

        // Verificación
        assertNotNull(updatedCategory, "El valor de updatedCategory no debe ser null");
        assertEquals("Updated Name", updatedCategory.getName(), "El nombre no se ha actualizado correctamente");
        assertEquals("Updated Description", updatedCategory.getDescription(), "La descripción no se ha actualizado correctamente");
    }

    @Test
    void updateCategory_WhenCategoryDoesNotExist_ThrowsCategoryNotFoundException() {
        // Arrange
        Category category = new Category(1L, "Electronics name", "Electronics description");
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryAdapter.updateCategory(category));
    }

    @Test
    void deleteCategory_WhenCategoryExists_DeletesCategory() {
        // Arrange
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics name", "Electronics description");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));

        // Act
        categoryAdapter.deleteCategory(1L);

        // Assert
        verify(categoryRepository).deleteById(1L);
    }

    @Test
    void deleteCategory_WhenCategoryDoesNotExist_ThrowsCategoryNotFoundException() {
        // Arrange
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryAdapter.deleteCategory(1L));
    }
}