package com.example.assesmentbackend.service;

import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.TransactionRequest;

public interface ProductService {
    TransaksiPenutupanProduct createTransaction(TransactionRequest transactionRequest);
}
