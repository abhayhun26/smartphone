package com.example.smartphone.dto.ProductDTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String brand;
    private String model;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;
    private Boolean active;
    private LocalDateTime createdAt;

}
