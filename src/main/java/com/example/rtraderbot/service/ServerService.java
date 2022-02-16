package com.example.rtraderbot.service;

import com.example.rtraderbot.model.entity.Server;
import com.example.rtraderbot.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServerService {

    private final ServerRepository serverRepository;

    public Server save(Server server) {
       return serverRepository.save(server);
    }

    public Optional<Server> get(String id){
        return serverRepository.findById(id);
    }

}
