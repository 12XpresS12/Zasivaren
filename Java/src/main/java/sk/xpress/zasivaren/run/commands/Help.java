package sk.xpress.zasivaren.run.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import sk.xpress.zasivaren.core.commands.Command;
import sk.xpress.zasivaren.core.commands.ICommand;

public class Help implements ICommand {

    @Override
    @Command(command = "help", description = {"A", "B", "C", "D"}, commandPrefix = "!")
    public void onCommand(User user, String command, String[] args, String arg, MessageReceivedEvent event) {
        event.getChannel().sendMessage("NICE, IDK HOW TO HELP YOU, BUT ITS BEUTIFUL, WHILE THIS IS WORKING").queue();
    }
}
