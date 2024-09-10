package com.bootcampPragma.EmazonTest01.adapters.driving.http.controller;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.AddBrandRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.UpdateBrandRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.response.BrandResponse;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.IBrandRequestMapper;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.IBrandResponseMapper;
import com.bootcampPragma.EmazonTest01.domain.api.IBrandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final IBrandServicePort brandServicePort; //aca si o si debe ir private final o da error de NullPointerException
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addBrand(@RequestBody AddBrandRequest request) {
        brandServicePort.saveBrand(brandRequestMapper.addRequestToBrand(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrand(@PathVariable Long id) {
        return ResponseEntity.ok(brandResponseMapper.toBrandResponse(
                brandServicePort.getBrand(id)));
    }

    @GetMapping("/")
    public ResponseEntity<List<BrandResponse>> getAllBrands(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.ok(brandResponseMapper.toBrandResponseList(
                brandServicePort.getAllBrands(page, size)
        ));
    }

    @PutMapping("/")
    public ResponseEntity<BrandResponse> updateBrand(@RequestBody UpdateBrandRequest request){
        return ResponseEntity.ok(brandResponseMapper.toBrandResponse(
                brandServicePort.updateBrand(brandRequestMapper.updateRequestToBrand(request))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id){
        brandServicePort.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
