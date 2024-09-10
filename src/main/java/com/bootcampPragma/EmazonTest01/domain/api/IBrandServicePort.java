package com.bootcampPragma.EmazonTest01.domain.api;

import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import java.util.List;

public interface IBrandServicePort {

    void saveBrand(Brand brand);

    Brand getBrand(Long id);

    List<Brand> getAllBrands(Integer page, Integer size);

    Brand updateBrand(Brand brand);

    void deleteBrand(Long id);
}
