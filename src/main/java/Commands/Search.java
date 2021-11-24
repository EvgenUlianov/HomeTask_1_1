package Commands;

import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Search implements Command{

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
        if (TaskDescription.checkName(substring)) return;
        //Abstraction: we are going to change "List" to Database
        TasksData tasks = TasksDataList.get();
        tasks.printTasks(task -> task.getName().contains(substring));
    }
}
