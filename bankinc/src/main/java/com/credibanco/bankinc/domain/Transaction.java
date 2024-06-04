package com.credibanco.bankinc.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
    private Card card;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "STATE")
    private String state;

    public Transaction() {
    }

    public Transaction(Card card, Date date, Double price, String state) {
        this.card = card;
        this.date = date;
        this.price = price;
        this.state = state;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
