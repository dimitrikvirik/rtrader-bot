package com.example.rtraderbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.security.auth.login.LoginException;

@SpringBootApplication
public class RtraderBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RtraderBotApplication.class, args);
    }

    @Bean
    public JDA jda() throws LoginException {
        return JDABuilder.createDefault("")
                .setActivity(Activity.watching("!trader-help"))
                .build();
    }


}
