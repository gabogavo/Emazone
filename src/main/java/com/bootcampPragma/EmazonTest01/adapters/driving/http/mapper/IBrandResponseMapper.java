package com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.response.BrandResponse;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {

    BrandResponse toBrandResponse(Brand brand);

    List<BrandResponse> toBrandResponseList(List<Brand> brands);
}
