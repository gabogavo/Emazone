package com.bootcampPragma.EmazonTest01.configuration;

import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.bootcampPragma.EmazonTest01.domain.api.ICategoryServicePort;
import com.bootcampPragma.EmazonTest01.domain.api.usecase.CategoryUseCase;
import com.bootcampPragma.EmazonTest01.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}