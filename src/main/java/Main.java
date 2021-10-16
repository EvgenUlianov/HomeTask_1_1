import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        final String ADD_COMMAND = "add";
        final String PRINT_COMMAND = "print";
        final String TOGGLE_COMMAND = "toggle";
        final String QUIT_COMMAND = "quit";
        System.out.println("Список задач");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<TaskDescription> tasks = new ArrayList<>();

        Map<String, Consumer<String>> commands = new HashMap<>();

        commands.put(ADD_COMMAND, (name) -> {
            if (name.length() == 0) {
                System.out.println("Не указано наименование");
                return;
            }

            TaskDescription taskDescription = new TaskDescription(name);
            tasks.add(taskDescription);
        });

        commands.put(PRINT_COMMAND, (argument) -> {
            // аргумент "all" воспринимается, остальное игнорируется
            for (int i = 0; i < tasks.size(); i++) {
                TaskDescription task = tasks.get(i);
                if (argument.equals("all") || !(task.isCompleted()))
                    System.out.printf("%d. [%s] %s%n", i + 1, (task.isCompleted() ? "x" : " "), task.getName());
            }
        });

        commands.put(TOGGLE_COMMAND, (stringNumber) -> {
            if (stringNumber.length() == 0) {
                System.out.println("Не указан номер");
                return;
            }

            int number;
            try {
                number = Integer.parseInt(stringNumber);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                return;
            }
            TaskDescription taskDescription;
            try {
                taskDescription = tasks.get(number - 1);
            } catch (IndexOutOfBoundsException ex){
                ex.printStackTrace();
                return;
            }
            taskDescription.toggle();

        });

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

            String[] commandWords;
            String delimiter = " ";
            if (commandName == null)
                break;

            commandWords = commandName.trim().toLowerCase().split(delimiter);

            String mainCommandWord = commandWords[0];
            String argumentCommandWord;
            if (commandWords.length > 1)
                argumentCommandWord = commandWords[1];
            else
                argumentCommandWord = "";

            Consumer<String> command  = commands.get(mainCommandWord);
            if (command == null)
                break;
            command.accept(argumentCommandWord);
            if (mainCommandWord.equals(QUIT_COMMAND))
                break;
        }

    }
}
