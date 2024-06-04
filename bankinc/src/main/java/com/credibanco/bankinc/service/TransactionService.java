package com.credibanco.bankinc.service;

import com.credibanco.bankinc.dto.TransactionDTO;
import com.credibanco.bankinc.dto.apiRequest.AnulateTransactionRequest;
import com.credibanco.bankinc.dto.apiRequest.CreateTransactionRequest;
import com.credibanco.bankinc.dto.apiResponse.TransactionResponse;

public interface TransactionService {
    TransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest);

    TransactionDTO getTransactionById(Long transactionId);

    void anulateTransaction(AnulateTransactionRequest anulateTransactionRequest);
}
