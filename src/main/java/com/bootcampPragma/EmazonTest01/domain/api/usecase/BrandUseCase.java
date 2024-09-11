package com.bootcampPragma.EmazonTest01.domain.api.usecase;

import com.bootcampPragma.EmazonTest01.domain.api.IBrandServicePort;
import com.bootcampPragma.EmazonTest01.domain.exception.BrandAlreadyExistsException;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import com.bootcampPragma.EmazonTest01.domain.spi.IBrandPersistencePort;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort){
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand){
        if(brandPersistencePort.brandExistsByName(brand.getName())){
            throw new BrandAlreadyExistsException("Brand with name '" + brand.getName() + "' already exists.");
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Brand getBrand(Long id){
        return brandPersistencePort.getBrand(id);
    }

    @Override
    public List<Brand> getAllBrands(Integer page, Integer size, boolean ascending){
        return brandPersistencePort.getAllBrands(page, size, ascending);
    }

    @Override
    public Brand updateBrand(Brand brand){
        brandPersistencePort.updateBrand(brand);
        return brand;
    }

    @Override
    public void deleteBrand(Long id) {
        brandPersistencePort.deleteBrand(id);
    }
}
