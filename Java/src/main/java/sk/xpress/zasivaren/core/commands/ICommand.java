package sk.xpress.zasivaren.core.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommand {

    public void onCommand(User user, String command, String[] args, String arg, MessageReceivedEvent event);

}
