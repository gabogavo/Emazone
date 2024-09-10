package com.bootcampPragma.EmazonTest01.domain.api.usecase;

import com.bootcampPragma.EmazonTest01.domain.api.ICategoryServicePort;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import com.bootcampPragma.EmazonTest01.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.EmazonTest01.domain.exception.CategoryAlreadyExistsException; // Excepción personalizada para categoría existente

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistencePort.categoryExistsByName(category.getName())) {
            throw new CategoryAlreadyExistsException("Category with name '" + category.getName() + "' already exists.");
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size, boolean ascending) {
        return categoryPersistencePort.getAllCategories(page, size, ascending);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }

    @Override
    public Category updateCategory(Category category) {
        categoryPersistencePort.updateCategory(category);
        return category;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryPersistencePort.deleteCategory(id);
    }

}