package com.example.rtraderbot.repository;

import com.example.rtraderbot.model.entity.UserAccount;
import com.example.rtraderbot.model.entity.UserContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContentRepository extends JpaRepository<UserContent, Long> {

    List<UserContent> findAllByOwner(UserAccount owner);


}
