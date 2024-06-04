package com.credibanco.bankinc.dto.apiRequest;


import jakarta.validation.constraints.NotNull;

public class AddBalanceRequest {

    @NotNull(message = "El campo 'cardId' no puede ser nulo")
    private Long cardId;

    @NotNull(message = "El campo 'balance' no puede ser nulo")
    private Double balance;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
