package com.bootcampPragma.EmazonTest01.adapters.driving.http.controller;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.AddCategoryRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.response.CategoryResponse;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.bootcampPragma.EmazonTest01.domain.api.ICategoryServicePort;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryRestControllerAdapterTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCategory_Success() {
        // Arrange
        AddCategoryRequest request = new AddCategoryRequest("CategoryName", "Description");
        Category category = new Category(1L, "CategoryName", "Description");
        when(categoryRequestMapper.addRequestToCategory(any(AddCategoryRequest.class))).thenReturn(category);

        // Act
        ResponseEntity<Void> response = categoryRestControllerAdapter.addCategory(request);

        // Assert
        verify(categoryServicePort).saveCategory(category);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getCategory_Success() {
        // Arrange
        Category category = new Category(1L, "CategoryName", "Description");
        CategoryResponse categoryResponse = new CategoryResponse(1L, "CategoryName", "Description");
        when(categoryServicePort.getCategory(anyLong())).thenReturn(category);
        when(categoryResponseMapper.toCategoryResponse(any(Category.class))).thenReturn(categoryResponse);

        // Act
        ResponseEntity<CategoryResponse> response = categoryRestControllerAdapter.getCategory(1L);

        // Assert
        verify(categoryServicePort).getCategory(1L);
        verify(categoryResponseMapper).toCategoryResponse(category);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponse, response.getBody());
    }

    @Test
    void getAllCategories_Success() {
        // Arrange
        Category category = new Category(1L, "CategoryName", "Description");
        CategoryResponse categoryResponse = new CategoryResponse(1L, "CategoryName", "Description");

        // Mockear los métodos para que retornen los valores esperados
        when(categoryServicePort.getAllCategories(anyInt(), anyInt(), anyBoolean())).thenReturn(Collections.singletonList(category));
        when(categoryResponseMapper.toCategoryResponseList(anyList())).thenReturn(Collections.singletonList(categoryResponse));

        // Act
        ResponseEntity<List<CategoryResponse>> response = categoryRestControllerAdapter.getAllCategories(0, 10, true);

        // Assert
        verify(categoryServicePort).getAllCategories(0, 10, true);
        verify(categoryResponseMapper).toCategoryResponseList(Collections.singletonList(category));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(categoryResponse, response.getBody().get(0));
    }

    @Test
    void updateCategory_Success() {
        // Arrange
        UpdateCategoryRequest request = new UpdateCategoryRequest(1L, "UpdatedCategory", "UpdatedDescription");
        Category category = new Category(1L, "UpdatedCategory", "UpdatedDescription");
        CategoryResponse categoryResponse = new CategoryResponse(1L, "UpdatedCategory", "UpdatedDescription");
        when(categoryRequestMapper.updateRequestToCategory(any(UpdateCategoryRequest.class))).thenReturn(category);
        when(categoryServicePort.updateCategory(any(Category.class))).thenReturn(category);
        when(categoryResponseMapper.toCategoryResponse(any(Category.class))).thenReturn(categoryResponse);

        // Act
        ResponseEntity<CategoryResponse> response = categoryRestControllerAdapter.updateCategory(request);

        // Assert
        verify(categoryServicePort).updateCategory(category);
        verify(categoryResponseMapper).toCategoryResponse(category);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponse, response.getBody());
    }

    @Test
    void deleteCategory_Success() {
        // Arrange
        doNothing().when(categoryServicePort).deleteCategory(anyLong());

        // Act
        ResponseEntity<Void> response = categoryRestControllerAdapter.deleteCategory(1L);

        // Assert
        verify(categoryServicePort).deleteCategory(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}