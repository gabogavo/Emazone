package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.adapter;

import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.CategoryNotFoundException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import com.bootcampPragma.EmazonTest01.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException("Category with name " + category.getName() + " already exists.");
        }
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public Category getCategory(Long id) {
        // Buscar la entidad en el repositorio por ID
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found."));

        // Convertir la entidad a modelo de dominio
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size, boolean ascending) {
        Pageable pagination = PageRequest.of(page, size);
        List<CategoryEntity> categoryEntities;

        if (ascending) {
            categoryEntities = categoryRepository.findAllByOrderByNameAsc(pagination).getContent();
        } else {
            categoryEntities = categoryRepository.findAllByOrderByNameDesc(pagination).getContent();
        }

        return categoryEntityMapper.toModelList(categoryEntities);
    }

    @Override
    public Category updateCategory(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + category.getId() + " not found.");
        }
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);
        return categoryEntityMapper.toModel(categoryRepository.save(categoryEntity));
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + id + " not found.");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean categoryExistsByName(String name) {
        return false;
    }

}