package EvgenUlianov.HomeTask_1_1.Commands;


import org.springframework.context.annotation.Configuration;

//Pattern strategy for abstract class Command
@Configuration
public interface Command {

    public abstract String getName();

    public abstract String getDescription();

    public abstract void accept(String argumentCommandWord);

}
