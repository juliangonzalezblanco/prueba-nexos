package com.credibanco.bankinc.repository;


import com.credibanco.bankinc.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public Transaction findByIdAndCardCardId(Long id, Long cardId);

}
