package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.mapper;

import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Brand toModel(BrandEntity brandEntity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    BrandEntity toEntity(Brand brand);

    List<Brand> toModelList(List<BrandEntity> brandEntities);

    List<BrandEntity> toEntityList(List<Brand> brands);
}
