package com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.adapter;

import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.BrandAlreadyExistsException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.exception.BrandNotFoundException;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.bootcampPragma.EmazonTest01.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.bootcampPragma.EmazonTest01.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class BrandAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrand_WhenCategoryExists_ThrowsBrandAlreadyExistsException() {
        // Arrange
        Brand brand = new Brand(1L, "Electronics name", "Electronics description");
        when(brandRepository.findByName(brand.getName())).thenReturn(Optional.of(new BrandEntity(1L, "Electronics name", "Electronics description")));

        // Act & Assert
        assertThrows(BrandAlreadyExistsException.class, () -> brandAdapter.saveBrand(brand));
        verify(brandRepository, never()).save(any(BrandEntity.class));
    }

    @Test
    void saveBrand_WhenCategoryDoesNotExist_SavesBrand() {
        // Arrange
        Brand brand = new Brand(1L, "Electronics name", "Electronics description");
        when(brandRepository.findByName(brand.getName())).thenReturn(Optional.empty());
        when(brandEntityMapper.toEntity(brand)).thenReturn(new BrandEntity(1L, "Electronics name", "Electronics description"));

        // Act
        brandAdapter.saveBrand(brand);

        // Assert
        verify(brandRepository).save(any(BrandEntity.class));
    }

    @Test
    void getBrand_BrandExists_ReturnsBrand() {
        // Arrange
        Long brandId = 1L;
        BrandEntity brandEntity = new BrandEntity(1L, "Original Name", "Original Description");
        Brand brand = new Brand(1L, "Original Name", "Original Description");
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brandEntity));
        when(brandEntityMapper.toModel(brandEntity)).thenReturn(brand);

        // Act
        Brand result = brandAdapter.getBrand(brandId);

        // Assert
        assertNotNull(result, "El resultado no debe ser null");
        assertEquals(brand, result, "El objeto Brand devuelto no es el esperado");
    }

    @Test
    void getBrand_BrandDoesNotExist_ThrowsBrandNotFoundException() {
        // Arrange
        Long brandId = 1L;
        when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BrandNotFoundException.class, () -> brandAdapter.getBrand(brandId));
    }


    @Test
    void getAllBrands_ReturnsListOfBrands() {
        // Arrange
        List<BrandEntity> brandEntities = List.of(new BrandEntity(1L, "Electronics name", "Electronics description"));
        when(brandRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandEntities));
        when(brandEntityMapper.toModelList(brandEntities)).thenReturn(List.of(new Brand(1L, "Electronics name", "Electronics description")));

        // Act
        List<Brand> brands = brandAdapter.getAllBrands(0, 10);

        // Assert
        assertNotNull(brands);
        assertFalse(brands.isEmpty());
    }

    @Test
    void updateBrand_WhenBrandExists_UpdatesBrand() {
        // Datos de prueba
        Brand brandToUpdate = new Brand(1L, "Updated Name", "Updated Description");
        BrandEntity existingEntity = new BrandEntity(1L, "Original Name", "Original Description");
        BrandEntity updatedEntity = new BrandEntity(1L, "Updated Name", "Updated Description");

        // Configuración del mock para devolver la entidad existente
        when(brandRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        // Configuración del mock para convertir el modelo a entidad
        when(brandEntityMapper.toEntity(brandToUpdate)).thenReturn(updatedEntity);

        // Configuración del mock para guardar la entidad y devolver la entidad actualizada
        when(brandRepository.save(updatedEntity)).thenReturn(updatedEntity);

        // Configuración del mock para convertir la entidad guardada en el modelo de dominio
        when(brandEntityMapper.toModel(updatedEntity)).thenReturn(brandToUpdate);

        // Llamada al método a probar
        Brand updateBrand = brandAdapter.updateBrand(brandToUpdate);

        // Verificación
        assertNotNull(updateBrand, "El valor de updatedBrand no debe ser null");
        assertEquals("Updated Name", updateBrand.getName(), "El nombre no se ha actualizado correctamente");
        assertEquals("Updated Description", updateBrand.getDescription(), "La descripción no se ha actualizado correctamente");
    }

    @Test
    void updateBrand_WhenBrandDoesNotExist_ThrowsBrandNotFoundException() {
        // Arrange
        Brand brand = new Brand(1L, "Electronics name", "Electronics description");
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BrandNotFoundException.class, () -> brandAdapter.updateBrand(brand));
    }

    @Test
    void deleteBrand_WhenBrandExists_DeletesBrand() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity(1L, "Electronics name", "Electronics description");
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brandEntity));

        // Act
        brandAdapter.deleteBrand(1L);

        // Assert
        verify(brandRepository).deleteById(1L);
    }

    @Test
    void deleteBrand_WhenBrandDoesNotExist_ThrowsBrandNotFoundException() {
        // Arrange
        when(brandRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BrandNotFoundException.class, () -> brandAdapter.deleteBrand(1L));
    }

}