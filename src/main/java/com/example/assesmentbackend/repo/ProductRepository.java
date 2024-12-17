package com.example.assesmentbackend.repo;

import com.example.assesmentbackend.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByProductCode(String productCode);
}