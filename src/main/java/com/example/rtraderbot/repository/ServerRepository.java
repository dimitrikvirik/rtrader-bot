package com.example.rtraderbot.repository;

import com.example.rtraderbot.model.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, String> {
}
