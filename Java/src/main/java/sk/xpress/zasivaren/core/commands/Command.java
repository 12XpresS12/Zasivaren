package sk.xpress.zasivaren.core.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    String command();
    String commandPrefix() default "?";
    String[] aliases() default {};
    String[] description();
}
