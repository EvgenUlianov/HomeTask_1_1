
import Commands.Command;
import General.*;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
public class Main {

    public static void main(String[] args) {
        System.out.println("Список задач");

        log.info("Запуск программы Список задач");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Command> commands1 = new TreeMap<>();

        Set<Class<? extends Command>> subTypes = new Reflections("Commands").getSubTypesOf(Command.class);
        for (Class clazz : subTypes) {
            try {
                Command command = (Command) clazz.getDeclaredConstructor().newInstance();
                commands1.put(command.getName(), command);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                log.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }

        System.out.println("Список комманд: ");
        //       commands1.values().map((command) -> {command.getDescription();}).forEach(System.out::println);
       commands1.values().forEach((command) -> {System.out.println(command.getDescription());});

        CompletionControl completionControl = CompletionControl.get();
        do {
            System.out.println("type command");
            String commandName = null;
            try {
                commandName = reader.readLine();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                e.printStackTrace();
            }
            log.debug("User input: {}", commandName);

            if (commandName == null)
                break;

            WordDelimiter wordDelimiter = new WordDelimiter(commandName);
            String mainCommandWord = wordDelimiter.getFirstWord();
            String argumentCommandWord = wordDelimiter.getSecondWord();

            Command command = commands1.get(mainCommandWord);
            if (command == null) {
                String msg = String.format("Unknown command %s", mainCommandWord);
                System.out.println(msg);
                log.error(msg);
            } else {
                command.accept(argumentCommandWord);
            }
        } while (!completionControl.isMustBeCompleted());

    }
}
