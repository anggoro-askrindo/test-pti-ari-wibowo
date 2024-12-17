package com.example.assesmentbackend.service;

import com.example.assesmentbackend.exception.DataNotFoundException;
import com.example.assesmentbackend.model.entity.Product;
import com.example.assesmentbackend.exception.BadRequestException;
import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.TransactionRequest;
import com.example.assesmentbackend.repo.ProductRepository;
import com.example.assesmentbackend.repo.TransaksiPenutupanProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final ProductRepository productRepository;
    private final TransaksiPenutupanProductRepository transaksiPenutupanProductRepository;
    private final ProductTransactionFactoryService productFactoryService;

    public TransactionService(ProductRepository productRepository, TransaksiPenutupanProductRepository transaksiPenutupanProductRepository, ProductTransactionFactoryService productFactoryService) {
        this.productRepository = productRepository;
        this.transaksiPenutupanProductRepository = transaksiPenutupanProductRepository;
        this.productFactoryService = productFactoryService;
    }

    public void createTransaction(TransactionRequest transactionRequest) {
        Product product = productRepository.findByProductCode(transactionRequest.getProductCode()).orElseThrow(() -> new BadRequestException("Product code tidak ditemukan"));
        ProductService productService = productFactoryService.getProductService(product.getProductCode());
        TransaksiPenutupanProduct transaksiPenutupanProduct = productService.createTransaction(transactionRequest);
        transaksiPenutupanProductRepository.save(transaksiPenutupanProduct);
    }

    public List<TransaksiPenutupanProduct> getTransaksiPenutupanProduct() {
        List<TransaksiPenutupanProduct> transaksiPenutupanProducts = transaksiPenutupanProductRepository.findAll();
        if (transaksiPenutupanProducts.isEmpty()) throw new DataNotFoundException("Data Transaksi tidak ditemukan");
        return transaksiPenutupanProducts;
    }
}
