package com.credibanco.bankinc.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TRANSACTION_AUDIT")
public class TransactionAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_AUDIT_ID")
    private Long transactionAuditId;

    @ManyToOne
    @JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "TRANSACTION_ID")
    private Transaction transaction;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "OLD_STATE")
    private String oldState;

    @Column(name = "NEW_STATE")
    private String newState;

    public TransactionAudit() {
    }

    public TransactionAudit(Transaction transaction, Date date, String oldState, String newState) {
        this.transaction = transaction;
        this.date = date;
        this.oldState = oldState;
        this.newState = newState;
    }

    public Long getTransactionAuditId() {
        return transactionAuditId;
    }

    public void setTransactionAuditId(Long transactionAuditId) {
        this.transactionAuditId = transactionAuditId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOldState() {
        return oldState;
    }

    public void setOldState(String oldState) {
        this.oldState = oldState;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(String newState) {
        this.newState = newState;
    }
}
