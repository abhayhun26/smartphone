package com.example.smartphone.controller;

import com.example.smartphone.dto.ProductDTO.ProductRequest;
import com.example.smartphone.dto.ProductDTO.ProductResponse;
import com.example.smartphone.entity.Products;
import com.example.smartphone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        Products product = service.createProduct(request);
        if (product != null) {
            ProductResponse response = ProductResponse.builder().id(product.getId())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .model(product.getModel())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .stockQuantity(product.getStockQuantity())
                    .active(product.getActive())
                    .createdAt(product.getCreatedAt())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getAllProducts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Products> allProducts = service.getAllProducts();
        if (!allProducts.isEmpty()) {
            List<ProductResponse> response = new ArrayList<>();
            for (Products product : allProducts) {
                response.add(ProductResponse.builder().id(product.getId())
                        .name(product.getName())
                        .brand(product.getBrand())
                        .model(product.getModel())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .stockQuantity(product.getStockQuantity())
                        .active(product.getActive())
                        .createdAt(product.getCreatedAt())
                        .build());
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getProduct/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        Products product = service.getProductById(id);
        if (product != null) {
            ProductResponse response = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .model(product.getModel())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .stockQuantity(product.getStockQuantity())
                    .active(product.getActive())
                    .createdAt(product.getCreatedAt())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getProductBy/{filter}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ProductResponse>> getProductByName(@PathVariable String filter) {
        List<Products> filterProducts = service.getProductByfilter(filter);
        List<ProductResponse> response = new ArrayList<>();
        for (Products product : filterProducts) {
            response.add(ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .model(product.getModel())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .stockQuantity(product.getStockQuantity())
                    .active(product.getActive())
                    .createdAt(product.getCreatedAt())
                    .build());
        }
        if (!response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        Products product = service.updateProduct(id, request);
        if (product != null) {
            ProductResponse response = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .model(product.getModel())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .stockQuantity(product.getStockQuantity())
                    .active(product.getActive())
                    .createdAt(product.getCreatedAt())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/removeProduct/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        Products product = service.deleteProduct(id);
        if (product != null) {
            ProductResponse response = ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .brand(product.getBrand())
                    .model(product.getModel())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .stockQuantity(product.getStockQuantity())
                    .active(product.getActive())
                    .createdAt(product.getCreatedAt())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/sortProducts")
    public ResponseEntity<List<ProductResponse>> sortByProduct(@RequestParam String sortBy) {
        List<Products> sortedProducts = service.sortByProduct(sortBy);
        if (!sortedProducts.isEmpty()) {
            List<ProductResponse> response = new ArrayList<>();
            for (Products product : sortedProducts) {
                response.add(ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .brand(product.getBrand())
                        .model(product.getModel())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .stockQuantity(product.getStockQuantity())
                        .active(product.getActive())
                        .createdAt(product.getCreatedAt())
                        .build());
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
