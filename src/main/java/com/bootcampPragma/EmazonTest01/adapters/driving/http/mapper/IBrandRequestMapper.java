package com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.AddBrandRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.UpdateBrandRequest;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {

    @Mapping(target = "id", ignore = true)
    Brand addRequestToBrand(AddBrandRequest addBrandRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    Brand updateRequestToBrand(UpdateBrandRequest updateBrandRequest);
}
