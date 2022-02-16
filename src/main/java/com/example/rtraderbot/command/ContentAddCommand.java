package com.example.rtraderbot.command;

import com.example.rtraderbot.UserContext;
import com.example.rtraderbot.model.entity.UserContent;
import com.example.rtraderbot.service.UserContentService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentAddCommand implements Command {

    private final UserContentService userContentService;

    @Override
    public String getCommand() {
        return "!content-add";
    }

    @Override
    public String getParams() {
        return "{name} {price} | attachment {img}";
    }

    @Override
    public String getDescription() {
        return "Adds a new content to user collection";
    }

    @Override
    public void execute(MessageReceivedEvent event, UserContext sender) {

        List<Message.Attachment> attachments = event.getMessage().getAttachments();

        if (attachments.isEmpty()) {
            event.getChannel().sendMessage("You need to attach an image to this command").queue();
            return;
        }
        if (attachments.size() > 1) {
            event.getChannel().sendMessage("You can only attach one image to this command").queue();
            return;
        }
        String[] strings = event.getMessage().getContentRaw().split(" ");
        if (strings.length != 3) {
            event.getChannel().sendMessage("You need to provide all the parameters").queue();
            return;
        }
        String name = strings[1];
        try {
            Double price = Double.parseDouble(strings[2]);

            String url = attachments.get(0).getUrl();
            UserContent userContent = new UserContent();
            userContent.setUrl(url);
            userContent.setOwner(sender.getUser());
            userContent.setPrice(price);
            userContent.setName(name);
            userContentService.save(userContent);

            event.getChannel().sendMessage("Content added").queue();
        } catch (NumberFormatException e) {
            event.getChannel().sendMessage("You need to provide a valid price").queue();
        }
    }
}
