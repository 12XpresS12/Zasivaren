package sk.xpress.zasivaren;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sk.xpress.zasivaren.core.commands.CommandRegister;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

    private static JDA jda;

    public static void main(String[] args) throws LoginException {
        JDA api = JDABuilder.createDefault(botToken.botKey).build();
        api.addEventListener(new Main());
        api.addEventListener(new CommandRegister());
        jda = api;
    }

    public static JDA getJDA() {
        return jda;
    }
}
