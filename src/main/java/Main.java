import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    static boolean needToQuit = false;

    public static void main(String[] args) {
        System.out.println("Список задач");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //List<TaskDescription> tasks = new ArrayList<>();
        // need the type "TreeMap" because of using "lastKey()"
        TreeMap<Integer, TaskDescription> tasks = new TreeMap<>();

        Map<String, Consumer<String>> commands = new HashMap<>();

        commands.put("add", (name) -> {
            if (checkName(name)) return;

            TaskDescription taskDescription = new TaskDescription(name);
            int key;
            if (tasks.isEmpty()){
                key = 1;
            } else {
                key = tasks.lastKey() + 1;
            }
            tasks.put(key, taskDescription);
        });

        commands.put("print", (argument) -> {
            // аргумент "all" воспринимается, остальное игнорируется
            tasks.entrySet().stream()
                    .filter((entry) -> {TaskDescription task = entry.getValue();
                        return argument.equals("all") || !(task.isCompleted());
                    })
                    .forEach(Main::printTask);
        });

        commands.put("toggle", (stringNumber) -> {
            Integer number = getNumber(stringNumber);
            if (number == null) return;
            TaskDescription taskDescription;
            taskDescription = tasks.get(number);
            if (taskDescription == null) {
                System.out.println("идентификатор не определен");
                return;
            }
            taskDescription.toggle();

        });

        commands.put("delete", (stringNumber) -> {
            Integer number = getNumber(stringNumber);
            if (number == null) return;
            tasks.remove(number);
        });

        commands.put("edit", (argumentCommandWord) -> {

            WordDelimiter wordDelimiter = new WordDelimiter(argumentCommandWord);
            String name = wordDelimiter.secondWord;
            if (checkName(name)) return;

            String stringNumber = wordDelimiter.firstWord;
            Integer number = getNumber(stringNumber);
            if (number == null) return;


            TaskDescription taskDescription;
            taskDescription = tasks.get(number);
            if (taskDescription == null) {
                System.out.println("идентификатор не определен");
                return;
            }
            taskDescription.setName(name);

        });

        commands.put("search", (substring) -> {
            if (checkName(substring)) return;

            tasks.entrySet().stream()
                    .filter((entry) -> {TaskDescription task = entry.getValue();
                        return task.getName().contains(substring);
                    })
                    .forEach(Main::printTask);
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
                e.printStackTrace();
            }

            if (commandName == null)
                break;


            WordDelimiter wordDelimiter = new WordDelimiter(commandName);
            String mainCommandWord = wordDelimiter.firstWord;
            String argumentCommandWord = wordDelimiter.secondWord;

            Consumer<String> command = commands.get(mainCommandWord);
            if (command == null) {
                System.out.println("Unknown command");
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
            System.out.println("Не указано наименование");
            return true;
        }
        return false;
    }

    private static Integer getNumber(String stringNumber) {
        if (stringNumber.length() == 0) {
            System.out.println("Не указан номер");
            return null;
        }

        int number;
        try {
            number = Integer.parseInt(stringNumber);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return null;
        }
        return number;
    }

    private static void printTask(Map.Entry<Integer, TaskDescription> entry) {
        Integer key = entry.getKey();
        TaskDescription task = entry.getValue();
        System.out.printf("%d. [%s] %s%n", key, (task.isCompleted() ? "x" : " "), task.getName());
    }


}
