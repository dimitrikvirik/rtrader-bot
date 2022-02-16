package com.example.rtraderbot.command;

import com.example.rtraderbot.UserContext;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceCommand implements Command {

    @Override
    public String getCommand() {
        return "!balance";
    }

    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Show your balance";
    }

    @Override
    public void execute(MessageReceivedEvent event, UserContext sender) {
        MessageChannel chanel = event.getChannel();
        chanel.sendMessage("Your balance is: " + sender.getUser().getBalance()).queue();
    }
}
