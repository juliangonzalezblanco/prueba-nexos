package com.credibanco.bankinc.dto.apiResponse;


import java.util.Date;

public class CreateCardResponse {
    private Long cardId;
    private String expirationDate;

    public CreateCardResponse() {
    }

    public CreateCardResponse(Long cardId, String expirationDate) {
        this.cardId = cardId;
        this.expirationDate = expirationDate;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
