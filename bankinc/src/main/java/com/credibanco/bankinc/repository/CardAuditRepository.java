package com.credibanco.bankinc.repository;

import com.credibanco.bankinc.domain.CardAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAuditRepository extends JpaRepository<CardAudit, Long> {
}
