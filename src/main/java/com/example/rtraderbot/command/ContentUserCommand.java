package com.example.rtraderbot.command;

import com.example.rtraderbot.UserContext;
import com.example.rtraderbot.model.entity.UserAccount;
import com.example.rtraderbot.model.entity.UserContent;
import com.example.rtraderbot.service.UserAccountService;
import com.example.rtraderbot.service.UserContentService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentUserCommand implements Command {

    private final UserContentService userContentService;
    private final UserAccountService userAccountService;

    @Override
    public String getCommand() {
        return "!content-user";
    }

    @Override
    public String getParams() {
        return "{mentionUser}";
    }

    @Override
    public String getDescription() {
        return "return all user's content or mention user";
    }

    @Override
    public void execute(MessageReceivedEvent event, UserContext sender) {
        List<Member> members = event.getMessage().getMentionedMembers();
        if (members.isEmpty()) {
            event.getChannel().sendMessage(" you must mention user").queue();
            return;
        } else if (members.size() > 1) {
            event.getChannel().sendMessage(" you can mention only one user").queue();
            return;
        }

        String mention = members.get(0).getUser().getAsMention();
        UserAccount userAccount = userAccountService.get(mention).orElseThrow();


        List<UserContent> contents = userContentService.getAll(userAccount);
        String collect = contents.stream().map(content ->
                "ContentId: " + content.getId() + " | " + "Name: " + content.getName() + " | " + "Price: " + content.getPrice().toString()
        ).collect(Collectors.joining("\n"));

        event.getChannel().sendMessage(collect).queue();
    }

}
