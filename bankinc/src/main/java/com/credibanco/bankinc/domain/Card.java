package com.credibanco.bankinc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CARD")
public class Card {
    @Id
    @NotNull
    @Column(name = "CARD_ID")
    private Long cardId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "STATE")
    private String state;

    public Card() {}

    public Card(Long cardId, Long productId, Date expirationDate, Double balance, String state) {
        this.cardId = cardId;
        this.productId = productId;
        this.expirationDate = expirationDate;
        this.balance = balance;
        this.state = state;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
