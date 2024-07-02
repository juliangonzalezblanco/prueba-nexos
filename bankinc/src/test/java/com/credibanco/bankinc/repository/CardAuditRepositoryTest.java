package com.credibanco.bankinc.repository;

import com.credibanco.bankinc.domain.Card;
import com.credibanco.bankinc.domain.CardAudit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class CardAuditRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardAuditRepository cardAuditRepository;

    @Test
    @Rollback(false)
    public void testSaveAndFindById() {
        // Given
        Card card = new Card();
        card.setCardId(1L);
        card.setProductId(123L);
        card.setExpirationDate(new Date());
        card.setState("ACTIVE");
        card.setBalance(300000D);
        cardRepository.save(card);

        CardAudit cardAudit = new CardAudit();
        cardAudit.setCard(card);
        cardAudit.setDate(new Date());
        cardAudit.setOldBalance(1000.0);
        cardAudit.setNewBalance(900.0);
        cardAudit.setOldState("ACTIVE");
        cardAudit.setNewState("INACTIVE");

        // When
        cardAuditRepository.save(cardAudit);

        // Then
        Optional<CardAudit> found = cardAuditRepository.findById(cardAudit.getId());

        // Verify interactions
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(cardAudit.getId());
    }
}
