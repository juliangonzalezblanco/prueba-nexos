package com.credibanco.bankinc.dto.apiResponse;

public class ConsultBalanceResponse {

    private Long cardId;
    private Double balance;

    public ConsultBalanceResponse(Long cardId, Double balance) {
        this.cardId = cardId;
        this.balance = balance;
    }

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
