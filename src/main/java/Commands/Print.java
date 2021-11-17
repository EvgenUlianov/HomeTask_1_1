package Commands;

import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Print implements Command{

    @Override
    public String getName() {
        return "print";
    }

    @Override
    public String getDescription() {
        return "print [all]";
    }

    @Override
    public void accept(String argument) {

        //Abstraction: we are going to change "List" to Database
        TasksData tasks = TasksDataList.get();
        tasks.printTasks(task -> argument.equals("all") || !(task.isCompleted()));

    }
}
