package com.credibanco.bankinc.controller;

import com.credibanco.bankinc.dto.apiRequest.ActivateCardRequest;
import com.credibanco.bankinc.dto.apiRequest.AddBalanceRequest;
import com.credibanco.bankinc.dto.apiResponse.ApiResponse;
import com.credibanco.bankinc.dto.apiResponse.ConsultBalanceResponse;
import com.credibanco.bankinc.dto.apiResponse.CreateCardResponse;
import com.credibanco.bankinc.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    public void setup() {}

    @Test
    public void testCreateCard() {
        // Given
        CreateCardResponse mockResponse = new CreateCardResponse(1L, "08/2024");
        when(cardService.createCard(1L)).thenReturn(mockResponse);

        // When
        ResponseEntity<ApiResponse<CreateCardResponse>> responseEntity = cardController.createCard(1L);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Card creada");
        assertThat(responseEntity.getBody().getData()).isEqualTo(mockResponse);

        // Verify
        verify(cardService, times(1)).createCard(1L);
    }

    @Test
    public void testActivateCard() {
        // Given
        ActivateCardRequest activateCardRequest = new ActivateCardRequest();
        activateCardRequest.setCardId(1L);

        // When
        ResponseEntity<ApiResponse<?>> responseEntity = cardController.activateCard(activateCardRequest);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Card activada satisfactoriamente");

        // Verify
        verify(cardService, times(1)).activateCard(activateCardRequest);
    }

    @Test
    public void testBlockCard() {
        // When
        ResponseEntity<ApiResponse<?>> responseEntity = cardController.blockCard(1L);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Card bloqueada satisfactoriamente");

        // Verify
        verify(cardService, times(1)).blockCard(1L);
    }

    @Test
    public void testAddBalance() {
        // Given
        AddBalanceRequest addBalanceRequest = new AddBalanceRequest();
        addBalanceRequest.setCardId(1L);
        addBalanceRequest.setBalance(500.0);

        // When
        ResponseEntity<ApiResponse<CreateCardResponse>> responseEntity = cardController.addBalance(addBalanceRequest);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Saldo añadido satisfactoriamente");

        // Verify
        verify(cardService, times(1)).addBalance(addBalanceRequest);
    }

    @Test
    public void testGetCard() {
        // Given
        ConsultBalanceResponse mockResponse = new ConsultBalanceResponse(1L, 1000.0);
        when(cardService.getBalanceById(1L)).thenReturn(mockResponse);

        // When
        ResponseEntity<ApiResponse<ConsultBalanceResponse>> responseEntity = cardController.getCard(1L);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getMessage()).isEqualTo("Consulta exitosa");
        assertThat(responseEntity.getBody().getData()).isEqualTo(mockResponse);

        // Verify
        verify(cardService, times(1)).getBalanceById(1L);
    }
}
