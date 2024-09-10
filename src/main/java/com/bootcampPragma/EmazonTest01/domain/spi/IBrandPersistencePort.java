package com.bootcampPragma.EmazonTest01.domain.spi;

import com.bootcampPragma.EmazonTest01.domain.model.Brand;

import java.util.List;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Brand getBrand(Long id);

    List<Brand> getAllBrands(Integer page, Integer size);

    boolean brandExistsByName(String name);

    Brand updateBrand(Brand brand);

    void deleteBrand(Long id);
}
