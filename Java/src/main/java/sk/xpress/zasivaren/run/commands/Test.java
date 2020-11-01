package sk.xpress.zasivaren.run.commands;

import net.dv8tion.jda.api.entities.User;
import sk.xpress.zasivaren.core.commands.Command;
import sk.xpress.zasivaren.core.commands.ICommand;

public class Test implements ICommand {

    @Command(command = "ping", description = {"", "cte"}, aliases = {"a", "b"})
    public void onCommand(User user, String command, String[] args, String arg) {
            System.out.println("[COMMAND]: " + user.getName() + ", CMD: " + command + ", ARGS: " +  args + ", ARG:" + arg);
            if(args != null)
            for(String s : args){
                System.out.println("ARGS: " + s);
            }
    }

}
