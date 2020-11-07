package sk.xpress.zasivaren.core.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CommandRegister extends ListenerAdapter {

    private static final String commandPrefix = "?";

    private final List<Method> registerCommands = new ArrayList<>();

    public CommandRegister() {
        Reflections reflections = new Reflections("sk.xpress.zasivaren.run.commands", new MethodAnnotationsScanner());
        Set<Method> methods = reflections.getMethodsAnnotatedWith(Command.class);
        registerCommands.addAll(methods);
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        if(event.getMessage().getContentDisplay() == null)
            return;

        if(event.getMessage().getAttachments().size() >= 1)
            return;

        String[] content = event.getMessage().getContentDisplay().split(" ");


        if(content.length < 1)
            return;

        String[] args = null;
        String arg = null;

        if(content.length >= 2) {
            arg = event.getMessage().getContentDisplay().substring(content[0].length()+1);
            args = arg.split(" ");
        }

        String[] finalArgs = args;
        String finalArg = arg;
        registerCommands.forEach((method -> {
            Command command = method.getAnnotation(Command.class);
            if(content[0].length() <= 0)
                return;

            String commandName = content[0].substring(1);
            if(command.command().equals(commandName) || isAliassesEqualsName(commandName, command.aliases())) {

                if(!content[0].startsWith(command.commandPrefix()))
                    return;


                if(finalArg != null && finalArg.length() > 0 && finalArgs[0].length() > 0 && finalArgs[0].equalsIgnoreCase("-d")) {
                    if(command.description().length <= 0) return;
                    StringBuilder stringBuilder = new StringBuilder();
                    for(String desc : command.description()) {
                        if (desc.length() <= 0)
                            continue;
                        stringBuilder.append(desc+"\n");
                    }
                    event.getChannel().sendMessage(stringBuilder.toString()).queue();
                    return;
                }

                method.setAccessible(true);
                try {
                    method.invoke(method.getDeclaringClass().newInstance(), event.getAuthor(), commandName, finalArgs, finalArg, event);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private boolean isAliassesEqualsName(String name, String[] aliases) {
        if(aliases.length <= 0)
            return false;
        for(String s : aliases) {
            if(s.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
}
