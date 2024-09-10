package com.bootcampPragma.EmazonTest01.adapters.driving.http.controller;

import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.AddBrandRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.request.UpdateBrandRequest;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.dto.response.BrandResponse;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.IBrandRequestMapper;
import com.bootcampPragma.EmazonTest01.adapters.driving.http.mapper.IBrandResponseMapper;
import com.bootcampPragma.EmazonTest01.domain.api.IBrandServicePort;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BrandRestControllerAdapterTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandRestControllerAdapter brandRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBrand_Success() {
        // Arrange
        AddBrandRequest request = new AddBrandRequest("BrandName", "Description");
        Brand brand = new Brand(1L, "BrandName", "Description");
        when(brandRequestMapper.addRequestToBrand(any(AddBrandRequest.class))).thenReturn(brand);

        // Act
        ResponseEntity<Void> response = brandRestControllerAdapter.addBrand(request);

        // Assert
        verify(brandServicePort).saveBrand(brand);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getCategory_Success() {
        // Arrange
        Brand brand = new Brand(1L, "BrandName", "Description");
        BrandResponse brandResponse = new BrandResponse(1L, "BrandName", "Description");
        when(brandServicePort.getBrand(anyLong())).thenReturn(brand);
        when(brandResponseMapper.toBrandResponse(any(Brand.class))).thenReturn(brandResponse);

        // Act
        ResponseEntity<BrandResponse> response = brandRestControllerAdapter.getBrand(1L);

        // Assert
        verify(brandServicePort).getBrand(1L);
        verify(brandResponseMapper).toBrandResponse(brand);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandResponse, response.getBody());
    }

    @Test
    void getAllBrands_Success() {
        // Arrange
        Brand brand = new Brand(1L, "BrandName", "Description");
        BrandResponse brandResponse = new BrandResponse(1L, "BrandName", "Description");
        when(brandServicePort.getAllBrands(anyInt(), anyInt())).thenReturn(Collections.singletonList(brand));
        when(brandResponseMapper.toBrandResponseList(anyList())).thenReturn(Collections.singletonList(brandResponse));

        // Act
        ResponseEntity<List<BrandResponse>> response = brandRestControllerAdapter.getAllBrands(0, 10);

        // Assert
        verify(brandServicePort).getAllBrands(0, 10);
        verify(brandResponseMapper).toBrandResponseList(Collections.singletonList(brand));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(brandResponse, response.getBody().get(0));
    }

    @Test
    void updateBrand_Success() {
        // Arrange
        UpdateBrandRequest request = new UpdateBrandRequest(1L, "UpdatedBrand", "UpdatedDescription");
        Brand brand = new Brand(1L, "UpdatedBrand", "UpdatedDescription");
        BrandResponse brandResponse = new BrandResponse(1L, "UpdatedBrand", "UpdatedDescription");
        when(brandRequestMapper.updateRequestToBrand(any(UpdateBrandRequest.class))).thenReturn(brand);
        when(brandServicePort.updateBrand(any(Brand.class))).thenReturn(brand);
        when(brandResponseMapper.toBrandResponse(any(Brand.class))).thenReturn(brandResponse);

        // Act
        ResponseEntity<BrandResponse> response = brandRestControllerAdapter.updateBrand(request);

        // Assert
        verify(brandServicePort).updateBrand(brand);
        verify(brandResponseMapper).toBrandResponse(brand);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brandResponse, response.getBody());
    }

    @Test
    void deleteBrand_Success() {
        // Arrange
        doNothing().when(brandServicePort).deleteBrand(anyLong());

        // Act
        ResponseEntity<Void> response = brandRestControllerAdapter.deleteBrand(1L);

        // Assert
        verify(brandServicePort).deleteBrand(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}