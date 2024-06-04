package com.credibanco.bankinc.service;

import com.credibanco.bankinc.dto.CardDTO;
import com.credibanco.bankinc.dto.apiRequest.ActivateCardRequest;
import com.credibanco.bankinc.dto.apiRequest.AddBalanceRequest;
import com.credibanco.bankinc.dto.apiResponse.ConsultBalanceResponse;
import com.credibanco.bankinc.dto.apiResponse.CreateCardResponse;

import java.util.List;

public interface CardService {
    CreateCardResponse createCard(Long productId);

    void activateCard(ActivateCardRequest activateCardRequest);

    void blockCard(Long idCard);

    void addBalance(AddBalanceRequest addBalanceRequest);

    ConsultBalanceResponse getBalanceById(Long cardId);
}
