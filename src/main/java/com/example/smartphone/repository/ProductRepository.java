package com.example.smartphone.repository;

import com.example.smartphone.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    List<Products> findByNameContainingIgnoreCase(String filter);

    List<Products> findByBrandContainingIgnoreCase(String filter);

    List<Products> findByModelContainingIgnoreCase(String filter);
}
