package com.bootcampPragma.EmazonTest01.domain.api;

import com.bootcampPragma.EmazonTest01.domain.model.Category;
import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    Category getCategory(Long id);

    List<Category> getAllCategories(Integer page, Integer size, boolean ascending);

    Category updateCategory(Category category);

    void deleteCategory(Long id);
}
