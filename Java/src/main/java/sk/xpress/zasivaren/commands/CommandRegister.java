package sk.xpress.zasivaren.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CommandRegister extends ListenerAdapter {

    private static final String commandPrefix = "?";

    private static List<ICommand> commands = new ArrayList<ICommand>();

    public static void registerCommand(ICommand command) {
        commands.add(command);
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if(event.getMessage().getContentDisplay() == null)
            return;

        if(event.getMessage().getAttachments().size() >= 1)
            return;

        String[] content = event.getMessage().getContentDisplay().split(" ");
        if(!content[0].startsWith(commandPrefix)) {
            System.out.println("STARTS WITH: " + content[0]);
            return;
        }

        if(content.length < 1)
            return;

        String[] args = null;
        String arg = null;

        if(content.length >= 2) {
            arg = event.getMessage().getContentDisplay().substring(content[0].length()+1);
            args = arg.split(" ");
        }

        for(ICommand cmd : commands){
            cmd.onCommand(event.getAuthor(), content[0].substring(1), args, arg);
        }


    }
}
