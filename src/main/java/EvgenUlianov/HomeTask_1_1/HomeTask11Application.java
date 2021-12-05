package EvgenUlianov.HomeTask_1_1;

import EvgenUlianov.HomeTask_1_1.Commands.*;
import EvgenUlianov.HomeTask_1_1.General.CompletionControl;
import EvgenUlianov.HomeTask_1_1.General.WordDelimiter;
import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@SpringBootApplication
public class HomeTask11Application implements CommandLineRunner {//

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HomeTask11Application.class, args);
    }

    @Autowired
    private List<Command> commandsList;

    @Autowired
    private CompletionControl completionControl;

    @Autowired
    private IOStream ioStream;

    private Map<String, Command> commands;


    @Override
    public void run(String... args) throws Exception {

        ioStream.println("Список задач");

        log.info("Запуск программы Список задач");

        commands = new TreeMap<>();
        commandsList.stream().forEach((command)->commands.put(command.getName(),    command));

        // оставлю этот код, чтобы помнить про рефлекссии
////        Set<Class<? extends Command>> subTypes = new Reflections("EvgenUlianov.HomeTask_1_1.Commands").getSubTypesOf(Command.class);
////        for (Class clazz : subTypes) {
////            try {
////
////                if (clazz.getSimpleName().equals("Add"))
////                    commands.put(add.getName(), add);
////                else{
////                    Command command = (Command) clazz.getDeclaredConstructor().newInstance();
////                    commands.put(command.getName(), command);
////                }
////            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
////                log.error(e.getMessage(), e);
////                e.printStackTrace();
////            }
////        }

        ioStream.println("Список комманд: ");
        commands.values().forEach((command) -> {ioStream.println(command.getDescription());});

        do {
            ioStream.println("type command");
            String commandName = ioStream.readLine();

            log.debug("User input: {}", commandName);

            if (commandName == null)
                break;

            WordDelimiter wordDelimiter = new WordDelimiter(commandName);
            String mainCommandWord = wordDelimiter.getFirstWord();
            String argumentCommandWord = wordDelimiter.getSecondWord();

            Command command = commands.get(mainCommandWord);
            if (command == null) {
                String msg = String.format("Unknown command %s", mainCommandWord);
                ioStream.println(msg);
                log.error(msg);
            } else {
                command.accept(argumentCommandWord);
            }
        } while (!completionControl.isMustBeCompleted());

    }
}
