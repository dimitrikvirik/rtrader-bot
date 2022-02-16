package com.example.rtraderbot.command;

import com.example.rtraderbot.UserContext;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

@Service
public class PingCommand implements Command {
    @Override
    public String getCommand() {
        return "!ping";
    }

    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Print !pong and time to response";
    }

    @Override
    public void execute(MessageReceivedEvent event, UserContext sender) {
        MessageChannel chanel = event.getChannel();
        long time = System.currentTimeMillis();
        chanel.sendMessage("Pong!").queue(
                response ->
                        response.editMessageFormat("Pong! %s ms", System.currentTimeMillis() - time).queue()
        );
    }
}
