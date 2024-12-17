package com.example.assesmentbackend.service;

import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.TransactionRequest;
import com.example.assesmentbackend.model.request.UpdateTransactionRequest;

public interface ProductService {
    TransaksiPenutupanProduct createTransaction(TransactionRequest transactionRequest);
    TransaksiPenutupanProduct updateTransaction(TransaksiPenutupanProduct transaksiPenutupanProduct, UpdateTransactionRequest transactionRequest);
}
