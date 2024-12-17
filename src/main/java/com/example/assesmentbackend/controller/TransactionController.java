package com.example.assesmentbackend.controller;

import com.example.assesmentbackend.model.entity.TransaksiPenutupanProduct;
import com.example.assesmentbackend.model.request.Payload;
import com.example.assesmentbackend.model.request.TransactionRequest;
import com.example.assesmentbackend.model.response.ApiResponse;
import com.example.assesmentbackend.service.TransactionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PreAuthorize("hasRole('MARKETING')")
    @PostMapping(value="/create")
    public ApiResponse<Object> createTransaction(@Valid @RequestBody Payload<TransactionRequest> transaction){
        transactionService.createTransaction(transaction.getRequest());
        return ApiResponse.ok(null, "Create Successfully");
    }

    @GetMapping
    public ApiResponse<Object> getTransaction(){
        List<TransaksiPenutupanProduct> transaksiPenutupanProductList = transactionService.getTransaksiPenutupanProduct();
        return ApiResponse.ok(transaksiPenutupanProductList, "Data Found");
    }
}
