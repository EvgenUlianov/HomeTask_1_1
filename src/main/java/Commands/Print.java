package Commands;

import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

import java.util.stream.IntStream;

public class Print extends Command{
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
        IntStream.range(0, tasks.size())
                .filter((index) -> {
                    TaskDescription task = tasks.get(index);
                    return argument.equals("all") || !(task.isCompleted());
                })
                .forEach(tasks::printTask);
    }
}
