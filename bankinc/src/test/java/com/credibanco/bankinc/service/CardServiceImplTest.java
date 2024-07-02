package com.credibanco.bankinc.service;

import com.credibanco.bankinc.domain.Card;
import com.credibanco.bankinc.domain.CardAudit;
import com.credibanco.bankinc.dto.apiRequest.ActivateCardRequest;
import com.credibanco.bankinc.dto.apiRequest.AddBalanceRequest;
import com.credibanco.bankinc.dto.apiResponse.ConsultBalanceResponse;
import com.credibanco.bankinc.dto.apiResponse.CreateCardResponse;
import com.credibanco.bankinc.exception.ResourceNotFoundException;
import com.credibanco.bankinc.repository.CardRepository;
import com.credibanco.bankinc.util.RandomNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardAuditService cardAuditService;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCard() {
        // Given
        Long productId = 123456L;
        when(cardRepository.save(any(Card.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CreateCardResponse response = cardService.createCard(productId);

        // Then
        assertNotNull(response);
        assertNotNull(response.getCardId());
        assertNotNull(response.getExpirationDate());

        // Verify interactions
        verify(cardRepository, times(1)).save(any(Card.class));
        verify(cardAuditService, times(1)).createCardAudit(any(CardAudit.class));
    }

    @Test
    public void testActivateCard() {
        // Given
        ActivateCardRequest request = new ActivateCardRequest();
        request.setCardId(123L);
        Card card = new Card(123L, 456L, new Date(), 0.0, "ACTIVE");

        // When
        when(cardRepository.findCardByCardId(anyLong())).thenReturn(card);

        // Then
        assertThrows(ResourceNotFoundException.class, () -> cardService.activateCard(request));

        // Verify interactions
        verify(cardAuditService, never()).createCardAudit(any(CardAudit.class));
        verify(cardRepository, never()).save(any(Card.class));
    }

    @Test
    public void testBlockCard() {
        // Given
        Long cardId = 123L;
        Card card = new Card(cardId, 456L, new Date(), 0.0, "ACTIVE");
        when(cardRepository.findCardByCardId(cardId)).thenReturn(card);

        // When
        cardService.blockCard(cardId);

        // Then
        assertEquals("BLOCK", card.getState());
        verify(cardAuditService, times(1)).createCardAudit(any(CardAudit.class));
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    public void testAddBalance() {
        // Given
        AddBalanceRequest request = new AddBalanceRequest();
        request.setCardId(123L);
        request.setBalance(100.0);
        Card card = new Card(123L, 456L, new Date(), 50.0, "ACTIVE");
        when(cardRepository.findCardByCardId(anyLong())).thenReturn(card);

        // When
        cardService.addBalance(request);

        // Then
        assertEquals(150.0, card.getBalance());
        verify(cardAuditService, times(1)).createCardAudit(any(CardAudit.class));
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    public void testGetBalanceById() {
        // Given
        Long cardId = 123L;
        Card card = new Card(cardId, 456L, new Date(), 100.0, "ACTIVE");
        when(cardRepository.findCardByCardId(cardId)).thenReturn(card);

        // When
        ConsultBalanceResponse response = cardService.getBalanceById(cardId);

        // Then
        assertNotNull(response);
        assertEquals(cardId, response.getCardId());
        assertEquals(100.0, response.getBalance());
    }
}
