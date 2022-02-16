package com.example.rtraderbot.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "server_")
@Data
public class Server {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToMany(mappedBy = "servers")
    private List<UserAccount> users;

}
