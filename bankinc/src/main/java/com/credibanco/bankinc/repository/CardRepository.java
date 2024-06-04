package com.credibanco.bankinc.repository;

import com.credibanco.bankinc.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    public Card findCardByCardId(Long id);

}
