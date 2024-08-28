package com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.response.CategoryResponse;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    // Mapea un objeto Category a CategoryResponse
    CategoryResponse toCategoryResponse(Category category);

    // Mapea una lista de objetos Category a una lista de CategoryResponse
    List<CategoryResponse> toCategoryResponseList(List<Category> categories);
}
