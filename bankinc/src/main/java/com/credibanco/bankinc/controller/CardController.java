package com.credibanco.bankinc.controller;

import com.credibanco.bankinc.dto.apiRequest.ActivateCardRequest;
import com.credibanco.bankinc.dto.apiRequest.AddBalanceRequest;
import com.credibanco.bankinc.dto.apiResponse.ApiResponse;
import com.credibanco.bankinc.dto.apiResponse.ConsultBalanceResponse;
import com.credibanco.bankinc.dto.apiResponse.CreateCardResponse;
import com.credibanco.bankinc.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{productId}/number")
    public ResponseEntity<ApiResponse<CreateCardResponse>> createCard(@PathVariable Long productId) {
        CreateCardResponse createCardResponse = cardService.createCard(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(HttpStatus.CREATED.value(), "Card creada", createCardResponse));
    }

    @PostMapping("/enroll")
    public ResponseEntity<ApiResponse<?>> activateCard(@Valid @RequestBody ActivateCardRequest activateCardRequest) {
        cardService.activateCard(activateCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Card activada satisfactoriamente", null));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<ApiResponse<?>> blockCard(@PathVariable Long cardId) {
        cardService.blockCard(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Card bloqueada satisfactoriamente", null));
    }

    @PostMapping("/balance")
    public ResponseEntity<ApiResponse<CreateCardResponse>> addBalance(@Valid @RequestBody AddBalanceRequest addBalanceRequest) {
        cardService.addBalance(addBalanceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.OK.value(), "Saldo añadido satisfactoriamente", null));
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<ApiResponse<ConsultBalanceResponse>> getCard(@PathVariable Long cardId) {
        ConsultBalanceResponse consultBalanceResponse = cardService.getBalanceById(cardId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Consulta exitosa", consultBalanceResponse));
    }
}
