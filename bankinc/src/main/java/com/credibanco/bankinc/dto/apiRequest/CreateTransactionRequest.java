package com.credibanco.bankinc.dto.apiRequest;

import jakarta.validation.constraints.NotNull;

public class CreateTransactionRequest {

    @NotNull(message = "cardId es requerido")
    private Long cardId;

    @NotNull(message = "price es requerido")
    private Double price;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
