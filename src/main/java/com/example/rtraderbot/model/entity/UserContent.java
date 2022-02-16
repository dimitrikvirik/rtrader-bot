package com.example.rtraderbot.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_content")
@Data
public class UserContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String url;

    private String name;

    private Double price;

    @ManyToOne
    private UserAccount owner;

}
