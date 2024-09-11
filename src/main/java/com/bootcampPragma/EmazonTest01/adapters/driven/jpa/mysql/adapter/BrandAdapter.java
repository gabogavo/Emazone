package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.adapter;

import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.BrandAlreadyExistsException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.BrandNotFoundException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import com.bootcampPragma.EmazonTest01.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    private String name;

    @Override
    public void saveBrand(Brand brand){
        if (brandRepository.findByName(brand.getName()).isPresent()){
            throw new BrandAlreadyExistsException("Brand with name " + brand.getName() + " already exists.");
        }
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public Brand getBrand(Long id) {
        BrandEntity brandEntity = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Brand with id " + id + " not found."));

        return brandEntityMapper.toModel(brandEntity);
    }

    @Override
    public List<Brand> getAllBrands(Integer page, Integer size, boolean ascending) {
        Pageable pagination = PageRequest.of(page, size);
        List<BrandEntity> brandEntities;

        if (ascending) {
            brandEntities = brandRepository.findAllByOrderByNameAsc(pagination).getContent();
        } else {
            brandEntities = brandRepository.findAllByOrderByNameDesc(pagination).getContent();
        }
        return brandEntityMapper.toModelList(brandEntities);
    }

    @Override
    public Brand updateBrand(Brand brand){
        if (brandRepository.findById(brand.getId()).isEmpty()) {
            throw new BrandNotFoundException("Brand with id " + brand.getId() + " not found.");
        }
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        return brandEntityMapper.toModel(brandRepository.save(brandEntity));
    }

    @Override
    public void deleteBrand(Long id){
        if (brandRepository.findById(id).isEmpty()){
            throw new BrandNotFoundException("Brand with id " + id + " not found.");
        }
        brandRepository.deleteById(id);
    }

    @Override
    public boolean brandExistsByName(String name) { return  false; }
}
