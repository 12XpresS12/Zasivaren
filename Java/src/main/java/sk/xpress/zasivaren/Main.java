package sk.xpress.zasivaren;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sk.xpress.zasivaren.commands.CommandRegister;
import sk.xpress.zasivaren.commands.Test;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {


    public static void main(String[] args) throws LoginException {
        JDA api = JDABuilder.createDefault(botToken.getBotToken()).build();
        api.addEventListener(new Main());
        api.addEventListener(new CommandRegister());

        CommandRegister.registerCommand(new Test());
    }
}
;