package com.example.assesmentbackend.repo;

import com.example.assesmentbackend.model.entity.MLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MLookupRepository extends JpaRepository<MLookup, String> {
    Optional<MLookup> findByLookupGroupAndKeyOnly(String lookupGroup, String keyOnly);
}