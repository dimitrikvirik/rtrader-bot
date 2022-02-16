package com.example.rtraderbot.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    private Server server;

    @ManyToOne(optional = false)
    private UserAccount from;

    @ManyToOne(optional = false)
    private UserAccount to;

}
