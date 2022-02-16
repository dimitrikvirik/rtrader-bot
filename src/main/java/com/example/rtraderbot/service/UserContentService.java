package com.example.rtraderbot.service;

import com.example.rtraderbot.model.entity.UserAccount;
import com.example.rtraderbot.model.entity.UserContent;
import com.example.rtraderbot.repository.UserContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserContentService {

    private final UserContentRepository repository;


    public void save(UserContent userContent){
        repository.save(userContent);
    }

    public Optional<UserContent> findById(Long id){
        return repository.findById(id);
    }

    public List<UserContent> getAll(UserAccount owner){
        return repository.findAllByOwner(owner);
    }



}
