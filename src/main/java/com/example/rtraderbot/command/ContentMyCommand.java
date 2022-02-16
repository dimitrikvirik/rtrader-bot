package com.example.rtraderbot.command;

import com.example.rtraderbot.UserContext;
import com.example.rtraderbot.model.entity.UserContent;
import com.example.rtraderbot.service.UserContentService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentMyCommand implements Command {

    private final UserContentService userContentService;

    @Override
    public String getCommand() {
        return "!content-my";
    }

    @Override
    public String getParams() {
        return "{info/contentId}";
    }

    @Override
    public String getDescription() {
        return "Shows your content";
    }

    @Override
    public void execute(MessageReceivedEvent event, UserContext sender) {

        String[] strings = event.getMessage().getContentRaw().split(" ");
        if (strings.length != 2) {
            event.getChannel().sendMessage("You need to specify the second command").queue();
            return;
        }
        String s = strings[1];
        if (s.equals("info")) {
            List<UserContent> userContents = userContentService.getAll(sender.getUser());
            String collect = userContents.stream().map(content ->
                    "ContentId: " + content.getId() + " | " + "Name: " + content.getName() + " | " + "Price: " + content.getPrice().toString()
            ).collect(Collectors.joining("\n"));
            event.getChannel().sendMessage(collect).queue();
        } else {
            try {
                Optional<UserContent> userContent = userContentService.findById(Long.parseLong(s));
                if (userContent.isPresent()) {
                    UserContent content = userContent.get();
                    String s1 = "ContentId: " + content.getId() + " \n " + "Name: " + content.getName() + " \n " + "Price: " + content.getPrice().toString() + "\n" + content.getUrl();
                    event.getChannel().sendMessage(s1).queue();
                } else {
                    event.getChannel().sendMessage("Content not found").queue();
                }

            } catch (NumberFormatException e) {
                event.getChannel().sendMessage("You need to specify valid number").queue();
            }
        }
    }
}
