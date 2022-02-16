package com.example.rtraderbot;

import com.example.rtraderbot.command.Command;
import com.example.rtraderbot.model.entity.Server;
import com.example.rtraderbot.model.entity.UserAccount;
import com.example.rtraderbot.service.ServerService;
import com.example.rtraderbot.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageListener extends ListenerAdapterBean {

    private final List<? extends Command> commands;
    private final UserAccountService userAccountService;
    private final ServerService serverService;


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        MessageChannel chanel = event.getChannel();
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!trader-help")) {
            String collect = commands.stream().map(c ->
                    c.getCommand() + " - " + c.getParams() + " - " + c.getDescription()
            ).collect(Collectors.joining("\n"));
            chanel.sendMessage(collect).queue();
        } else if (!msg.getContentRaw().isBlank() && !event.getAuthor().isBot()) {
            String s = msg.getContentRaw().split(" ")[0];
            Optional<? extends Command> command = commands.stream().filter(c ->
                    c.getCommand().equals(s)
            ).findFirst();


            if (command.isPresent()) {
                String mention = event.getAuthor().getAsMention();
                String serverId = event.getGuild().getId();
                Server server = serverService.get(serverId).orElseGet(
                        () -> {
                            Server newServer = new Server();
                            newServer.setId(serverId);
                            return serverService.save(newServer);
                        }
                );


                UserAccount account = userAccountService.get(mention).orElseGet(
                        () -> {
                            UserAccount newUserAccount = new UserAccount();
                            newUserAccount.setUsername(mention);
                            newUserAccount.setServers(Collections.singletonList(server));
                            return userAccountService.save(newUserAccount);
                        }
                );

                UserContext userContext = new UserContext();
                userContext.setUser(account);
                userContext.setServer(server);

                command.get().execute(event, userContext);
            }
        }
    }
}
