package com.credibanco.bankinc.controller;

import com.credibanco.bankinc.dto.TransactionDTO;
import com.credibanco.bankinc.dto.apiRequest.AddBalanceRequest;
import com.credibanco.bankinc.dto.apiRequest.AnulateTransactionRequest;
import com.credibanco.bankinc.dto.apiRequest.CreateTransactionRequest;
import com.credibanco.bankinc.dto.apiResponse.ApiResponse;
import com.credibanco.bankinc.dto.apiResponse.TransactionResponse;
import com.credibanco.bankinc.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest) {
        TransactionResponse transactionResponse = transactionService.createTransaction(createTransactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Transaccion creada satisfactoriamente", transactionResponse));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<ApiResponse<TransactionDTO>> findTransaction(@PathVariable Long transactionId) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Transaccion consultada satisfactoriamente", transactionDTO));
    }

    @PostMapping("/anulation")
    public ResponseEntity<ApiResponse<TransactionDTO>> anulateTransaction(@Valid @RequestBody AnulateTransactionRequest anulateTransactionRequest) {
        transactionService.anulateTransaction(anulateTransactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Transaccion anulada satisfactoriamente", null));
    }
}
