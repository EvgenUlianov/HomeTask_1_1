import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        final String QUIT_COMMAND = "quit";
        System.out.println("Список задач");
        Scanner scanner = new Scanner(System.in);

        List<TaskDescription> tasks = new ArrayList<>();

        Map<String, Consumer<String>> commands = new HashMap<>();

        commands.put("add", (name) -> {
            TaskDescription taskDescription = new TaskDescription(name);
            tasks.add(taskDescription);
        });

        commands.put("print", (argument) -> {
            for (int i = 0; i < tasks.size(); i++) {
                TaskDescription task = tasks.get(i);
                if (argument.equals("all") || task.isCompleted())
                    System.out.printf("%d. [%s] %s%n", i + 1, (task.isCompleted() ? "x" : " "), task.getName());
            }
        });

        commands.put("toggle", (stringNumber) -> {
            int number = Integer.parseInt(stringNumber);
            TaskDescription taskDescription = tasks.get(number - 1);
            taskDescription.toggle();
        });

        commands.put(QUIT_COMMAND, (argument) -> System.out.println("Программа завершена"));

        System.out.println("Список комманд: ");
        commands.keySet().forEach(System.out::println);

        while (true) {
            System.out.println("type command");
            String commandName = scanner.nextLine();

            String[] commandWords;
            String delimiter = " ";
            commandWords = commandName.split(delimiter);
            String commandWords0 = commandWords[0];
            String commandWords1;
            if (commandWords.length > 1)
                commandWords1 = commandWords[1];
            else
                commandWords1 = "";

            Consumer<String> command  = commands.get(commandWords0);
            if (command == null)
                break;
            command.accept(commandWords1);
            if (commandWords0.equals(QUIT_COMMAND))
                break;
        }

    }
}
