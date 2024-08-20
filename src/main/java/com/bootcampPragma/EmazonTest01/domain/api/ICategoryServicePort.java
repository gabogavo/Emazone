package com.bootcampPragma.EmazonTest01.domain.api;

import com.bootcampPragma.EmazonTest01.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    Category saveCategory(Category category);

    List<Category> getAllCategory();

    Category getCategory(Long id);

    void updateCategory(Category category);

    void deleteCategory(Long id);

}
