package com.example.rtraderbot;

import com.example.rtraderbot.model.entity.Server;
import com.example.rtraderbot.model.entity.UserAccount;
import lombok.Data;

@Data
public class UserContext {

    private UserAccount user;

    private Server server;


}
