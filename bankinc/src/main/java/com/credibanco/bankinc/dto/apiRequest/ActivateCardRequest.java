package com.credibanco.bankinc.dto.apiRequest;

import jakarta.validation.constraints.NotNull;

public class ActivateCardRequest {

    @NotNull
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
