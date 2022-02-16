package com.example.rtraderbot.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class UserAccount {

    @Id
    private String username;

    private Double balance = 20.0;

    @OneToMany(mappedBy = "owner")
    private List<UserContent> contents;

    @ManyToMany
    private List<Server> servers;

}
