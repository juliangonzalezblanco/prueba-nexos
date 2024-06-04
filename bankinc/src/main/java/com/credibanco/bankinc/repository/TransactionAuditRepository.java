package com.credibanco.bankinc.repository;


import com.credibanco.bankinc.domain.TransactionAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionAuditRepository extends JpaRepository<TransactionAudit, Long> {
}
