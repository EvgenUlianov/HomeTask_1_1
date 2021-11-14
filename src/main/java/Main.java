
import General.*;
import TaskManager.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Slf4j
public class Main {

    public static void main(String[] args) {
        System.out.println("Список задач");

        log.info("Запуск программы Список задач");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Consumer<String>> commands = new HashMap<>();

        //Pattern strategy for interface Consumer<String>
        commands.put("add", (name) -> {
            if (BasicFunctions.checkName(name)) return;
            //Abstraction: we are going to change "List" to Database
            TasksData tasks = TasksDataList.get();
            TaskDescription taskDescription = new TaskDescription(name);
            tasks.add(taskDescription);
        });

        //Pattern strategy for interface Consumer<String>
        commands.put("print", (argument) -> {
            //Abstraction: we are going to change "List" to Database
            TasksData tasks = TasksDataList.get();
            IntStream.range(0, tasks.size())
                .filter((index) -> {TaskDescription task = tasks.get(index);
                    return argument.equals("all") || !(task.isCompleted());
                })
                .forEach(tasks::printTask);});

        //Pattern strategy for interface Consumer<String>
        commands.put("toggle", (stringNumber) -> {
            Integer number = BasicFunctions.getNumber(stringNumber);
            //Abstraction: we are going to change "List" to Database
            TasksData tasks = TasksDataList.get();
            if (number == null) return;
            if (number <= 0 || number > tasks.size()) {
                BasicFunctions.printAndLog(String.format("идентификатор %d вне границ массива задач", number));
                return;
            }

            TaskDescription taskDescription;
            taskDescription = tasks.get(number - 1);
            taskDescription.toggle();
        });

        //Pattern strategy for interface Consumer<String>
        commands.put("delete", (stringNumber) -> {
            Integer number = BasicFunctions.getNumber(stringNumber);
            //Abstraction: we are going to change "List" to Database
            TasksData tasks = TasksDataList.get();
            if (number == null) return;
            if (number <= 0 || number > tasks.size()) {
                BasicFunctions.printAndLog(String.format("идентификатор %d вне границ массива задач", number));
                return;
            }
            tasks.remove(number - 1);
        });

        //Pattern strategy for interface Consumer<String>
        commands.put("edit", (argumentCommandWord) -> {

            WordDelimiter wordDelimiter = new WordDelimiter(argumentCommandWord);
            String name = wordDelimiter.getSecondWord();
            if (BasicFunctions.checkName(name)) return;

            String stringNumber = wordDelimiter.getFirstWord();
            Integer number = BasicFunctions.getNumber(stringNumber);
            if (number == null) return;
            //Abstraction: we are going to change "List" to Database
            TasksData tasks = TasksDataList.get();
            if (number <= 0 || number > tasks.size()) {
                BasicFunctions.printAndLog(String.format("идентификатор %d вне границ массива задач", number));
                return;
            }

            TaskDescription taskDescription;
            taskDescription = tasks.get(number - 1);
            taskDescription.setName(name);

        });

        //Pattern strategy for interface Consumer<String>
        commands.put("search", (substring) -> {
            if (BasicFunctions.checkName(substring)) return;
            //Abstraction: we are going to change "List" to Database
            TasksData tasks = TasksDataList.get();

            IntStream.range(0, tasks.size())
                    .filter((index) -> {TaskDescription task = tasks.get(index);
                        return task.getName().contains(substring);
                    })
                    .forEach(tasks::printTask);
        });

        //Pattern strategy for interface Consumer<String>
        commands.put("quit", (argument) -> {
            CompletionControl completionControl = CompletionControl.get();
            completionControl.setMustBeCompleted(true);
            System.out.println("Программа завершена");
        });

        System.out.println("Список комманд: ");
        commands.keySet().forEach(System.out::println);

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

            Consumer<String> command = commands.get(mainCommandWord);
            if (command == null) {
                BasicFunctions.printAndLog(String.format("Unknown command %s", mainCommandWord));
            } else {
                command.accept(argumentCommandWord);
            }
        } while (!completionControl.isMustBeCompleted());

    }
}
