package com.bootcampPragma.EmazonTest01.domain.api;

import com.bootcampPragma.EmazonTest01.domain.model.Category;
import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    Category getCategory(Long id);

    Category getCategory(String name);

    List<Category> getAllCategories(Integer page, Integer size);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}
