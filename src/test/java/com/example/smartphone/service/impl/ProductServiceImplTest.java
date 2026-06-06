package com.example.smartphone.service.impl;

import com.example.smartphone.dto.ProductDTO.ProductRequest;
import com.example.smartphone.entity.Products;
import com.example.smartphone.exceptions.ResourceNotFoundExceptioon;
import com.example.smartphone.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductRequest productRequest;
    private Products product;

    @BeforeEach
    void setup(){
        productRequest = ProductRequest.builder()
                .name("iPhone 15")
                .model("A3090")
                .brand("Apple")
                .price(BigDecimal.valueOf(79999))
                .description("Latest Apple smartphone")
                .stockQuantity(10)
                .active(true)
                .build();

        product = Products.builder()
                .id(1L)
                .name("iPhone 15")
                .model("A3090")
                .brand("Apple")
                .price(BigDecimal.valueOf(79999))
                .description("Latest Apple smartphone")
                .stockQuantity(10)
                .active(true)
                .build();
    }

    @Test
    @DisplayName("Should create a Product")
    void testCreateProduct(){
        when(productRepo.save(any(Products.class))).thenReturn(product);
        Products savedProduct = productService.createProduct(productRequest);
        assertNotNull(savedProduct);
        assertEquals("iPhone 15",savedProduct.getName());
        assertEquals("Apple",savedProduct.getBrand());
        verify(productRepo,times(1)).save(any(Products.class));
    }

    @Test
    @DisplayName("Should return all products")
    void testGetAllProducts() {
        List<Products> productList = List.of(product);
        when(productRepo.findAll()).thenReturn(productList);
        List<Products> result = productService.getAllProducts();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("iPhone 15", result.get(0).getName());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return product when valid ID is provided")
    void testGetProductById_Success() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        Products foundProduct = productService.getProductById(1L);
        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals("iPhone 15", foundProduct.getName());

        verify(productRepo, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when product ID not found")
    void testGetProductById_NotFound() {
        when(productRepo.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundExceptioon exception = assertThrows(
                ResourceNotFoundExceptioon.class,
                () -> productService.getProductById(1L)
        );
        assertEquals("Product not found with such ID: 1", exception.getMessage());
        verify(productRepo, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should update product successfully")
    void testUpdateProduct_Success() {
        ProductRequest updatedRequest = ProductRequest.builder()
                .name("Samsung S24")
                .model("SM-S921B")
                .brand("Samsung")
                .price(BigDecimal.valueOf(74999.0))
                .description("Flagship Samsung phone")
                .stockQuantity(15)
                .active(true)
                .build();
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        when(productRepo.save(any(Products.class))).thenReturn(product);
        Products updatedProduct = productService.updateProduct(1L, updatedRequest);
        assertNotNull(updatedProduct);
        assertEquals("Samsung S24", updatedProduct.getName());
        assertEquals("Samsung", updatedProduct.getBrand());
        assertEquals(BigDecimal.valueOf(74999.0), updatedProduct.getPrice());
        verify(productRepo, times(1)).findById(1L);
        verify(productRepo, times(1)).save(any(Products.class));
    }

    @Test
    @DisplayName("Should throw exception when updating non-existing product")
    void testUpdateProduct_NotFound() {
        when(productRepo.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.updateProduct(1L, productRequest)
        );

        assertEquals("Product not found", exception.getMessage());
        verify(productRepo, times(1)).findById(1L);
        verify(productRepo, never()).save(any(Products.class));
    }

    @Test
    @DisplayName("Should delete product successfully")
    void testDeleteProduct_Success() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepo).deleteById(1L);
        Products deletedProduct = productService.deleteProduct(1L);
        assertNotNull(deletedProduct);
        assertEquals(1L, deletedProduct.getId());
        assertEquals("iPhone 15", deletedProduct.getName());
        verify(productRepo, times(1)).findById(1L);
        verify(productRepo, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existing product")
    void testDeleteProduct_NotFound() {
        when(productRepo.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.deleteProduct(1L)
        );
        assertEquals("Product not Found", exception.getMessage());
        verify(productRepo, times(1)).findById(1L);
        verify(productRepo, never()).deleteById(anyLong());
    }

}
