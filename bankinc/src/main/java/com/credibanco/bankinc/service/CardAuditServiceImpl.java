package com.credibanco.bankinc.service;

import com.credibanco.bankinc.domain.CardAudit;
import com.credibanco.bankinc.repository.CardAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardAuditServiceImpl implements CardAuditService{

    @Autowired
    private CardAuditRepository cardAuditRepository;

    @Override
    public void createCardAudit(CardAudit cardAudit){
        cardAuditRepository.save(cardAudit);
    }
}
