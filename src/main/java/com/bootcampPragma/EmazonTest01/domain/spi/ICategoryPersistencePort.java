package com.bootcampPragma.EmazonTest01.domain.spi;

import com.bootcampPragma.EmazonTest01.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    void saveCategory();
}
