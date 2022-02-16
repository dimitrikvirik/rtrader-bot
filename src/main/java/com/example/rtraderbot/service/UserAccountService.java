package com.example.rtraderbot.service;

import com.example.rtraderbot.model.entity.Server;
import com.example.rtraderbot.model.entity.UserAccount;
import com.example.rtraderbot.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository repository;

    public UserAccount save(UserAccount userAccount) {
        return repository.save(userAccount);
    }

    public Optional<UserAccount> get(String userName) {
        return repository.findByUsername(userName);
    }

    public List<UserAccount> getAll(Server server) {
        return repository.findAllByServersContains(server);
    }


}
