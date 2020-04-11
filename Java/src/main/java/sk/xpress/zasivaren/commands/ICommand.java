package sk.xpress.zasivaren.commands;

import net.dv8tion.jda.api.entities.User;

public interface ICommand {

    public void onCommand(User user, String command, String[] args, String arg);

}
