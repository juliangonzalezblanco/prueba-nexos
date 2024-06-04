package com.credibanco.bankinc.dto.apiResponse;

public class TransactionResponse {

    private Long transactionId;

    private Double price;

    public TransactionResponse(Long transactionId, Double price) {
        this.transactionId = transactionId;
        this.price = price;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
