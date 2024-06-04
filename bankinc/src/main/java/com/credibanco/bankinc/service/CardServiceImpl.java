package com.credibanco.bankinc.service;

import com.credibanco.bankinc.domain.Card;
import com.credibanco.bankinc.domain.CardAudit;
import com.credibanco.bankinc.dto.CardDTO;
import com.credibanco.bankinc.dto.apiRequest.ActivateCardRequest;
import com.credibanco.bankinc.dto.apiRequest.AddBalanceRequest;
import com.credibanco.bankinc.dto.apiResponse.ConsultBalanceResponse;
import com.credibanco.bankinc.dto.apiResponse.CreateCardResponse;
import com.credibanco.bankinc.exception.ResourceNotFoundException;
import com.credibanco.bankinc.mapper.CardMapper;
import com.credibanco.bankinc.repository.CardRepository;
import com.credibanco.bankinc.util.RandomNumberGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardAuditService cardAuditService;

    @Override
    @Transactional
    public CreateCardResponse createCard(Long productId) {
        if(productId == null || productId.toString().length()!=6) throw new ResourceNotFoundException("El tamaño del id producto no puede ser diferente de 6.");
        Long id = RandomNumberGenerator.generateRandomNumber(productId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 3);
        Date expirationDate = calendar.getTime();
        Card card = new Card(id, productId, expirationDate, 0.0, "CREATED");
        cardRepository.save(card);

        CardAudit cardAudit = new CardAudit(card, new Date(), null, 0.0, null, "CREATED");
        cardAuditService.createCardAudit(cardAudit);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        String formattedDate = sdf.format(card.getExpirationDate());
        CreateCardResponse createCardResponse = new CreateCardResponse(card.getCardId(), formattedDate);
        return createCardResponse;
    }

    @Override
    @Transactional
    public void activateCard(ActivateCardRequest activateCardRequest){
        Card card = cardRepository.findCardByCardId(activateCardRequest.getCardId());
        if(card==null) throw new ResourceNotFoundException("No se encontro id "+activateCardRequest.getCardId());
        if(!"CREATED".equals(card.getState())) throw new ResourceNotFoundException("El estado actual de la tarjeta no es valido.");
        if("ACTIVE".equals(card.getState())) throw new ResourceNotFoundException("Ya se encuenta activa "+activateCardRequest.getCardId());
        CardAudit cardAudit = new CardAudit(card, new Date(), null, 0.0, card.getState(), "ACTIVE");
        cardAuditService.createCardAudit(cardAudit);
        card.setState("ACTIVE");
        cardRepository.save(card);
    }

    @Override
    @Transactional
    public void blockCard(Long idCard){
        if(idCard==null) throw new ResourceNotFoundException("idCard requerido");
        Card card = cardRepository.findCardByCardId(idCard);
        if(card==null) throw new ResourceNotFoundException("No se encontro id "+idCard);
        if(!"ACTIVE".equals(card.getState())) throw new ResourceNotFoundException("Tarjeta en estado no valido para bloquear");
        CardAudit cardAudit = new CardAudit(card, new Date(), null, 0.0, card.getState(), "BLOCK");
        cardAuditService.createCardAudit(cardAudit);
        card.setState("BLOCK");
        cardRepository.save(card);
    }

    @Override
    @Transactional
    public void addBalance(AddBalanceRequest addBalanceRequest){
        Card card = cardRepository.findCardByCardId(addBalanceRequest.getCardId());
        if(card==null) throw new ResourceNotFoundException("No se encontro id "+addBalanceRequest.getCardId());
        if(!"ACTIVE".equals(card.getState())) throw new ResourceNotFoundException("La tarjeta aun no se encuentra activa");
        Double newBalance = card.getBalance()+addBalanceRequest.getBalance();
        CardAudit cardAudit = new CardAudit(card, new Date(), card.getBalance(), newBalance, null, null);
        cardAuditService.createCardAudit(cardAudit);
        card.setBalance(newBalance);
        cardRepository.save(card);
    }

    @Override
    public ConsultBalanceResponse getBalanceById(Long cardId){
        Card card = cardRepository.findCardByCardId(cardId);
        if(card==null) throw new ResourceNotFoundException("Id no encontrado");
        return new ConsultBalanceResponse(card.getCardId(), card.getBalance());
    }
}
