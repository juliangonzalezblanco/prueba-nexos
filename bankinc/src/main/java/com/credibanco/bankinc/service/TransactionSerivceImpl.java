package com.credibanco.bankinc.service;

import com.credibanco.bankinc.domain.Card;
import com.credibanco.bankinc.domain.Transaction;
import com.credibanco.bankinc.domain.TransactionAudit;
import com.credibanco.bankinc.dto.TransactionDTO;
import com.credibanco.bankinc.dto.apiRequest.AnulateTransactionRequest;
import com.credibanco.bankinc.dto.apiRequest.CreateTransactionRequest;
import com.credibanco.bankinc.dto.apiResponse.TransactionResponse;
import com.credibanco.bankinc.exception.ResourceNotFoundException;
import com.credibanco.bankinc.mapper.TransactionMapper;
import com.credibanco.bankinc.repository.CardRepository;
import com.credibanco.bankinc.repository.TransactionAuditRepository;
import com.credibanco.bankinc.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TransactionSerivceImpl implements TransactionService{

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionAuditRepository transactionAuditRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest){
        Card card = cardRepository.findCardByCardId(createTransactionRequest.getCardId());
        if(card==null) throw new ResourceNotFoundException("No existe la tarjeta");
        if(!"ACTIVE".equals(card.getState())) throw new ResourceNotFoundException("La tarjeta no se encuentra activa");
        if(card.getExpirationDate().before(new Date())) throw new ResourceNotFoundException("La tarjeta se ha expirado");
        if(createTransactionRequest.getPrice()>card.getBalance()) throw new ResourceNotFoundException("El saldo no es suficiente");
        Double newBalance = card.getBalance()-createTransactionRequest.getPrice();
        card.setBalance(newBalance);
        Transaction transaction = new Transaction(card, new Date(), createTransactionRequest.getPrice(), "APPROVED");
        transactionRepository.save(transaction);
        TransactionAudit transactionAudit = new TransactionAudit(transaction, new Date(), null, "APPROVED");
        transactionAuditRepository.save(transactionAudit);
        return new TransactionResponse(transaction.getId(), transaction.getPrice());
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId){
        Optional<Transaction> transactionOtp = transactionRepository.findById(transactionId);
        if(!transactionOtp.isPresent()) throw new ResourceNotFoundException("No se encontro la transaccion");
        return transactionMapper.toDTO(transactionOtp.get());
    }

    @Override
    @Transactional
    public void anulateTransaction(AnulateTransactionRequest anulateTransactionRequest){
        Transaction transaction = transactionRepository.findByIdAndCardCardId(anulateTransactionRequest.getTransactionId(), anulateTransactionRequest.getCardId());
        if(transaction==null) throw new ResourceNotFoundException("No se encontraron registros con los datos ingresados");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(transaction.getDate());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date compareDate = calendar.getTime();
        if(compareDate.before(new Date())) throw new ResourceNotFoundException("Han pasado 24 horas, ya no es posible anular la trasaccion");
        TransactionAudit transactionAudit = new TransactionAudit(transaction, new Date(), transaction.getState(), "ANULATED");
        transactionAuditRepository.save(transactionAudit);
        transaction.setState("ANULATED");
        Card card = transaction.getCard();
        Double newBalance = card.getBalance()+transaction.getPrice();
        card.setBalance(newBalance);
        cardRepository.save(card);
    }
}
