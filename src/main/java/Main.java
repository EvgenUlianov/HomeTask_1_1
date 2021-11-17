
import Commands.Command;
import General.*;
import IO.IOStream;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
public class Main {

    public static void main(String[] args) {
        IOStream ioStream = IOStream.get();
        ioStream.println("Список задач");

        log.info("Запуск программы Список задач");

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

        ioStream.println("Список комманд: ");
        commands1.values().forEach((command) -> {ioStream.println(command.getDescription());});

        CompletionControl completionControl = CompletionControl.get();
        do {
            ioStream.println("type command");
            String commandName = ioStream.readLine();
//            try {
//                commandName = reader.readLine();
//            } catch (IOException e) {
//                log.error(e.getMessage(), e);
//                e.printStackTrace();
//            }
            log.debug("User input: {}", commandName);

            if (commandName == null)
                break;

            WordDelimiter wordDelimiter = new WordDelimiter(commandName);
            String mainCommandWord = wordDelimiter.getFirstWord();
            String argumentCommandWord = wordDelimiter.getSecondWord();

            Command command = commands1.get(mainCommandWord);
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
