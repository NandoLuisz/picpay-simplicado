package com.luis.picpaysimplicadochallenger.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "transfers")
@Table(name = "transfers")
public class Transfers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Float value;

    @Column(nullable = false)
    private Integer payer;

    @Column(nullable = false)
    private Integer payee;

    public Transfers(Float value, Integer payer, Integer payee) {
        this.value = value;
        this.payer = payer;
        this.payee = payee;
    }

    public Transfers() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Integer getPayer() {
        return payer;
    }

    public void setPayer(Integer payer) {
        this.payer = payer;
    }

    public Integer getPayee() {
        return payee;
    }

    public void setPayee(Integer payee) {
        this.payee = payee;
    }
}
