package com.example.smartphone.service;

import com.example.smartphone.dto.ProductDTO.ProductRequest;
import com.example.smartphone.entity.Products;

import java.util.List;

public interface ProductService {

    public Products createProduct(ProductRequest request);
    public List<Products> getAllProducts();
    public Products getProductById(Long Id);
    public Products updateProduct(Long id, ProductRequest request);
    public Products deleteProduct(Long id);

    List<Products> getProductByfilter(String filter);
}
