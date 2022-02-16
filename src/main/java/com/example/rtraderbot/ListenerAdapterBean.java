package com.example.rtraderbot;

import jakarta.annotation.PostConstruct;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ListenerAdapterBean extends ListenerAdapter {

    @Autowired
    private JDA jda;

    @PostConstruct
    public void init() {
        jda.addEventListener(this);
    }


}
