package com.example.rtraderbot.repository;

import com.example.rtraderbot.model.entity.Server;
import com.example.rtraderbot.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    Optional<UserAccount> findByUsername(String username);

    List<UserAccount> findAllByServersContains(Server server);
}
