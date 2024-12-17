package com.example.assesmentbackend.service;

import org.springframework.stereotype.Service;

@Service
public interface ProductTransactionFactoryService {
    ProductService getProductService(String productCode);
}
