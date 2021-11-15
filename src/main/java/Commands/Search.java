package Commands;

import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

import java.util.stream.IntStream;

public class Search extends Command{
    @Override
    public String getName() {
        return "search";
    }

    @Override
    public String getDescription() {
        return "search <substring>";
    }

    @Override
    public void accept(String substring) {
        if (super.checkName(substring)) return;
        //Abstraction: we are going to change "List" to Database
        TasksData tasks = TasksDataList.get();

        IntStream.range(0, tasks.size())
                .filter((index) -> {
                    TaskDescription task = tasks.get(index);
                    return task.getName().contains(substring);
                })
                .forEach(tasks::printTask);
    }
}
