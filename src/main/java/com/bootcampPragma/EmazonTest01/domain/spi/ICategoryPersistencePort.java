package com.bootcampPragma.EmazonTest01.domain.spi;

import com.bootcampPragma.EmazonTest01.domain.model.Category;
import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    Category getCategory(Long id);

    Category getCategory();

    List<Category> getAllCategories(Integer page, Integer size, boolean ascending);

    Category updateCategory(Category category);

    void deleteCategory(Long id);
    boolean categoryExistsByName(String name);
}