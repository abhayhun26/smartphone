package com.example.smartphone.service.impl;

import com.example.smartphone.dto.ProductDTO.ProductRequest;
import com.example.smartphone.entity.Products;
import com.example.smartphone.exceptions.ResourceNotFoundExceptioon;
import com.example.smartphone.repository.ProductRepository;
import com.example.smartphone.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepo;

    @Override
    public Products createProduct(ProductRequest request) {
        log.info("Product Request : {}", request);
        Products product = Products.builder()
                .name(request.getName())
                .model(request.getModel())
                .brand(request.getBrand())
                .price(request.getPrice())
                .description(request.getDescription())
                .stockQuantity(request.getStockQuantity())
                .active(true)
                .build();
        log.info("Product Bean: ",product.getActive());
        return productRepo.save(product);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Products getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExceptioon("Product not found with such ID: "+ id));
//        return productRepo.findById(id).get();
    }

    @Override
    public Products updateProduct(Long id, ProductRequest request) {
        Products product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptioon("Product not found"));

        log.info("Product Exist: {}", product);
        // Update existing fields
        product.setName(request.getName());
        product.setModel(request.getModel());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStockQuantity(request.getStockQuantity());
        product.setActive(request.getActive());

        return productRepo.save(product);
    }

    @Override
    public Products deleteProduct(Long id) {
        Products product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExceptioon("Product not Found"));
        productRepo.deleteById(id);
        return product;
    }

}
