package com.example.smartphone.dto.ProductDTO;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    private String name;
    private String brand;
    private String model;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;
    private Boolean active;

}
