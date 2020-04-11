package sk.xpress.zasivaren.commands;

import net.dv8tion.jda.api.entities.User;

public class Test implements ICommand {

    public void onCommand(User user, String command, String[] args, String arg) {
        if(command.equalsIgnoreCase("ping")){
            System.out.println("[COMMAND]: " + user.getName() + ", CMD: " + command + ", ARGS: " +  args + ", ARG:" + arg);
            if(args != null)
            for(String s : args){
                System.out.println("ARGS: " + s);
            }

        }
    }

}
