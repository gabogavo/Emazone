package com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.AddCategoryRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.UpdateCategoryRequest;
import com.bootcampPragma.EmazonTest01.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryRequestMapper {

    @Mapping(target = "id", ignore = true) // ID se ignora al crear una nueva categoría
    Category addRequestToCategory(AddCategoryRequest addCategoryRequest);

    @Mapping(target = "id", source = "id") // Mapeo del ID desde el DTO
    @Mapping(target = "name", source = "name") // Mapeo del nombre desde el DTO
    @Mapping(target = "description", source = "description") // Mapeo de la descripción desde el DTO
    Category updateRequestToCategory(UpdateCategoryRequest updateCategoryRequest);
}
