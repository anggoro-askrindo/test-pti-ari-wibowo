package com.example.assesmentbackend.repo;

import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransaksiPenutupanProductRepository extends JpaRepository<TransaksiPenutupanProduct, UUID> {
}
