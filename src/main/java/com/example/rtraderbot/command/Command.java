package com.example.rtraderbot.command;

import com.example.rtraderbot.UserContext;
import com.example.rtraderbot.model.entity.UserContent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Repository;

@Repository
public interface Command {

     String getCommand();

     String getParams();

     String getDescription();

     void execute(MessageReceivedEvent event, UserContext sender);

}
