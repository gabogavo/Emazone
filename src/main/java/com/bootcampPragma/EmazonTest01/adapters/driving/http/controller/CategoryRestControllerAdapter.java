package com.bootcampPragma.EmazonTest01.adapters.driving.http.controller;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.AddCategoryRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.response.CategoryResponse;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.bootcampPragma.EmazonTest01.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest request) {
        categoryServicePort.saveCategory(categoryRequestMapper.addRequestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(categoryServicePort.getCategory(id)));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size,
                                                                   @RequestParam(required = false, defaultValue = "true") boolean ascending) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponseList(categoryServicePort.getAllCategories(page, size, ascending)));
    }

    @PutMapping("/")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryResponseMapper.toCategoryResponse(
                categoryServicePort.updateCategory(categoryRequestMapper.updateRequestToCategory(request))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryServicePort.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
