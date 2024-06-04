package com.credibanco.bankinc.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CARD_AUDIT")
public class CardAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_AUDIT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
    private Card card;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "OLD_BALANCE")
    private Double oldBalance;

    @Column(name = "NEW_BALANCE")
    private Double newBalance;

    @Column(name = "OLD_STATE")
    private String oldState;

    @Column(name = "NEW_STATE")
    private String newState;

    public CardAudit() {
    }

    public CardAudit(Card card, Date date, Double oldBalance, Double newBalance, String oldState, String newState) {
        this.card = card;
        this.date = date;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.oldState = oldState;
        this.newState = newState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Double oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Double newBalance) {
        this.newBalance = newBalance;
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
