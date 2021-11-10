
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Slf4j
public class Main {
    static boolean needToQuit = false;

    public static void main(String[] args) {
        System.out.println("Список задач");

        log.info("Запуск программы Список задач");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<TaskDescription> tasks = new ArrayList<>();

        Map<String, Consumer<String>> commands = new HashMap<>();

        commands.put("add", (name) -> {
            if (checkName(name)) return;

            TaskDescription taskDescription = new TaskDescription(name);
            tasks.add(taskDescription);
        });

        commands.put("print", (argument) -> IntStream.range(0, tasks.size())
                .filter((index) -> {TaskDescription task = tasks.get(index);
                    return argument.equals("all") || !(task.isCompleted());
                })
                .forEach((index) -> printTask(index, tasks.get(index))));

        commands.put("toggle", (stringNumber) -> {
            Integer number = getNumber(stringNumber);
            if (number == null) return;
            if (number <= 0 || number > tasks.size()) {
                printAndLog(String.format("идентификатор %d вне границ массива задач", number));
                return;
            }

            TaskDescription taskDescription;
            taskDescription = tasks.get(number - 1);
            taskDescription.toggle();
        });

        commands.put("delete", (stringNumber) -> {
            Integer number = getNumber(stringNumber);
            if (number == null) return;
            if (number <= 0 || number > tasks.size()) {
                printAndLog(String.format("идентификатор %d вне границ массива задач", number));
                return;
            }
            tasks.remove(number - 1);
        });

        commands.put("edit", (argumentCommandWord) -> {

            WordDelimiter wordDelimiter = new WordDelimiter(argumentCommandWord);
            String name = wordDelimiter.secondWord;
            if (checkName(name)) return;

            String stringNumber = wordDelimiter.firstWord;
            Integer number = getNumber(stringNumber);
            if (number == null) return;
            if (number <= 0 || number > tasks.size()) {
                printAndLog(String.format("идентификатор %d вне границ массива задач", number));
                return;
            }

            TaskDescription taskDescription;
            taskDescription = tasks.get(number - 1);
            taskDescription.setName(name);

        });

        commands.put("search", (substring) -> {
            if (checkName(substring)) return;

            IntStream.range(0, tasks.size())
                    .filter((index) -> {TaskDescription task = tasks.get(index);
                        return task.getName().contains(substring);
                    })
                    .forEach((index) -> printTask(index, tasks.get(index)));
        });


        commands.put("quit", (argument) -> {
            needToQuit = true;
            System.out.println("Программа завершена");
        });

        System.out.println("Список комманд: ");
        commands.keySet().forEach(System.out::println);

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
            String mainCommandWord = wordDelimiter.firstWord;
            String argumentCommandWord = wordDelimiter.secondWord;

            Consumer<String> command = commands.get(mainCommandWord);
            if (command == null) {
                printAndLog(String.format("Unknown command %s", mainCommandWord));
            } else {
                command.accept(argumentCommandWord);
            }
        } while (!needToQuit);

    }

    private static class WordDelimiter {
        private final String firstWord;
        private final String secondWord;

        public WordDelimiter(String commandName) {
            final char SPACE = ' ';
            final int spacePosition = commandName.indexOf(SPACE);
            if (spacePosition > 0){
                this.firstWord = commandName.substring(0, spacePosition).trim().toLowerCase();
                this.secondWord = commandName.substring(spacePosition + 1).trim().toLowerCase();
            } else {
                this.firstWord = commandName.trim().toLowerCase();
                this.secondWord = "";
            }
        }
    }

    private static boolean checkName (String name) {
        if (name.length() == 0) {
            printAndLog("Не указано наименование");
            return true;
        }
        return false;
    }

    private static Integer getNumber(String stringNumber) {
        if (stringNumber.length() == 0) {
            printAndLog("Не указан номер");
            return null;
        }

        int number;
        try {
            number = Integer.parseInt(stringNumber);
        } catch (NumberFormatException ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
            return null;
        }
        return number;
    }

    private static void printTask(int key, TaskDescription task) {
        System.out.printf("%d. [%s] %s%n", key + 1, (task.isCompleted() ? "x" : " "), task.getName());
    }

    public static void printAndLog(String msg){
        System.out.println(msg);
        log.error(msg);
        /*
        может-быть пригодится когда-нибудь
        if (Main.isLoggingEnabled()) {

            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(msg);
            if (trace.length > 2) {
                stringBuilder.append("\n");
                IntStream.range(2, trace.length)
                        .forEach((index) -> {
                            String traceText = trace[index].toString();
                            stringBuilder.append("\t\t");
                            stringBuilder.append(traceText);
                            stringBuilder.append("\n");
                        });
            }
        }*/
    }
}
