import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

public class Main {

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

    public static void main(String[] args) {
        System.out.println("Список задач");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<TaskDescription> tasks = new ArrayList<>();

        Map<String, Consumer<String>> commands = new HashMap<>();

        commands.put("add", (name) -> {
            if (checkName(name)) return;

            TaskDescription taskDescription = new TaskDescription(name);
            tasks.add(taskDescription);
        });

        commands.put("print", (argument) -> {
            // аргумент "all" воспринимается, остальное игнорируется
            for (int i = 0; i < tasks.size(); i++) {
                TaskDescription task = tasks.get(i);
                if (argument.equals("all") || !(task.isCompleted()))
                    printTask(i, task);
            }
        });

        commands.put("toggle", (stringNumber) -> {
            Integer number = getNumber(stringNumber);
            if (number == null) return;
            TaskDescription taskDescription;
            try {
                taskDescription = tasks.get(number - 1);
            } catch (IndexOutOfBoundsException ex){
                ex.printStackTrace();
                return;
            }
            taskDescription.toggle();

        });

        commands.put("delete", (stringNumber) -> {
            Integer number = getNumber(stringNumber);
            if (number == null) return;
            TaskDescription taskDescription;
            try {
                taskDescription = tasks.remove(number - 1);
            } catch (IndexOutOfBoundsException ex){
                ex.printStackTrace();
                return;
            }
            taskDescription.toggle();

        });

        commands.put("edit", (argumentCommandWord) -> {

            WordDelimiter wordDelimiter = new WordDelimiter(argumentCommandWord);
            String name = wordDelimiter.secondWord;
            if (checkName(name)) return;

            String stringNumber = wordDelimiter.firstWord;
            Integer number = getNumber(stringNumber);
            if (number == null) return;


            TaskDescription taskDescription;
            try {
                taskDescription = tasks.get(number - 1);
            } catch (IndexOutOfBoundsException ex){
                ex.printStackTrace();
                return;
            }
            taskDescription.setName(name);

        });

        commands.put("search", (substring) -> {
            if (checkName(substring)) return;

            for (int i = 0; i < tasks.size(); i++) {
                TaskDescription task = tasks.get(i);
                if (task.getName().contains(substring)) {
                    printTask(i, task);
                }
            }
        });


        final String QUIT_COMMAND = "quit";
        commands.put(QUIT_COMMAND, (argument) -> System.out.println("Программа завершена"));

        System.out.println("Список комманд: ");
        commands.keySet().forEach(System.out::println);

        while (true) {
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

            Consumer<String> command  = commands.get(mainCommandWord);
            if (command == null)
                break;
            command.accept(argumentCommandWord);
            if (mainCommandWord.equals(QUIT_COMMAND))
                break;
        }

    }

    private static boolean checkName (String name) {
        if (name.length() == 0) {
            System.out.println("Не указано наименование");
            return true;
        }
        return false;
    }

    private static void printTask(int i, TaskDescription task) {
        System.out.printf("%d. [%s] %s%n", i + 1, (task.isCompleted() ? "x" : " "), task.getName());
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


}
