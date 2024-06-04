package com.credibanco.bankinc.dto.apiRequest;

import jakarta.validation.constraints.NotNull;

public class AnulateTransactionRequest {

    @NotNull(message = "cardId requerido")
    private Long cardId;

    @NotNull(message = "transactionId requerido")
    private Long transactionId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
